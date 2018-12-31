package br.com.stelo.batch.pagamento.boleto.repository.dao.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.stelo.batch.pagamento.boleto.repository.dao.IBatchExecutorDAO;

@Repository
public class BatchExecutorDAO implements IBatchExecutorDAO {
	private static final Logger log = LoggerFactory.getLogger(BatchExecutorDAO.class);

	private static final int ID_JOB_PAGAMENTO_BOLETO = 1;

	@Qualifier("TRNGDS")
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BatchExecutorDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public Integer getSequencial() {
		log.info("###BatchExecutorDAO.getSequencial: ");

		String sql = "SELECT CD_SEQ_ULT_EXCUC FROM USR_GEPD.TB_CTRL_BATCH_EXCUC where ID_BATCH=?";

		Number temp = jdbcTemplate.queryForObject(sql, new Object[] { ID_JOB_PAGAMENTO_BOLETO }, Number.class);
		if (temp == null) {
			return 1;
		} else {
			return temp.intValue();
		}
	}

	@Override
	@Transactional
	public void atualizaSequencial(int sequencial) {

		String sql = "UPDATE USR_GEPD.TB_CTRL_BATCH_EXCUC SET CD_SEQ_ULT_EXCUC=?, DT_ULT_EXCUC=? WHERE ID_BATCH = ?";

		this.jdbcTemplate.update(sql, new Object[] { sequencial, new Date(), ID_JOB_PAGAMENTO_BOLETO });
	}

}
