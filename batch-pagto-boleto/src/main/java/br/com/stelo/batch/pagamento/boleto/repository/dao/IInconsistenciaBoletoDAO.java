package br.com.stelo.batch.pagamento.boleto.repository.dao;

import br.com.stelo.batch.pagamento.boleto.repository.entity.InconsistenciaBoletoEntity;

public interface IInconsistenciaBoletoDAO {

	void inserir(InconsistenciaBoletoEntity inconsistenciaBoleto);

	int inserirObterPK(InconsistenciaBoletoEntity inconsistenciaBoleto);


}
