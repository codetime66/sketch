//TB_TRANS_DIA
package br.com.stelo.batch.pagamento.boleto.repository.entity;


import java.io.Serializable;
import java.util.Date;

public class TransacaoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long CD_TRANS;
	private String CD_RRN;
	private Integer CD_TPO_MSGEM;
	private Date DT_TRANS;
	private Long CD_VDDOR;
	private Integer CD_PROCM;
	private Double VR_TRANS;
	private Date DT_HORA_TRNSM;
	private Integer CD_TRACE;
	private Integer DT_CAPTU;
	private Integer CD_FORMA_CAPTU;
	private String CD_AUTRZ;
	private String NU_TERM;
	private Integer CD_MOEDA;
	private Integer CD_CRDEN;
	private Integer CD_TPO_PGTO;
	private Integer CD_PPRIE;
	private Integer CD_PCMTO;
	private Integer CD_SUB_MOTVO_REJEI;
	private String CD_RESPT;
	private String CD_RETOR;
	private Long CD_PDIDO;
	private String CD_CANAL_TRANS;
	private String DS_RETOR;
	private String DS_MOTVO_REJEI;
	private String CD_PDIDO_EC;
	private String ID_TRANS_CANAL;

	public Long getCD_TRANS() {
		return CD_TRANS;
	}
	public void setCD_TRANS(Long cD_TRANS) {
		CD_TRANS = cD_TRANS;
	}
	public String getCD_RRN() {
		return CD_RRN;
	}
	public void setCD_RRN(String cD_RRN) {
		CD_RRN = cD_RRN;
	}
	public Integer getCD_TPO_MSGEM() {
		return CD_TPO_MSGEM;
	}
	public void setCD_TPO_MSGEM(Integer cD_TPO_MSGEM) {
		CD_TPO_MSGEM = cD_TPO_MSGEM;
	}
	public Date getDT_TRANS() {
		return DT_TRANS;
	}
	public void setDT_TRANS(Date dT_TRANS) {
		DT_TRANS = dT_TRANS;
	}
	public Long getCD_VDDOR() {
		return CD_VDDOR;
	}
	public void setCD_VDDOR(Long cD_VDDOR) {
		CD_VDDOR = cD_VDDOR;
	}
	public Integer getCD_PROCM() {
		return CD_PROCM;
	}
	public void setCD_PROCM(Integer cD_PROCM) {
		CD_PROCM = cD_PROCM;
	}
	public Double getVR_TRANS() {
		return VR_TRANS;
	}
	public void setVR_TRANS(Double vR_TRANS) {
		VR_TRANS = vR_TRANS;
	}
	public Date getDT_HORA_TRNSM() {
		return DT_HORA_TRNSM;
	}
	public void setDT_HORA_TRNSM(Date dT_HORA_TRNSM) {
		DT_HORA_TRNSM = dT_HORA_TRNSM;
	}
	public Integer getCD_TRACE() {
		return CD_TRACE;
	}
	public void setCD_TRACE(Integer cD_TRACE) {
		CD_TRACE = cD_TRACE;
	}
	public Integer getDT_CAPTU() {
		return DT_CAPTU;
	}
	public void setDT_CAPTU(Integer dT_CAPTU) {
		DT_CAPTU = dT_CAPTU;
	}
	public Integer getCD_FORMA_CAPTU() {
		return CD_FORMA_CAPTU;
	}
	public void setCD_FORMA_CAPTU(Integer cD_FORMA_CAPTU) {
		CD_FORMA_CAPTU = cD_FORMA_CAPTU;
	}
	public String getCD_AUTRZ() {
		return CD_AUTRZ;
	}
	public void setCD_AUTRZ(String cD_AUTRZ) {
		CD_AUTRZ = cD_AUTRZ;
	}
	public String getNU_TERM() {
		return NU_TERM;
	}
	public void setNU_TERM(String nU_TERM) {
		NU_TERM = nU_TERM;
	}
	public Integer getCD_MOEDA() {
		return CD_MOEDA;
	}
	public void setCD_MOEDA(Integer cD_MOEDA) {
		CD_MOEDA = cD_MOEDA;
	}
	public Integer getCD_CRDEN() {
		return CD_CRDEN;
	}
	public void setCD_CRDEN(Integer cD_CRDEN) {
		CD_CRDEN = cD_CRDEN;
	}
	public Integer getCD_TPO_PGTO() {
		return CD_TPO_PGTO;
	}
	public void setCD_TPO_PGTO(Integer cD_TPO_PGTO) {
		CD_TPO_PGTO = cD_TPO_PGTO;
	}
	public Integer getCD_PPRIE() {
		return CD_PPRIE;
	}
	public void setCD_PPRIE(Integer cD_PPRIE) {
		CD_PPRIE = cD_PPRIE;
	}
	public Integer getCD_PCMTO() {
		return CD_PCMTO;
	}
	public void setCD_PCMTO(Integer cD_PCMTO) {
		CD_PCMTO = cD_PCMTO;
	}
	public Integer getCD_SUB_MOTVO_REJEI() {
		return CD_SUB_MOTVO_REJEI;
	}
	public void setCD_SUB_MOTVO_REJEI(Integer cD_SUB_MOTVO_REJEI) {
		CD_SUB_MOTVO_REJEI = cD_SUB_MOTVO_REJEI;
	}
	public String getCD_RESPT() {
		return CD_RESPT;
	}
	public void setCD_RESPT(String cD_RESPT) {
		CD_RESPT = cD_RESPT;
	}
	public String getCD_RETOR() {
		return CD_RETOR;
	}
	public void setCD_RETOR(String cD_RETOR) {
		CD_RETOR = cD_RETOR;
	}
	public Long getCD_PDIDO() {
		return CD_PDIDO;
	}
	public void setCD_PDIDO(Long cD_PDIDO) {
		CD_PDIDO = cD_PDIDO;
	}
	public String getCD_CANAL_TRANS() {
		return CD_CANAL_TRANS;
	}
	public void setCD_CANAL_TRANS(String cD_CANAL_TRANS) {
		CD_CANAL_TRANS = cD_CANAL_TRANS;
	}
	public String getDS_RETOR() {
		return DS_RETOR;
	}
	public void setDS_RETOR(String dS_RETOR) {
		DS_RETOR = dS_RETOR;
	}
	public String getDS_MOTVO_REJEI() {
		return DS_MOTVO_REJEI;
	}
	public void setDS_MOTVO_REJEI(String dS_MOTVO_REJEI) {
		DS_MOTVO_REJEI = dS_MOTVO_REJEI;
	}
	public String getCD_PDIDO_EC() {
		return CD_PDIDO_EC;
	}
	public void setCD_PDIDO_EC(String cD_PDIDO_EC) {
		CD_PDIDO_EC = cD_PDIDO_EC;
	}
	public String getID_TRANS_CANAL() {
		return ID_TRANS_CANAL;
	}
	public void setID_TRANS_CANAL(String iD_TRANS_CANAL) {
		ID_TRANS_CANAL = iD_TRANS_CANAL;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CD_AUTRZ == null) ? 0 : CD_AUTRZ.hashCode());
		result = prime * result + ((CD_CANAL_TRANS == null) ? 0 : CD_CANAL_TRANS.hashCode());
		result = prime * result + ((CD_CRDEN == null) ? 0 : CD_CRDEN.hashCode());
		result = prime * result + ((CD_FORMA_CAPTU == null) ? 0 : CD_FORMA_CAPTU.hashCode());
		result = prime * result + ((CD_MOEDA == null) ? 0 : CD_MOEDA.hashCode());
		result = prime * result + ((CD_PCMTO == null) ? 0 : CD_PCMTO.hashCode());
		result = prime * result + ((CD_PDIDO == null) ? 0 : CD_PDIDO.hashCode());
		result = prime * result + ((CD_PDIDO_EC == null) ? 0 : CD_PDIDO_EC.hashCode());
		result = prime * result + ((CD_PPRIE == null) ? 0 : CD_PPRIE.hashCode());
		result = prime * result + ((CD_PROCM == null) ? 0 : CD_PROCM.hashCode());
		result = prime * result + ((CD_RESPT == null) ? 0 : CD_RESPT.hashCode());
		result = prime * result + ((CD_RETOR == null) ? 0 : CD_RETOR.hashCode());
		result = prime * result + ((CD_RRN == null) ? 0 : CD_RRN.hashCode());
		result = prime * result + ((CD_SUB_MOTVO_REJEI == null) ? 0 : CD_SUB_MOTVO_REJEI.hashCode());
		result = prime * result + ((CD_TPO_MSGEM == null) ? 0 : CD_TPO_MSGEM.hashCode());
		result = prime * result + ((CD_TPO_PGTO == null) ? 0 : CD_TPO_PGTO.hashCode());
		result = prime * result + ((CD_TRACE == null) ? 0 : CD_TRACE.hashCode());
		result = prime * result + ((CD_TRANS == null) ? 0 : CD_TRANS.hashCode());
		result = prime * result + ((CD_VDDOR == null) ? 0 : CD_VDDOR.hashCode());
		result = prime * result + ((DS_MOTVO_REJEI == null) ? 0 : DS_MOTVO_REJEI.hashCode());
		result = prime * result + ((DS_RETOR == null) ? 0 : DS_RETOR.hashCode());
		result = prime * result + ((DT_CAPTU == null) ? 0 : DT_CAPTU.hashCode());
		result = prime * result + ((DT_HORA_TRNSM == null) ? 0 : DT_HORA_TRNSM.hashCode());
		result = prime * result + ((DT_TRANS == null) ? 0 : DT_TRANS.hashCode());
		result = prime * result + ((ID_TRANS_CANAL == null) ? 0 : ID_TRANS_CANAL.hashCode());
		result = prime * result + ((NU_TERM == null) ? 0 : NU_TERM.hashCode());
		result = prime * result + ((VR_TRANS == null) ? 0 : VR_TRANS.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransacaoEntity other = (TransacaoEntity) obj;
		if (CD_AUTRZ == null) {
			if (other.CD_AUTRZ != null)
				return false;
		} else if (!CD_AUTRZ.equals(other.CD_AUTRZ))
			return false;
		if (CD_CANAL_TRANS == null) {
			if (other.CD_CANAL_TRANS != null)
				return false;
		} else if (!CD_CANAL_TRANS.equals(other.CD_CANAL_TRANS))
			return false;
		if (CD_CRDEN == null) {
			if (other.CD_CRDEN != null)
				return false;
		} else if (!CD_CRDEN.equals(other.CD_CRDEN))
			return false;
		if (CD_FORMA_CAPTU == null) {
			if (other.CD_FORMA_CAPTU != null)
				return false;
		} else if (!CD_FORMA_CAPTU.equals(other.CD_FORMA_CAPTU))
			return false;
		if (CD_MOEDA == null) {
			if (other.CD_MOEDA != null)
				return false;
		} else if (!CD_MOEDA.equals(other.CD_MOEDA))
			return false;
		if (CD_PCMTO == null) {
			if (other.CD_PCMTO != null)
				return false;
		} else if (!CD_PCMTO.equals(other.CD_PCMTO))
			return false;
		if (CD_PDIDO == null) {
			if (other.CD_PDIDO != null)
				return false;
		} else if (!CD_PDIDO.equals(other.CD_PDIDO))
			return false;
		if (CD_PDIDO_EC == null) {
			if (other.CD_PDIDO_EC != null)
				return false;
		} else if (!CD_PDIDO_EC.equals(other.CD_PDIDO_EC))
			return false;
		if (CD_PPRIE == null) {
			if (other.CD_PPRIE != null)
				return false;
		} else if (!CD_PPRIE.equals(other.CD_PPRIE))
			return false;
		if (CD_PROCM == null) {
			if (other.CD_PROCM != null)
				return false;
		} else if (!CD_PROCM.equals(other.CD_PROCM))
			return false;
		if (CD_RESPT == null) {
			if (other.CD_RESPT != null)
				return false;
		} else if (!CD_RESPT.equals(other.CD_RESPT))
			return false;
		if (CD_RETOR == null) {
			if (other.CD_RETOR != null)
				return false;
		} else if (!CD_RETOR.equals(other.CD_RETOR))
			return false;
		if (CD_RRN == null) {
			if (other.CD_RRN != null)
				return false;
		} else if (!CD_RRN.equals(other.CD_RRN))
			return false;
		if (CD_SUB_MOTVO_REJEI == null) {
			if (other.CD_SUB_MOTVO_REJEI != null)
				return false;
		} else if (!CD_SUB_MOTVO_REJEI.equals(other.CD_SUB_MOTVO_REJEI))
			return false;
		if (CD_TPO_MSGEM == null) {
			if (other.CD_TPO_MSGEM != null)
				return false;
		} else if (!CD_TPO_MSGEM.equals(other.CD_TPO_MSGEM))
			return false;
		if (CD_TPO_PGTO == null) {
			if (other.CD_TPO_PGTO != null)
				return false;
		} else if (!CD_TPO_PGTO.equals(other.CD_TPO_PGTO))
			return false;
		if (CD_TRACE == null) {
			if (other.CD_TRACE != null)
				return false;
		} else if (!CD_TRACE.equals(other.CD_TRACE))
			return false;
		if (CD_TRANS == null) {
			if (other.CD_TRANS != null)
				return false;
		} else if (!CD_TRANS.equals(other.CD_TRANS))
			return false;
		if (CD_VDDOR == null) {
			if (other.CD_VDDOR != null)
				return false;
		} else if (!CD_VDDOR.equals(other.CD_VDDOR))
			return false;
		if (DS_MOTVO_REJEI == null) {
			if (other.DS_MOTVO_REJEI != null)
				return false;
		} else if (!DS_MOTVO_REJEI.equals(other.DS_MOTVO_REJEI))
			return false;
		if (DS_RETOR == null) {
			if (other.DS_RETOR != null)
				return false;
		} else if (!DS_RETOR.equals(other.DS_RETOR))
			return false;
		if (DT_CAPTU == null) {
			if (other.DT_CAPTU != null)
				return false;
		} else if (!DT_CAPTU.equals(other.DT_CAPTU))
			return false;
		if (DT_HORA_TRNSM == null) {
			if (other.DT_HORA_TRNSM != null)
				return false;
		} else if (!DT_HORA_TRNSM.equals(other.DT_HORA_TRNSM))
			return false;
		if (DT_TRANS == null) {
			if (other.DT_TRANS != null)
				return false;
		} else if (!DT_TRANS.equals(other.DT_TRANS))
			return false;
		if (ID_TRANS_CANAL == null) {
			if (other.ID_TRANS_CANAL != null)
				return false;
		} else if (!ID_TRANS_CANAL.equals(other.ID_TRANS_CANAL))
			return false;
		if (NU_TERM == null) {
			if (other.NU_TERM != null)
				return false;
		} else if (!NU_TERM.equals(other.NU_TERM))
			return false;
		if (VR_TRANS == null) {
			if (other.VR_TRANS != null)
				return false;
		} else if (!VR_TRANS.equals(other.VR_TRANS))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TransacaoEntity [CD_TRANS=" + CD_TRANS + ", CD_RRN=" + CD_RRN + ", CD_TPO_MSGEM=" + CD_TPO_MSGEM
				+ ", DT_TRANS=" + DT_TRANS + ", CD_VDDOR=" + CD_VDDOR + ", CD_PROCM=" + CD_PROCM + ", VR_TRANS="
				+ VR_TRANS + ", DT_HORA_TRNSM=" + DT_HORA_TRNSM + ", CD_TRACE=" + CD_TRACE + ", DT_CAPTU=" + DT_CAPTU
				+ ", CD_FORMA_CAPTU=" + CD_FORMA_CAPTU + ", CD_AUTRZ=" + CD_AUTRZ + ", NU_TERM=" + NU_TERM
				+ ", CD_MOEDA=" + CD_MOEDA + ", CD_CRDEN=" + CD_CRDEN + ", CD_TPO_PGTO=" + CD_TPO_PGTO + ", CD_PPRIE="
				+ CD_PPRIE + ", CD_PCMTO=" + CD_PCMTO + ", CD_SUB_MOTVO_REJEI=" + CD_SUB_MOTVO_REJEI + ", CD_RESPT="
				+ CD_RESPT + ", CD_RETOR=" + CD_RETOR + ", CD_PDIDO=" + CD_PDIDO + ", CD_CANAL_TRANS=" + CD_CANAL_TRANS
				+ ", DS_RETOR=" + DS_RETOR + ", DS_MOTVO_REJEI=" + DS_MOTVO_REJEI + ", CD_PDIDO_EC=" + CD_PDIDO_EC
				+ ", ID_TRANS_CANAL=" + ID_TRANS_CANAL + "]";
	}

}