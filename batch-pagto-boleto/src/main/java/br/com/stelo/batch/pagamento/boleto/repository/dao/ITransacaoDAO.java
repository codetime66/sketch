package br.com.stelo.batch.pagamento.boleto.repository.dao;

import br.com.stelo.batch.pagamento.boleto.repository.entity.TransacaoEntity;

public interface ITransacaoDAO {

	void inserir(TransacaoEntity transacaoEntity);

}
