package br.com.stelo.batch.pagamento.boleto.service;

import br.com.stelo.batch.pagamento.boleto.model.RegistroArquivoTiff;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PagamentoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;

public interface ILayoutTifService {

	RegistroArquivoTiff getRegistroArquivoTiff(PagamentoEntity pagamentoEntity, PedidoEntity pedidoEntity,
			Integer numSequencia, String nomeFantasia);

}
