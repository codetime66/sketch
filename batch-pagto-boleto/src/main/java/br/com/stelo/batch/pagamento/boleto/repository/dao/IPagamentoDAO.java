package br.com.stelo.batch.pagamento.boleto.repository.dao;

import br.com.stelo.batch.pagamento.boleto.repository.entity.PagamentoEntity;

public interface IPagamentoDAO {

	public PagamentoEntity getPagamento(final Long idStelo, final Long codigoPedido);

}
