package br.com.stelo.batch.pagamento.boleto.service;

import br.com.stelo.batch.pagamento.boleto.model.PagamentoMatera;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PagamentoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.VendaEntity;

public interface ITransacaoService {

	void processaPagamentoAprovado(PagamentoMatera pagamentoMatera, PagamentoEntity pagamentoEntity,
			VendaEntity vendaEntity, PedidoEntity pedidoEntity) throws Exception;

}
