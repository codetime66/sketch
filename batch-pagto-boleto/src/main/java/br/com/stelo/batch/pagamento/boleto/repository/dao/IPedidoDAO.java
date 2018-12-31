package br.com.stelo.batch.pagamento.boleto.repository.dao;

import java.util.List;

import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;

public interface IPedidoDAO {

	List<PedidoEntity> getPedidos(Long CD_PDIDO);

	PedidoEntity getPedido(Long CD_PDIDO);

}
