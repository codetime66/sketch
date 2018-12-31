package br.com.stelo.batch.pagamento.boleto.repository.dao;

import br.com.stelo.batch.pagamento.boleto.repository.entity.VendaEntity;

public interface IVendaDAO {

	public VendaEntity getVenda(final Long idStelo, final Long codigoPedido);

}
