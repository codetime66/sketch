package br.com.stelo.batch.pagamento.boleto.repository.dao;

public interface IBatchExecutorDAO {

	Integer getSequencial();

	void atualizaSequencial(int sequencial);

}
