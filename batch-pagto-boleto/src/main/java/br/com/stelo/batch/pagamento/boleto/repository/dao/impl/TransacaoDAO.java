package br.com.stelo.batch.pagamento.boleto.repository.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.stelo.batch.pagamento.boleto.repository.dao.ITransacaoDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.TransacaoEntity;

@Repository
public class TransacaoDAO implements ITransacaoDAO {

	private static final Logger log = LoggerFactory.getLogger(PagamentoDAO.class);

	@Qualifier("TRNGDS")
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public TransacaoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public void inserir(TransacaoEntity transacaoEntity) {
		log.info("### TransacaoDAO.inserir:"+transacaoEntity.toString());
		
		 jdbcTemplate.update(getInsertSql(),
					transacaoEntity.getCD_TRANS(),
					transacaoEntity.getCD_TPO_MSGEM(),
					transacaoEntity.getCD_PROCM(),
					transacaoEntity.getVR_TRANS(),
					transacaoEntity.getDT_HORA_TRNSM(),
					transacaoEntity.getCD_TRACE(),
					transacaoEntity.getDT_TRANS(),
					transacaoEntity.getDT_CAPTU(),
					transacaoEntity.getCD_FORMA_CAPTU(),
					transacaoEntity.getCD_RRN(),
					transacaoEntity.getCD_AUTRZ(),
					transacaoEntity.getNU_TERM(),
					transacaoEntity.getCD_VDDOR(),
					transacaoEntity.getCD_MOEDA(),
					transacaoEntity.getCD_CRDEN(),
					transacaoEntity.getCD_TPO_PGTO(),
					transacaoEntity.getCD_PPRIE(),
					transacaoEntity.getCD_PCMTO(),
					transacaoEntity.getCD_RESPT(),
					transacaoEntity.getCD_SUB_MOTVO_REJEI(),
					transacaoEntity.getDS_MOTVO_REJEI(),
					transacaoEntity.getCD_RETOR(),
					transacaoEntity.getDS_RETOR(),
					transacaoEntity.getCD_PDIDO(),
					transacaoEntity.getCD_CANAL_TRANS(),
					transacaoEntity.getCD_PDIDO_EC(),
					transacaoEntity.getID_TRANS_CANAL()
				 );
/*		
		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(getInsertSql());
			ps.setLong(1, transacaoEntity.getCD_TRANS());
			ps.setLong(2, transacaoEntity.getCD_TPO_MSGEM());
			ps.setLong(3, transacaoEntity.getCD_PROCM());
			ps.setDouble(4, transacaoEntity.getVR_TRANS());
			ps.setDate(5, new java.sql.Date( transacaoEntity.getDT_HORA_TRNSM().getTime()));
			ps.setLong(6, transacaoEntity.getCD_TRACE());
			ps.setDate(7, new java.sql.Date(transacaoEntity.getDT_TRANS().getTime()));
			ps.setLong(8, transacaoEntity.getDT_CAPTU());
			ps.setLong(9, transacaoEntity.getCD_FORMA_CAPTU());
			ps.setString(10, transacaoEntity.getCD_RRN());
			ps.setString(11, transacaoEntity.getCD_AUTRZ());
			ps.setString(12, transacaoEntity.getNU_TERM());
			ps.setLong(13, transacaoEntity.getCD_VDDOR());
			ps.setLong(14, transacaoEntity.getCD_MOEDA());
			ps.setLong(15, transacaoEntity.getCD_CRDEN());
			ps.setLong(16, transacaoEntity.getCD_TPO_PGTO());
			ps.setLong(17, transacaoEntity.getCD_PPRIE());
			ps.setLong(18, transacaoEntity.getCD_PCMTO());
			ps.setString(19, transacaoEntity.getCD_RESPT());
			ps.setLong(20, transacaoEntity.getCD_SUB_MOTVO_REJEI());
			ps.setString(21, transacaoEntity.getDS_MOTVO_REJEI());
			ps.setString(22, transacaoEntity.getCD_RETOR());
			ps.setString(23, transacaoEntity.getDS_RETOR());
			ps.setLong(24, transacaoEntity.getCD_PDIDO());
			ps.setString(25, transacaoEntity.getCD_CANAL_TRANS());
			ps.setString(26, transacaoEntity.getCD_PDIDO_EC());
			ps.setString(27, transacaoEntity.getID_TRANS_CANAL());

			return ps;
		});
*/
	}

    private String getInsertSql() {
    	StringBuilder sb = new StringBuilder();
    	    	
    	sb.append("INSERT INTO USR_HSTR.TB_TRANS_DIA (");
    	sb.append(" CD_TRANS, ");
        sb.append(" CD_TPO_MSGEM, ");
	    sb.append(" CD_PROCM, ");
	    sb.append(" VR_TRANS, ");
        sb.append(" DT_HORA_TRNSM, ");
    	sb.append(" CD_TRACE, ");
    	sb.append(" DT_TRANS, ");
    	sb.append(" DT_CAPTU, ");
    	sb.append(" CD_FORMA_CAPTU, ");
    	sb.append(" CD_RRN, ");
    	sb.append(" CD_AUTRZ, ");
    	sb.append(" NU_TERM, ");
    	sb.append(" CD_VDDOR, ");
    	sb.append(" CD_MOEDA, ");
    	sb.append(" CD_CRDEN, ");
    	sb.append(" CD_TPO_PGTO, ");
    	sb.append(" CD_PPRIE, ");
    	sb.append(" CD_PCMTO, ");
    	sb.append(" CD_RESPT, ");
    	sb.append(" CD_SUB_MOTVO_REJEI, ");
    	sb.append(" DS_MOTVO_REJEI, ");
    	sb.append(" CD_RETOR, ");
    	sb.append(" DS_RETOR, ");
    	sb.append(" CD_PDIDO, ");
    	sb.append(" CD_CANAL_TRANS, ");
    	sb.append(" CD_PDIDO_EC, ");
    	sb.append(" ID_TRANS_CANAL ");
    	sb.append(" )");
    	sb.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	return sb.toString();
    }
}