package br.com.stelo.batch.pagamento.boleto.repository.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.stelo.batch.pagamento.boleto.repository.dao.IInconsistenciaBoletoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.InconsistenciaBoletoEntity;

@Repository
public class InconsistenciaBoletoDAO implements IInconsistenciaBoletoDAO {

	private static final Logger log = LoggerFactory.getLogger(InconsistenciaBoletoDAO.class);

	@Qualifier("TRNGDS")
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public InconsistenciaBoletoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public int inserirObterPK(InconsistenciaBoletoEntity inconsistenciaBoleto) {
		log.info("### InconsistenciaBoletoDAO.inserirObterPK:");
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String id_column = "ID_BLETO_INCOT";

		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(getInsertSql(), new String[] { id_column });
			ps.setLong(1, inconsistenciaBoleto.getNU_PDIDO());
			ps.setString(2, inconsistenciaBoleto.getNU_PDIDO_EC());
			ps.setLong(3, inconsistenciaBoleto.getCPF());
			ps.setString(4, inconsistenciaBoleto.getEMAIL_COMPR());
			ps.setLong(5, inconsistenciaBoleto.getCD_VDDOR());
			ps.setBigDecimal(6, inconsistenciaBoleto.getVR_PDIDO());
			ps.setBigDecimal(7, inconsistenciaBoleto.getVR_BLETO_PG());
			ps.setDate(8,  new java.sql.Date(inconsistenciaBoleto.getDT_PGTO().getTime()));
			ps.setDate(9,  new java.sql.Date(inconsistenciaBoleto.getDT_VCTO().getTime()));
			ps.setString(10, inconsistenciaBoleto.getCD_ORIGE());
			ps.setLong(11, inconsistenciaBoleto.getCD_FONTE_REJEI());
			ps.setLong(12, inconsistenciaBoleto.getCD_MTV_REJEI());
			ps.setLong(13, inconsistenciaBoleto.getCD_SUB_MTV_REJEI());
			ps.setString(14, inconsistenciaBoleto.getDS_INCOT());
			return ps;
		}, keyHolder);
		BigDecimal id = (BigDecimal) keyHolder.getKeys().get(id_column);
		return id.intValue();
	}
	
	@Override
	@Transactional
	public void inserir(InconsistenciaBoletoEntity inconsistenciaBoleto) {
		log.info("### InconsistenciaBoletoDAO.inserir:");
		
		String id_column = "ID_BLETO_INCOT";

		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(getInsertSql(), new String[] { id_column });
			ps.setLong(1, inconsistenciaBoleto.getNU_PDIDO());
			ps.setString(2, inconsistenciaBoleto.getNU_PDIDO_EC());
			ps.setLong(3, inconsistenciaBoleto.getCPF());
			ps.setString(4, inconsistenciaBoleto.getEMAIL_COMPR());
			ps.setLong(5, inconsistenciaBoleto.getCD_VDDOR());
			ps.setBigDecimal(6, inconsistenciaBoleto.getVR_PDIDO());
			ps.setBigDecimal(7, inconsistenciaBoleto.getVR_BLETO_PG());
			ps.setDate(8,  new java.sql.Date(inconsistenciaBoleto.getDT_PGTO().getTime()));
			ps.setDate(9,  new java.sql.Date(inconsistenciaBoleto.getDT_VCTO().getTime()));
			ps.setString(10, inconsistenciaBoleto.getCD_ORIGE());
			ps.setLong(11, inconsistenciaBoleto.getCD_FONTE_REJEI());
			ps.setLong(12, inconsistenciaBoleto.getCD_MTV_REJEI());
			ps.setLong(13, inconsistenciaBoleto.getCD_SUB_MTV_REJEI());
			ps.setString(14, inconsistenciaBoleto.getDS_INCOT());
			return ps;
		});

	}

	private String getInsertSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO USR_GEPD.TB_BLETO_INCOT ");
		sql.append("(ID_BLETO_INCOT, ");
		sql.append("NU_PDIDO, ");
		sql.append("NU_PDIDO_EC, ");
		sql.append("CPF, ");
		sql.append("EMAIL_COMPR, ");
		sql.append("CD_VDDOR, ");
		sql.append("VR_PDIDO, ");
		sql.append("VR_BLETO_PG, ");
		sql.append("DT_PGTO, ");
		sql.append("DT_VCTO, ");
		sql.append("CD_ORIGE, ");
		sql.append("CD_FONTE_REJEI, ");
		sql.append("CD_MTV_REJEI, ");
		sql.append("CD_SUB_MTV_REJEI, ");
		sql.append("DS_INCOT) ");
		sql.append(" VALUES ");
		sql.append("(USR_GEPD.SQ_ID_BLETO_INCOT.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return sql.toString();
	}
}