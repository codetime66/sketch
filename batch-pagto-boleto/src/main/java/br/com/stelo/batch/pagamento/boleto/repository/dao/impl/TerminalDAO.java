package br.com.stelo.batch.pagamento.boleto.repository.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.stelo.batch.pagamento.boleto.repository.dao.ITerminalDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.TerminalEntity;

@Repository
public class TerminalDAO implements ITerminalDAO {

	private static final Logger log = LoggerFactory.getLogger(TerminalDAO.class);
	
	@Qualifier("CDTODS")
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TerminalDAO(@Qualifier("CDTODS") DataSource dataSourceCdto) {
		this.jdbcTemplate = new JdbcTemplate(dataSourceCdto);
	}
	
	@Override
	@Transactional
	public TerminalEntity getTerminal(Long codigoVendedor, Long idTecnologia) {
		log.info("TerminalDAO.getTerminal(): codigoVendedor="+codigoVendedor+" , idTecnologia="+idTecnologia);
		
		String query = "SELECT NU_TERM from USR_CADU.tb_term where ID_STELO = ? AND ID_TECNO=?";
		
		List<TerminalEntity> vendas = jdbcTemplate.query(query,
				new Object[] { codigoVendedor, idTecnologia },
				new BeanPropertyRowMapper<TerminalEntity>(TerminalEntity.class));
		TerminalEntity terminalEntity = null;
		if (vendas!=null && !vendas.isEmpty()) {
			terminalEntity = vendas.get(0);
		}

		return terminalEntity;
	}	
}
