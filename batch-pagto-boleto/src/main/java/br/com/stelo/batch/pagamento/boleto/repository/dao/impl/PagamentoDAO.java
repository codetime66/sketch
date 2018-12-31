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

import br.com.stelo.batch.pagamento.boleto.repository.dao.IPagamentoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PagamentoEntity;

@Repository
public class PagamentoDAO implements IPagamentoDAO {

	private static final Logger log = LoggerFactory.getLogger(PagamentoDAO.class);

	@Qualifier("TRNGDS")
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public PagamentoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public PagamentoEntity getPagamento(final Long idStelo, final Long codigoPedido) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("pag.CD_PGTO, ");
		sql.append("pag.CD_VDDOR, ");
		sql.append("pag.CD_PDIDO, ");
		sql.append("pag.CD_TPO_PGTO, ");
		sql.append("pag.CD_PPRIE, ");
		sql.append("pag.VR_PGTO, ");
		sql.append("pag.CD_PCMTO, ");
		sql.append("pag.NU_TARGET, ");
		sql.append("pag.CD_STTUS_PGTO, ");
		sql.append("pag.VR_ESTRN, ");
		sql.append("pag.VR_CANCT, ");
		sql.append("pag.DT_INCL_PGTO, ");
		sql.append("pag.CD_AUTRZ, ");
		sql.append("pag.VR_PG, ");
		sql.append("pag.VR_FRETE, ");
		sql.append("pag.DS_MOEDA, ");
		sql.append("pag.DS_TP_PGTO, ");
		sql.append("pag.CD_COMPR, ");
		sql.append("pag.DT_VCTO, ");
		sql.append("pag.DT_PGTO, ");
		sql.append("pag.CD_FNT_REJEI, ");
		sql.append("pag.CD_MOTVO_REJEI, ");
		sql.append("pag.CD_SUBMOTVO_REJEI, ");
		sql.append("pag.CD_TRACE, ");
		sql.append("pag.CD_RRN, ");
		sql.append("pag.CD_CUPOM, ");
		sql.append("pag.NU_PCELA, ");
		sql.append("pag.DT_ULT_CANC, ");
		sql.append("pag.VR_ULT_CANC, ");
		sql.append("pag.DT_APROV_ADQRE, ");
		sql.append("pag.ID_BCO, ");
		sql.append("pag.VR_MARKUP_BCO, ");
		sql.append("pag.ID_PLATF, ");
		sql.append("pag.VR_MARKUP_PLATF, ");
		sql.append("pag.ID_PCERO, ");
		sql.append("pag.VR_MARKUP_PCERO, ");
		sql.append("pag.ID_PGTO_EC, ");
		sql.append("pag.NU_DOCTO_PTDOR, ");
		sql.append("pag.CD_RESPT, ");
		sql.append("pag.CD_SERIE_LEITR, ");
		sql.append("pag.ID_PGTO_CIELO, ");
		sql.append("pag.CD_TID_CIELO, ");
		sql.append("pag.NU_NSU_CIELO ");
        sql.append(" from ");
        sql.append("USR_GEPD.TB_PGTO pag ");
		sql.append(" where ");
		sql.append("pag.CD_VDDOR=? and pag.CD_PDIDO=?");
		
		log.info("###PagamentoDAO.getPagamento: ");
		Object[] params = {idStelo, codigoPedido};
		List<PagamentoEntity> pagamentos = jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<PagamentoEntity>(PagamentoEntity.class));
		PagamentoEntity pagamento = null;
		if (pagamentos!=null && !pagamentos.isEmpty()) {
			pagamento = pagamentos.get(0);
		}

		return pagamento;
	}

}