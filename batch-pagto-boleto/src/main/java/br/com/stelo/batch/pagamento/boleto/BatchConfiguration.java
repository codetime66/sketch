package br.com.stelo.batch.pagamento.boleto;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.FormatterLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import br.com.stelo.batch.pagamento.boleto.model.PagamentoMatera;
import br.com.stelo.batch.pagamento.boleto.model.RegistroArquivoTiff;
import br.com.stelo.batch.pagamento.boleto.reader.PagamentoMateraMapper;
import br.com.stelo.batch.pagamento.boleto.reader.PagamentoMateraMapperFooter;
import br.com.stelo.batch.pagamento.boleto.reader.PagamentoMateraMapperHeader;
import br.com.stelo.batch.pagamento.boleto.repository.dao.IBatchExecutorDAO;
import br.com.stelo.batch.pagamento.boleto.writer.PagamentoMateraFooterCallback;
import br.com.stelo.batch.pagamento.boleto.writer.PagamentoMateraHeaderCallback;
import br.com.stelo.batch.pagamento.boleto.processor.PagamentoMateraItemProcessor;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration(exclude = { BatchAutoConfiguration.class })
public class BatchConfiguration extends DefaultBatchConfigurer {

	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

	private final String FILE_SUFFIX="REM";
	private final String RESOURCES_PROTOCOL="file:";
	
	@Value("${boleto.pagmento.in}")
	private String INPUT_LOCATION;

	@Value("${boleto.pagmento.out}")
	private String OUTPUT_LOCATION;

	@Value("${boleto.pagamento.arquivotiff}")
	private String ARQUIVO_TIFF_PREFIX;
	
	@Autowired
	private IBatchExecutorDAO batchExecutorDAO;

	@Autowired
	private DataSource dataSource;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public FlatFileItemReader<PagamentoMatera> pagamentoMateraItemReader;	
	
	@Autowired
	public FlatFileItemWriter<RegistroArquivoTiff> procsItemWriter;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

//partitioner-----------------------------------------------------------------------------/	
	@Bean("partitioner")
	@StepScope
	public Partitioner partitioner() {
		log.info("In Partitioner");

		MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = null;
		try {
			resources = resolver.getResources(inputResources());
		} catch (IOException e) {
			e.printStackTrace();
		}
		partitioner.setResources(resources);
		partitioner.partition(10);
		return partitioner;
	}
	
	private String inputResources() {
		StringBuilder sb = new StringBuilder();
		sb.append(RESOURCES_PROTOCOL);
		sb.append(INPUT_LOCATION);
		sb.append("*.");
		sb.append(FILE_SUFFIX);
		return sb.toString();
	}

	private synchronized String getOutputFileName() {
		Integer sequencial = batchExecutorDAO.getSequencial();
		sequencial++;
		batchExecutorDAO.atualizaSequencial(sequencial);

		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String novoSequencial = StringUtils.leftPad(sequencial.toString(), 5, '0');
		
		StringBuilder sb = new StringBuilder();
		sb.append(OUTPUT_LOCATION);
		sb.append("D");
		sb.append(dateFormat.format(new Date()));
		sb.append("_");
		sb.append(novoSequencial);
		sb.append("_");
		sb.append(ARQUIVO_TIFF_PREFIX);
		sb.append(".");
		sb.append(FILE_SUFFIX);
		
		return sb.toString();		
	}
//end---/	
	
//reader--------------------------------------------------------------------------------------------------/
	@Bean
	@StepScope
	@Qualifier("pagamentoMateraItemReader")
	@DependsOn("partitioner")
    public FlatFileItemReader<PagamentoMatera> pagamentoMateraItemReader(@Value("#{stepExecutionContext['fileName']}") String filename) throws Exception {

            log.info("###BatchConfiguration.pagamentoMateraItemReader");

            FlatFileItemReader<PagamentoMatera> reader = new FlatFileItemReader<PagamentoMatera>();
            reader.setName("pagamentoMateraItemReader");
            reader.setResource(new UrlResource(filename));
            reader.setLinesToSkip(0);
            reader.setLineMapper(pagamentoMateraMultiLineMapper());

            return reader;
    }
	 
	@Bean
	public LineMapper<PagamentoMatera> pagamentoMateraMultiLineMapper() throws Exception {

		PatternMatchingCompositeLineMapper<PagamentoMatera> mapper = new PatternMatchingCompositeLineMapper<>();

		Map<String, LineTokenizer> tokenizers = new HashMap<String, LineTokenizer>();
		tokenizers.put("01*", detailLineTokenizer());
		tokenizers.put("00*", headerLineTokenizer());
		tokenizers.put("99*", footerLineTokenizer());
		mapper.setTokenizers(tokenizers);

		Map<String, FieldSetMapper<PagamentoMatera>> mappers = new HashMap<String, FieldSetMapper<PagamentoMatera>>();
		mappers.put("01*", detailFieldSetMapper());
		mappers.put("00*", headerFieldSetMapper());
		mappers.put("99*", footerFieldSetMapper());
		mapper.setFieldSetMappers(mappers);

		return mapper;
	}

	@Bean
	public LineTokenizer detailLineTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setColumns(new Range[] { new Range(1, 2), new Range(3, 16), new Range(17, 31), new Range(32, 39),
				new Range(40, 56), new Range(57, 60), new Range(61, 68), new Range(69, 76), new Range(77, 84) });
		tokenizer.setNames(new String[] { "tipoPagamento", "numeroDocumento", "valor", "idStelo", "codigoPedido",
				"agencia", "contaCorrente", "vencimento", "pagamento" });
		return tokenizer;
	}

	@Bean
	public LineTokenizer headerLineTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setColumns(new Range[] { new Range(1, 2), new Range(3, 10), new Range(11, 16) });
		tokenizer.setNames(new String[] { "tipoRegistro", "data", "sequencia" });
		return tokenizer;
	}

	@Bean
	public LineTokenizer footerLineTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setColumns(new Range[] { new Range(1, 2), new Range(3, 10), new Range(11, 15), new Range(16, 21) });
		tokenizer.setNames(new String[] { "tipoRegistro", "data", "sequencia", "quantidadeRegs" });
		return tokenizer;
	}

	@Bean
	public FieldSetMapper<PagamentoMatera> detailFieldSetMapper() throws Exception {
		return new PagamentoMateraMapper();
	}

	@Bean
	public FieldSetMapper<PagamentoMatera> headerFieldSetMapper() throws Exception {
		return new PagamentoMateraMapperHeader();
	}

	@Bean
	public FieldSetMapper<PagamentoMatera> footerFieldSetMapper() throws Exception {
		return new PagamentoMateraMapperFooter();
	}
//end-----/

//processor-------------------------------------------------------------------------/	
	@Bean
	@StepScope
	public PagamentoMateraItemProcessor processor() {
		return new PagamentoMateraItemProcessor();
	}
//end-----/

//writer-------------------------------------------------------------------------/	
	@Bean
	@StepScope
	public FlatFileItemWriter<RegistroArquivoTiff> procsItemWriter(@Value("#{StepExecution}") StepExecution stepExecution) {
		log.info("###BatchConfiguration.procsItemWriter(): ");

		String tiffFileName = getOutputFileName();
		stepExecution.getExecutionContext().put("tiffFileName", tiffFileName);
		
		FlatFileItemWriter<RegistroArquivoTiff> writer = new FlatFileItemWriter<RegistroArquivoTiff>();
		writer.setResource(new FileSystemResource(tiffFileName));
		writer.setHeaderCallback(headerCallback());
		writer.setFooterCallback(footerCallback());
		writer.setLineAggregator(formatterLineAggregator());

		return writer;
	}

	@Bean
	@StepScope
	public PagamentoMateraHeaderCallback headerCallback() {
		return new PagamentoMateraHeaderCallback();
	}

	@Bean
	@StepScope
	public PagamentoMateraFooterCallback footerCallback() {
		return new PagamentoMateraFooterCallback();
	}

	private LineAggregator<RegistroArquivoTiff> formatterLineAggregator() {
		FormatterLineAggregator<RegistroArquivoTiff> lineAggregator = new FormatterLineAggregator<>();
		lineAggregator.setFieldExtractor(fieldExtractor());
		lineAggregator.setFormat("30%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s");
		return lineAggregator;
	}

	private BeanWrapperFieldExtractor<RegistroArquivoTiff> fieldExtractor() {
		BeanWrapperFieldExtractor<RegistroArquivoTiff> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor
				.setNames(new String[] { "sequencial", "numOriginal", "dataTranscao", "mti", "codigoProcessamento", "codigoAutorizacao", "horaTransacao" , "numeroTerminal", "idCliente", "dataExpiracao", "codigoMoeda", "valorTransacao", "entrada", "codigoPais", "traceNumber", "rrn", "codigoPlanoVenda" , "idBranch" , "idDepartamento" , "tipoTecnologia", "posData", "metodoVerificacao", "numCartao", "sourceInterface", "fieldLength" , "field" , "codigoResposta" , "idTransacaoVisa" , "dadosRede" , "dataLiquidacao" , "idSeguranca" , "codigoIdentificacao" , "mcc", "idPromocao", "valorEntrada" , "taxaEmbarque" , "valorReembolso" , "idRequirente" , "idBin" , "idTransacao" , "taxaCambio" , "nomeEC", "dataOriginalTransacao" , "horaOriginalTransacao" , "idOriginalTransacao", "rrnOriginal" , "codigoServico" , "merchante" , "coBinGroup" , "cmsProduct" , "transactionType" , "binGroup" , "tipoConta" , "dCCIndicator" , "reconciliationAmount" , "reconciliationCurrency" , "amountAdditionals" , "amountAdditionalsCurrency" , "dCCExchangeForReconciliation" , "dCCExchangeRateWithMarkup" , "dCCExchangeRateWithoutMarkup" });
		return fieldExtractor;
	}
//end---------/
		
//job------------------------------------------------------------------------------------//
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(masterStep())
				.end()
				.build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<PagamentoMatera, RegistroArquivoTiff>chunk(10)
				.reader(pagamentoMateraItemReader)
				.processor(processor())
				.writer(procsItemWriter)
				.build();
	}

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setQueueCapacity(10);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}

	@Bean
	@Qualifier("masterStep")
	public Step masterStep() {
		return stepBuilderFactory.get("masterStep")
				.partitioner("step1", partitioner())
				.step(step1())
				.taskExecutor(taskExecutor())
				.build();
	}	
//end-------------/	
}
