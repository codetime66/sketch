package br.com.stelo.batch.pagamento.boleto.service;

import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;

public interface ITerminalService {

	Long buscarTerminal(PedidoEntity pedidoEntity) throws Exception;

}
