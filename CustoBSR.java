package br.com.cetip.aplicacao.cobranca.batch.arquivo.processador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import br.com.cetip.aplicacao.cobranca.batch.arquivo.vo.Faixa;
import br.com.cetip.aplicacao.cobranca.batch.arquivo.vo.TabelaPrecoBSRVO;
import br.com.cetip.infra.relatorios.AbstractJobRelatorio;
import br.com.cetip.infra.atributo.Atributo;
import br.com.cetip.infra.atributo.utilitario.DescricaoAtributo;
import br.com.cetip.infra.atributo.utilitario.xml.ConversorXML;
import br.com.cetip.infra.atributo.utilitario.xml.data.DescricaoBatch;
import br.com.cetip.infra.log.Logger;

/**
 * Classe auxiliar para processadores da cobranca.
 * @author Carlos Fernandes
 * @version 0.1
 */
public class CustoBSR extends AbstractJobRelatorio {

	public static String SISTEMA21 = "55"; 
	public static String OBJSERV_SISTEMA21 = "724";
	public static String D0 = "0";	
	public static String PATH_ARQUIVO = "Texto(ARQUIVO)=";
	public static String NOME_ARQUIVO = "Nome(ARQUIVO)=";
	public static String TABELA_PRECO = "Texto(TABELAPRECO)=";
	public static String OPERACAO_COMPROMISSADA = "TextoArquivo(OPERACAO_COMPROMISSADA)=";
	
	public static final BigDecimal ZERO = new BigDecimal(0);
	
	protected String pathArquivo;
	protected List arquivos;
	protected String listaDeOperacoesCompromissadas;
	protected String numDiasUteis;	
    protected Date dataD0;
	
	protected Hashtable tabelaPreco;
	
	protected Connection conn;

	//protected GrupoEconomico grupoEconomicoCache;
	
	/**
	 * retorna a conexao com o banco.
	 * @return
	 */
	protected Connection getConn() {
		return conn;
	}

	/**
	 * 
	 * Construtor
	 */
	public CustoBSR(){
		pathArquivo = null;
		arquivos = null;
        numDiasUteis=null;	
	    dataD0=null;
	    tabelaPreco=null;
	    listaDeOperacoesCompromissadas=null;
	}

	/**
	 * monta faxias de cobranca para aplicacao da tabela de preco.
	 * @param faixaREG
	 */
	protected void montaTabelaPreco(String faixaREG) {
		if(faixaREG!=null){
		   if(tabelaPreco==null){
			   tabelaPreco = new Hashtable();
		   }
	       Faixa fx = criaFaixa(faixaREG);
    	   tabelaPreco.put(fx.getCodigo(),fx);   
		}
	}
	
	/**
	 * monta o VO com a faixa de cobranca.
	 * @param aux
	 * @return
	 */
	protected Faixa criaFaixa(String aux) {
	    String[] tokens = aux.split(";");
	    Faixa fx = new Faixa();
	    fx.setCodigo(tokens[0]);
	    fx.setMinimo(Double.parseDouble(tokens[1]));
	    fx.setMaximo(Double.parseDouble(tokens[2]));
	    fx.setTp(tokens[3]);
	    return fx;
	}
	
	/**
	 * Obtem o preco de cobranca.
	 * @param quantidade
	 * @param criterio
	 * @param subcriterio
	 * @param grupo
	 * @param tp
	 * @return
	 * @throws Exception
	 */
	protected TabelaPrecoBSRVO obterPreco(BigDecimal quantidade,
            String criterio,
            String subcriterio,
            String grupo,
            String tp) throws Exception {
		
            TabelaPrecoBSRVO tabelaPrecoBSRVO = null;    	
            try {
                tabelaPrecoBSRVO = obterValorItemCobranca(criterio, 
                                                          subcriterio, 
                                                          grupo, 
                                                          getFaixa(quantidade,tp), 
                                                          null);
            } catch (Exception e) {
                if ( "NAO ENCONTREI VALOR ITEM COBRANCA".equals(e.getMessage()) ) {
                   //Esse Erro eh esperado no caso de nao existir o preco na tabela
                   Logger.info(e);
                } else if ( "NAO ENCONTREI FAIXA COBRANCA".equals(e.getMessage()) ) {                   
                   //Esse Erro eh esperado no caso de nao existir a faixa no arquivo xml que configura o batch.
                   Logger.info(e);
                } else {
                   throw e;
                }
            }
            return tabelaPrecoBSRVO;
    }

	/**
	 * Obtem o parametro faixa para busca na tabela de precos.
	 * @param quantidade
	 * @param tp
	 * @return
	 * @throws Exception
	 */
    protected String getFaixa(BigDecimal quantidade, String tp) throws Exception {
        String ret = null;
        Faixa faixa = null;
        Collection faixaColec = tabelaPreco.values();		
        for (Iterator it=faixaColec.iterator(); it.hasNext(); ) {
            faixa = (Faixa)it.next();
            if(tp.compareTo(faixa.getTp())==0){
               if(faixa.getMaximo()==0l){
                   if( quantidade.doubleValue() >= faixa.getMinimo() ){
                      ret = faixa.getCodigo();
                      break;
                   }				
               }else{
                   if( quantidade.doubleValue() >= faixa.getMinimo() &&
                        quantidade.doubleValue() <= faixa.getMaximo()){
                        ret = faixa.getCodigo();
                        break;
                   }				
               }
            }
        }
        if(ret==null){
           Logger.error("FAIXA DE COBRANCA NAO EXISTE PARA QUANTIDADE = "+quantidade.doubleValue());			
           throw new Exception("NAO ENCONTREI FAIXA COBRANCA");			
        }
        return ret;
    }
	
    /**
     * Grava na tabela CONFERE_COBRANCA (cobranca de volume).
     * @param conta
     * @param valor
     * @param dataD0
     * @param criterio
     * @param subcriterio
     * @param grupo
     * @param tp
     * @param intraExtra
     * @throws Exception
     */
	protected void escreveConfereCobrancaVolume(String conta,
                                                BigDecimal valor,
                                                Date dataD0,
                                                String criterio,
                                                String subcriterio,
                                                String grupo,
                                                String tp,
                                                String intraExtra) throws Exception {

		    BigDecimal valorMedio = valor.divide(new BigDecimal(numDiasUteis), 1);		
		    if(ZERO.compareTo( valorMedio ) < 0 ){		
               TabelaPrecoBSRVO tabelaPrecoBSRVO = obterPreco(valorMedio,criterio,subcriterio,grupo,tp);
               if(tabelaPrecoBSRVO!=null) {
            	   String contaCobranca = obterContaCobranca(conta);            	
            	   if(contaCobranca!=null){
            	       insertConfereCobranca(tabelaPrecoBSRVO.getNumIdValorItemCobranca(),
            			                     conta,
                                             dataD0,
                                             contaCobranca,
                                             new BigDecimal("0"),
                                             valorMedio);            	
            	   }
               }
		    }
    }
    
	/**
     * Grava na tabela CONFERE_COBRANCA. 
	 * @param conta
	 * @param quantidade
	 * @param dataD0
	 * @param criterio
	 * @param subcriterio
	 * @param grupo
	 * @throws Exception
	 */
	protected void escreveConfereCobranca(String conta,
                                          BigDecimal quantidade,
                                          Date dataD0,
                                          String criterio,
                                          String subcriterio,
                                          String grupo
                                          ) throws Exception {
		
	       if(ZERO.compareTo( quantidade ) < 0 ){		   
		      TabelaPrecoBSRVO tabelaPrecoBSRVO = obterValorItemCobranca(criterio, 
                                                                         subcriterio, 
                                                                         grupo, 
                                                                         "FX1", 
                                                                         null);
              if(tabelaPrecoBSRVO!=null) {
           	      String contaCobranca = obterContaCobranca(conta);            	
        	      if(contaCobranca!=null){
        	          insertConfereCobranca(tabelaPrecoBSRVO.getNumIdValorItemCobranca(),
        			                        conta,
                                            dataD0,
                                            contaCobranca,
                                            quantidade,
                                            tabelaPrecoBSRVO.getValCobranca());            	
        	      }
              }
	       }
    }
	
	/**
	 * Obtem a data batch.
	 * @return
	 * @throws Exception
	 */
    protected Date obterDataBatchD0_C21() throws Exception {
    	return obterDataBatch(SISTEMA21, OBJSERV_SISTEMA21, D0);
    }
    
    /**
     * Abre a conexao com o banco.
     * @throws Exception
     */
    protected void abrirConexao() throws Exception {
        try {
            conn = obtemConexao();
        } catch (Exception e) {
        	Logger.error(e);        	
            throw e;
        }
    }

    /**
     * Fecha a conexao com o banco.
     * @throws Exception
     */
    protected void fecharConexao() throws Exception {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) {
        	Logger.error(e);        	
            throw e;
        }
    }
    
    /**
     * Obter data do controle operacional (batch).
     * @param sistema
     * @param objsrv
     * @param ordem
     * @return
     * @throws Exception
     */
    protected Date obterDataBatch(String sistema,
    		                   String objsrv,
    		                   String ordem) throws Exception {

    	PreparedStatement stmt = null;
        Date dataDO = null;

        try {
            stmt = conn.prepareStatement(consultaDataOperacionalBatch());
            stmt.setString(1, sistema);
            stmt.setString(2, ordem);            
            stmt.setString(3, objsrv);            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dataDO = rs.getDate("dat_ctl_oper");
            } else {
            	Logger.error("NAO ENCONTREI D"+ordem+" EM CONTROLE OPERACIONAL");
                throw new Exception("NAO ENCONTREI D"+ordem+" EM CONTROLE OPERACIONAL");
            }
        } catch (Exception e) {
        	Logger.error(e);        	
            throw e;
        } finally {
            if (stmt != null) stmt.close();
        }

        return dataDO;
    }
	
    /**
     * Obter lei de formacao para leitura de arquivos.
     * @param arquivo
     * @return
     */
	protected String obterFormacaoArquivo(String arquivo){
		String raiz = "";
		ResourceBundle rs = null;
		try {
			rs = ResourceBundle.getBundle("batch"); 
			raiz = rs.getString("relatorio"); 
		} catch (Exception e) {
			Logger.info(this, "DIRETORIO RAIZ NAO ENCONTRADO. PROPRIEDADE relatorio NO batch.properties");
		}
		return (raiz + File.separator + arquivo);		
	}

	/**
     * Obter lei de formacao para leitura de arquivos XML de configuracao dos batchs. 
	 * @param arquivo
	 * @return
	 */
	protected String obterFormacaoXML(String arquivo){
		String raiz = "";
		ResourceBundle rs = null;
		try {
			rs = ResourceBundle.getBundle("batch"); 
			raiz = rs.getString("xml.dir"); 
		} catch (Exception e) {
			Logger.info(this, "DIRETORIO RAIZ NAO ENCONTRADO. PROPRIEDADE xml.dir NO batch.properties");
		}
		return (raiz + File.separator + arquivo);		
	}

	/**
	 * Obter parametros do arquivo XML de configuracao dos batchs.
	 * @param nomeArquivo
	 * @return
	 * @throws Exception
	 */
	protected DescricaoBatch obterXmlParametros(String nomeArquivo) throws Exception {
		DescricaoBatch descricaoBatch=null; 		
		StringBuffer xml = new StringBuffer();
		try {
			BufferedReader bufferedreader = new BufferedReader(new FileReader(nomeArquivo));
			String c;
			while ((c = bufferedreader.readLine()) != null) {
				xml.append(c);
			}
			bufferedreader.close();				
		} catch (IOException e) {
			Logger.error(e);
			Logger.error("Erro ao ler arquivo=" + nomeArquivo);
			throw e;
		}
		try {
			descricaoBatch = (DescricaoBatch) ConversorXML.fromXML(xml.toString()); 
		} catch (Exception e) {
			Logger.error(e);
			Logger.error("Arquivo invalido " + nomeArquivo);
			throw e;
		}
		return descricaoBatch;
	}	

	/**
	 * Inicaliza atributos da instancia.
	 * @param descricaoBatch
	 */
	protected void carregaParametrosEntradaXML(DescricaoBatch descricaoBatch){
		for (Iterator iter = descricaoBatch.getListaAtributosEntrada().iterator(); iter.hasNext();) {
			List linha = (List) iter.next();
			for (Iterator iterator = linha.iterator(); iterator.hasNext();) {
				Atributo atributo = (Atributo) iterator.next();
				String str = DescricaoAtributo.obterDescricao(atributo);
				Logger.info("parametros entrada xml: "+str);
				int pos = str.indexOf(PATH_ARQUIVO);
				if(pos>=0){
					pos = str.indexOf("=");
					pathArquivo = str.substring(++pos);
				}
				pos = str.indexOf(NOME_ARQUIVO);
				if(pos>=0){
					if(arquivos==null){
						arquivos = new ArrayList();
					}
					pos = str.indexOf("=");
					arquivos.add(str.substring(++pos));
				}				
				pos = str.indexOf(TABELA_PRECO);
				if(pos>=0){
					if(tabelaPreco==null){
						tabelaPreco = new Hashtable();
					}
					pos = str.indexOf("=");
					montaTabelaPreco(str.substring(++pos));
				}
				pos = str.indexOf(OPERACAO_COMPROMISSADA);
				if(pos>=0){
					pos = str.indexOf("=");
					listaDeOperacoesCompromissadas = str.substring(++pos);
				}
			}
		}			
	}
	
	/**
	 * Lista os parametros do arquivo XML de configuracao do batch.
	 * @param descricaoBatch
	 */
	protected void listaParametrosEntradaXML(DescricaoBatch descricaoBatch){
		for (Iterator iter = descricaoBatch.getListaAtributosEntrada().iterator(); iter.hasNext();) {
			List linha = (List) iter.next();
			for (Iterator iterator = linha.iterator(); iterator.hasNext();) {
				Atributo atributo = (Atributo) iterator.next();
				String str = DescricaoAtributo.obterDescricao(atributo);
				Logger.info("parametros entrada xml: "+str);
			}
		}			
	}
	
	/**
     * Obter SQL para conssulta da data do controle operacional (batch). 
	 * @return
	 */
    protected String consultaDataOperacionalBatch(){
        StringBuffer sql = new StringBuffer();
        sql.append("select co.dat_ctl_oper ");
        sql.append(" from cetip.controle_operacional co ");
        sql.append(" where co.num_sistema=? and ");
        sql.append(" co.num_ordem=? and");
        sql.append(" co.num_id_objeto_servico=?");
        return sql.toString();    	
    }

    /**
     * Insere na tabela CONFERE_COBRANCA.
     * @param NUM_ID_VALOR_ITEM_COBRANCA
     * @param NUM_CONTA_PARTICIPANTE
     * @param DAT_COBRANCA
     * @param NUM_CONTA_COBRANCA
     * @param QTD_COBRANCA
     * @param VAL_COBRANCA
     * @return
     * @throws Exception
     */
    protected boolean insertConfereCobranca(long NUM_ID_VALOR_ITEM_COBRANCA,
    		                                String NUM_CONTA_PARTICIPANTE,
    		                                Date DAT_COBRANCA,
    		                                String NUM_CONTA_COBRANCA,
    		                                BigDecimal QTD_COBRANCA,
    		                                BigDecimal VAL_COBRANCA) throws Exception {

    	    PreparedStatement stmt = null;
            boolean ret=false;

            try {

               stmt = conn.prepareStatement(insertConfereCobranca());
               stmt.setLong(1, NUM_ID_VALOR_ITEM_COBRANCA);
               stmt.setString(2, NUM_CONTA_PARTICIPANTE);
               stmt.setDate(3, new java.sql.Date(DAT_COBRANCA.getTime()));
               stmt.setString(4, NUM_CONTA_COBRANCA);
               stmt.setBigDecimal(5, QTD_COBRANCA);
               stmt.setBigDecimal(6, VAL_COBRANCA);
               ret = stmt.execute();
            } catch (Exception e) {
               Logger.error(e);        	
               throw e;
            } finally {
               if (stmt != null) stmt.close();
            }
            return ret;
    }
    
    /**
     * Obtem SQL para inserir na tabela CONFERE_COBRANCA.
     * @return
     */
    protected String insertConfereCobranca(){
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO cetip.CONFERE_COBRANCA ");
        sql.append(" (NUM_ID_CONFERE_COBRANCA,NUM_ID_VALOR_ITEM_COBRANCA,NUM_CONTA_PARTICIPANTE,DAT_COBRANCA,NUM_CONTA_COBRANCA,QTD_COBRANCA,VAL_COBRANCA) ");
        sql.append(" VALUES ");
        sql.append(" (CETIP.s_confere_cobranca.nextval,?,?,?,?,?,?)");
        return sql.toString();
    }
    
    /**
     * Obtem os arquivos de entrada.
     * @return
     */
	protected List getArquivos() {
		return arquivos;
	}

	/**
	 * Obtem o caminho dos arquivos de entrada.
	 * @return
	 */
	protected String getPathArquivo() {
		return pathArquivo;
	}
	
	/**
	 * Obtem o numero de dias uteis para media de custodia.
	 * @return
	 */
	protected String getNumDiasUteis() {
		return numDiasUteis;
	}

	/**
	 * Inicializa o numero de dias uteis para media de custodia.
	 * @param numDiasUteis
	 */
	protected void setNumDiasUteis(String numDiasUteis) {
		this.numDiasUteis = numDiasUteis;
	}
	
	protected String getListaDeOperacoesCompromissadas(){
		return listaDeOperacoesCompromissadas;
	}
	
	protected void setListaDeOperacoesCompromissadas(String listaDeOperacoesCompromissadas){
		this.listaDeOperacoesCompromissadas = listaDeOperacoesCompromissadas;
	}
	
	/**
	 * Monta uma data.
	 * @param dia
	 * @param mes
	 * @param ano
	 * @return
	 * @throws Exception
	 */
    protected Calendar obterData(String dia, String mes, String ano) throws Exception {
        try {
            Calendar retorno = Calendar.getInstance();
            retorno.clear();
            retorno.setLenient(false);
			retorno.set(Calendar.YEAR,Integer.parseInt(ano));
            retorno.set(Calendar.MONTH,Integer.parseInt(mes) - 1);
			retorno.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dia));
			Logger.info(retorno.getTime());
            return retorno;
        } catch (Exception e) {
            throw new Exception("DATA INVALIDA PARA DIA = "+dia+", MES = "+ mes + " E ANO = "+ano);
        }
    }
    
    /**
     * Obtem uma data a partir de um string.
     * @param strData
     * @return
     * @throws Exception
     */
    protected Date obterData(String strData) throws Exception {
    	Date dataD0 = null;
		if( strData != null && strData.trim().length() == 8 ){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");				
	        Calendar cal = obterData(strData.substring(6,8), strData.substring(4,6), strData.substring(0,4));
			String dataFormatada = sdf.format(cal.getTime());
			dataD0 = new java.sql.Date(sdf.parse(dataFormatada).getTime());
		    Logger.info("dataD0 informada como parametro = "+dataD0);				
		} 
        return dataD0;
    }

    /**
     * Obtem a data D0.
     * @return
     */
	protected Date getDataD0() {
		return dataD0;
	}

	/**
	 * Inicializa da data D0.
	 * @param dataD0
	 */
	protected void setDataD0(Date dataD0) {
		this.dataD0 = dataD0;
	}
	
	/**
	 * Obtem o VO com os dados de um preco.
	 * @param codCriterioCobranca
	 * @param codigoSubCriterioCobranca
	 * @param codGrupoCobranca
	 * @param codFaixaCobranca
	 * @param indIntraGrupoEconomico
	 * @return
	 * @throws Exception
	 */
    protected TabelaPrecoBSRVO obterValorItemCobranca(String codCriterioCobranca,
                                                   String codigoSubCriterioCobranca,
                                                   String codGrupoCobranca,
                                                   String codFaixaCobranca,
                                                   String indIntraGrupoEconomico) throws Exception {

    	PreparedStatement stmt = null;
        TabelaPrecoBSRVO tabelaPrecoBSRVO = null;

        try {
            stmt = conn.prepareStatement(consultaValorItemCobranca(codCriterioCobranca,
                                                                                     codigoSubCriterioCobranca,
                                                                                     codGrupoCobranca,
                                                                                     codFaixaCobranca,
                                                                                     indIntraGrupoEconomico));

            stmt.setString(1, codCriterioCobranca);            
            stmt.setString(2, codigoSubCriterioCobranca);
            int idx = 2;
            if (codGrupoCobranca!=null){
               idx++;	
               stmt.setString(idx, codGrupoCobranca);               
            }
            if (codFaixaCobranca!=null){
               idx++;	
               stmt.setString(idx, codFaixaCobranca);               
            }
            if (indIntraGrupoEconomico!=null){
               idx++;	
               stmt.setString(idx, indIntraGrupoEconomico);		
            }
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	tabelaPrecoBSRVO = new TabelaPrecoBSRVO();
            	tabelaPrecoBSRVO.setNumIdItemCobranca(rs.getLong("num_id_item_cobranca"));
            	tabelaPrecoBSRVO.setNomeItemCobranca(rs.getString("nom_item_cobranca"));
            	tabelaPrecoBSRVO.setCodCriterioCobranca(rs.getString("cod_criterio_cobranca"));
            	tabelaPrecoBSRVO.setCodigoSubCriterioCobranca(rs.getString("cod_sub_criterio_cobranca"));
            	tabelaPrecoBSRVO.setCodGrupoCobranca(rs.getString("cod_grupo_cobranca"));
            	tabelaPrecoBSRVO.setIndIntraGrupoEconomico(rs.getString("ind_intra_grupo_economico"));
            	tabelaPrecoBSRVO.setCodTipoCobranca(rs.getString("cod_tipo_cobranca"));
            	tabelaPrecoBSRVO.setIndLancamentoManual(rs.getString("ind_lancamento_manual"));
            	tabelaPrecoBSRVO.setNumIdValorItemCobranca(rs.getLong("num_id_valor_item_cobranca"));
            	tabelaPrecoBSRVO.setCodFaixaCobranca(rs.getString("cod_faixa_cobranca"));
            	tabelaPrecoBSRVO.setValCobranca(rs.getBigDecimal("val_cobranca"));
            	tabelaPrecoBSRVO.setNomDetalheCobranca(rs.getString("nom_detalhe_cobranca"));
            	tabelaPrecoBSRVO.setCodControle(rs.getString("cod_controle"));
            	tabelaPrecoBSRVO.setNomeCriterioCobranca(rs.getString("nom_criterio_cobranca"));
            } else {
            	Logger.error("NAO ENCONTREI VALOR ITEM COBRANCA");
                throw new Exception("NAO ENCONTREI VALOR ITEM COBRANCA");
            }
        } catch (Exception e) {
        	Logger.error(e);        	
            throw e;
        } finally {
            if (stmt != null) stmt.close();
        }
            
        return tabelaPrecoBSRVO;            

    } 
	
    /**
     * Obtem o SQL para consulta da tabela de precos.
     * @param codCriterioCobranca
     * @param codigoSubCriterioCobranca
     * @param codGrupoCobranca
     * @param codFaixaCobranca
     * @param indIntraGrupoEconomico
     * @return
     */
    protected String consultaValorItemCobranca(String codCriterioCobranca,
                                             String codigoSubCriterioCobranca,
                                             String codGrupoCobranca,
                                             String codFaixaCobranca,
                                             String indIntraGrupoEconomico){
    	
        StringBuffer query = new StringBuffer();		
        query.append("select * FROM cetip.V_VALOR_ITEM_COBRANCA item ");
        query.append(" where item.COD_CRITERIO_COBRANCA=? " );
            
        query.append(" and item.COD_SUB_CRITERIO_COBRANCA=? " );

        if (codGrupoCobranca==null){
           query.append(" and item.COD_GRUPO_COBRANCA is null " );
        } else {
           query.append(" and item.COD_GRUPO_COBRANCA=? " );
        }

        if (codFaixaCobranca==null){
           query.append(" and item.COD_FAIXA_COBRANCA is null " );
        } else {
           query.append(" and item.COD_FAIXA_COBRANCA=? " );
        }

        if (indIntraGrupoEconomico==null){
           query.append(" and item.IND_INTRA_GRUPO_ECONOMICO is null " );
        }else{
           query.append(" and item.IND_INTRA_GRUPO_ECONOMICO=? " );            	
        }

        Logger.info("consultaValorItemCobranca: "+query.toString());
        
        return query.toString();
    	
    }

    /**
     * Obtem a conta de cobranca de um participante.
     * @param numConta
     * @return
     * @throws Exception
     */
	protected String obterContaCobranca(String numConta) throws Exception {
		PreparedStatement stmt = null;
        String idContaCobranca = null;
        try {
            stmt = conn.prepareStatement(consultaContaParticipante());
            Logger.info("conta participante: "+numConta);
            stmt.setString(1, numConta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	idContaCobranca = rs.getString("NUM_CONTA_COBRANCA");
            } else {
            	Logger.error("NAO ENCONTREI CONTA COBRANCA PARA O ID = "+numConta);
                throw new Exception("NAO ENCONTREI CONTA COBRANCA");
            }
        } catch (Exception e) {
        	Logger.error(e);        	
            throw e;
        } finally {
            if (stmt != null) stmt.close();
        }
        return idContaCobranca;         
    }
    
	/**
	 * Obtem o SQL para consulta da conta de cobranca do participante.
	 * @return
	 */
	protected String consultaContaParticipante(){
		return "select * from cetip.CONTA_PARTICIPANTE conta WHERE conta.num_conta_participante = ? ";		
	}
    
	protected String obterGrupoEconomicoCache(String numConta) throws Exception {
        String grupo = GrupoEconomico.instancia().procuraGrupoEconomico(numConta);
        if(grupo==null){
        	grupo = obterGrupoEconomico(numConta);
        	GrupoEconomico.instancia().atualizaCacheDoGrupo(numConta, grupo);
        }
        return grupo;
	}
	
    /**
     * Obtem grupo economico de um participante.
     * @param numConta
     * @return
     * @throws Exception
     */
	protected String obterGrupoEconomico(String numConta) throws Exception {
		PreparedStatement stmt = null;
        String idContaCobranca = "null";
        try {
            stmt = conn.prepareStatement(consultaGrupoEconomico());
            stmt.setString(1, numConta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	idContaCobranca = rs.getString("NOM_SIMPLIFICADO_ENTIDADE");
            //} else {
            //	Logger.error("NAO ENCONTREI PARTICIPANTE O ID = "+numConta);
            //    throw new Exception("NAO ENCONTREI PARTICIPANTE");
            }
        } catch (Exception e) {
        	Logger.error(e);        	
            throw e;
        } finally {
            if (stmt != null) stmt.close();
        }
        return idContaCobranca;         
    }
    
	/**
	 * Obtem o SQL para consulta da conta de cobranca do participante.
	 * @return
	 */
	protected String consultaGrupoEconomico(){
	   StringBuffer sql = new StringBuffer();	
	   sql.append(" select  ");
	   sql.append(" entidadedo0_.nom_simplificado_entidade ");
	   sql.append(" from  ");
       sql.append(" CETIP.ENTIDADE entidadedo0_, ");
       sql.append(" CETIP.RELACAO relacaodo1_ ");
       sql.append(" where ");
	   sql.append(" relacaodo1_.NUM_ID_TIPO_RELACAO=5 ");
	   sql.append(" and relacaodo1_.IND_EXCLUIDO='N' ");
	   sql.append(" and relacaodo1_.NUM_ID_ENTIDADE=entidadedo0_.NUM_ID_ENTIDADE ");
	   sql.append(" and relacaodo1_.NUM_ID_ENTIDADE_PARA=(select ");
       sql.append(" contaparti0_.num_id_entidade ");
	   sql.append(" from ");
	   sql.append(" CETIP.CONTA_PARTICIPANTE contaparti0_ ");
	   sql.append(" where contaparti0_.COD_CONTA_PARTICIPANTE=?) ");
       return sql.toString();
	}	

	protected String contaCetipComMascara(String conta) throws Exception {
		StringBuffer sb = new StringBuffer();
		try {
		    sb.append(conta.substring(0,5));
		    sb.append(".");
		    sb.append(conta.substring(5,7));
		    sb.append("-");		
		    sb.append(conta.substring(7,8));
		} catch (IndexOutOfBoundsException ex){
			throw new Exception("CONTA_CETIP_INVALIDA: "+conta);
		}
		return sb.toString();
	}
}