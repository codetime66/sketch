package br.com.stelo.batch.pagamento.boleto.repository.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.stelo.batch.pagamento.boleto.repository.dao.IVendaDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.VendaEntity;

@Repository
public class VendaDAO implements IVendaDAO {

	private static final Logger log = LoggerFactory.getLogger(VendaDAO.class);

	@Qualifier("TRNGDS")
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public VendaDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public VendaEntity getVenda(final Long idStelo, final Long codigoPedido) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("venda.CD_VDDOR, ");
		sql.append("venda.CD_PDIDO, ");
		sql.append("venda.DT_INCL_VDA, ");
		sql.append("venda.CD_PDIDO_ESTBL_COML, ");
		sql.append("venda.DS_VDA_ESTBL_COML, ");
		sql.append("venda.VR_TOT_VDA, ");
		sql.append("venda.IC_RETRD_LJ, ");
		sql.append("venda.VR_TOT_DESC_PDIDO ");
        sql.append(" from ");
        sql.append("USR_GEPD.TB_VDA venda ");
		sql.append(" where ");
		sql.append("venda.CD_VDDOR=? and venda.CD_PDIDO=?");
		
		log.info("###VendaDAO.getVenda: ");
		Object[] params = {idStelo, codigoPedido};
		List<VendaEntity> vendas = jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<VendaEntity>(VendaEntity.class));
		VendaEntity venda = null;
		if (vendas!=null && !vendas.isEmpty()) {
			venda = vendas.get(0);
		}

		return venda;
	}

}