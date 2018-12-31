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

import br.com.stelo.batch.pagamento.boleto.repository.dao.IPedidoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;

@Repository
public class PedidoDAO implements IPedidoDAO {

	private static final Logger log = LoggerFactory.getLogger(PedidoDAO.class);

	@Qualifier("TRNGDS")
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PedidoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public List<PedidoEntity> getPedidos(Long CD_PDIDO) {
		log.info("###PedidoDAO.getPedidos: ");
		Object[] params = {CD_PDIDO};
		return jdbcTemplate.query(getSql(), params, new BeanPropertyRowMapper<PedidoEntity>(PedidoEntity.class));
	}

	@Override
	@Transactional
	public PedidoEntity getPedido(Long CD_PDIDO) {
		log.info("###PedidoDAO.getPedidos: ");
		Object[] params = {CD_PDIDO};
		List<PedidoEntity> pedidos = jdbcTemplate.query(getSql(), params, new BeanPropertyRowMapper<PedidoEntity>(PedidoEntity.class));
		PedidoEntity pedido = null;
		if(pedidos!=null && !pedidos.isEmpty()) {
			pedido = pedidos.get(0);
		}
		return pedido;
	}
	
	private String getSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("ped.CD_PDIDO, ");
		sql.append("ped.CD_STTUS_PDIDO, ");
		sql.append("ped.CD_PLATF, ");
		sql.append("ped.DT_INCL, ");
		sql.append("ped.CD_VDDOR_ROOT_PDIDO, ");
		sql.append("ped.CD_ORIGEM_PRODUTO, ");
		sql.append("ped.CD_COMPR, ");
		sql.append("ped.NU_DCTO_COMPR, ");
		sql.append("ped.NM_COMPR, ");
		sql.append("ped.DS_EMAIL_COMPR, ");
		sql.append("ped.NU_FONE_COMPR, ");
		sql.append("ped.DT_NASC, ");
		sql.append("ped.SG_GNRO, ");
		sql.append("ped.ID_TP_FONE, ");
		sql.append("ped.DT_ALT, ");
		sql.append("ped.USUAR_ALT, ");
		sql.append("ped.USUAR_INCL, ");
		sql.append("ped.CD_CANAL_ORIGEM, ");
		sql.append("ped.DT_CAD_COMPR_EC, ");
		sql.append("ped.NU_REFT, ");
		sql.append("ped.VR_TX_JURO_PCELA, ");
		sql.append("ped.VR_REFT, ");
		sql.append("ped.EN_NOTIF_EC, ");
		sql.append("ped.NU_NSU_STELO, ");
		sql.append("ped.CD_TID_STELO, ");
		sql.append("ped.NM_FANTS, ");
		sql.append("ped.NM_RESP, ");
		sql.append("ped.TPO_COMPR_PSSOA, ");
		sql.append("ped.CD_STTUS_SOLCT_MAQNA, ");
		sql.append("ped.IC_CAD_COPLT, ");
		sql.append("ped.ID_STTUS_PROCM_TRANS_TIF, ");
		sql.append("ped.NU_INSCR_EST, ");
		sql.append("ped.NM_RZ_SCIAL, ");
		sql.append("ped.NM_URL_LJ_CMPLTO, ");
		sql.append("ped.ID_TRANS_CANAL ");
		sql.append(" from USR_GEPD.TB_PDIDO ped where ped.CD_PDIDO = ?");
		return sql.toString();
	}
}