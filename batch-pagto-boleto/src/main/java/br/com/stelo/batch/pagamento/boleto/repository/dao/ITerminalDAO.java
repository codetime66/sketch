package br.com.stelo.batch.pagamento.boleto.repository.dao;

import br.com.stelo.batch.pagamento.boleto.repository.entity.TerminalEntity;

public interface ITerminalDAO {
	TerminalEntity getTerminal(Long codigoVendedor, Long idTecnologia) throws Exception;
}
