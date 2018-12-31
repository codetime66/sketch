package br.com.stelo.batch.pagamento.boleto.repository.entity;

import java.io.Serializable;
import java.util.Date;

public class PedidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long CD_PDIDO;
	private String CD_STTUS_PDIDO;
	private Integer CD_PLATF;
	private Date DT_INCL;
	private Long CD_VDDOR_ROOT_PDIDO;
	private String CD_ORIGEM_PRODUTO;
	private Long CD_COMPR;
	private String NU_DCTO_COMPR;
	private String NM_COMPR;
	private String DS_EMAIL_COMPR;
	private String NU_FONE_COMPR;
	private Date DT_NASC;
	private String SG_GNRO;
	private Integer ID_TP_FONE;
	private Date DT_ALT;
	private String USUAR_ALT;
	private String USUAR_INCL;
	private Integer CD_CANAL_ORIGEM;
	private Date DT_CAD_COMPR_EC;
	private String NU_REFT;
	private Integer VR_TX_JURO_PCELA;
	private Integer VR_REFT;
	private String EN_NOTIF_EC;
	private String NU_NSU_STELO;
	private String CD_TID_STELO;
	private String NM_FANTS;
	private String NM_RESP;
	private String TPO_COMPR_PSSOA;
	private Integer CD_STTUS_SOLCT_MAQNA;
	private String IC_CAD_COPLT;
	private Integer ID_STTUS_PROCM_TRANS_TIF;
	private String NU_INSCR_EST;
	private String NM_RZ_SCIAL;
	private String NM_URL_LJ_CMPLTO;
	private String ID_TRANS_CANAL;
	public Long getCD_PDIDO() {
		return CD_PDIDO;
	}
	public void setCD_PDIDO(Long cD_PDIDO) {
		CD_PDIDO = cD_PDIDO;
	}
	public String getCD_STTUS_PDIDO() {
		return CD_STTUS_PDIDO;
	}
	public void setCD_STTUS_PDIDO(String cD_STTUS_PDIDO) {
		CD_STTUS_PDIDO = cD_STTUS_PDIDO;
	}
	public Integer getCD_PLATF() {
		return CD_PLATF;
	}
	public void setCD_PLATF(Integer cD_PLATF) {
		CD_PLATF = cD_PLATF;
	}
	public Date getDT_INCL() {
		return DT_INCL;
	}
	public void setDT_INCL(Date dT_INCL) {
		DT_INCL = dT_INCL;
	}
	public Long getCD_VDDOR_ROOT_PDIDO() {
		return CD_VDDOR_ROOT_PDIDO;
	}
	public void setCD_VDDOR_ROOT_PDIDO(Long cD_VDDOR_ROOT_PDIDO) {
		CD_VDDOR_ROOT_PDIDO = cD_VDDOR_ROOT_PDIDO;
	}
	public String getCD_ORIGEM_PRODUTO() {
		return CD_ORIGEM_PRODUTO;
	}
	public void setCD_ORIGEM_PRODUTO(String cD_ORIGEM_PRODUTO) {
		CD_ORIGEM_PRODUTO = cD_ORIGEM_PRODUTO;
	}
	public Long getCD_COMPR() {
		return CD_COMPR;
	}
	public void setCD_COMPR(Long cD_COMPR) {
		CD_COMPR = cD_COMPR;
	}
	public String getNU_DCTO_COMPR() {
		return NU_DCTO_COMPR;
	}
	public void setNU_DCTO_COMPR(String nU_DCTO_COMPR) {
		NU_DCTO_COMPR = nU_DCTO_COMPR;
	}
	public String getNM_COMPR() {
		return NM_COMPR;
	}
	public void setNM_COMPR(String nM_COMPR) {
		NM_COMPR = nM_COMPR;
	}
	public String getDS_EMAIL_COMPR() {
		return DS_EMAIL_COMPR;
	}
	public void setDS_EMAIL_COMPR(String dS_EMAIL_COMPR) {
		DS_EMAIL_COMPR = dS_EMAIL_COMPR;
	}
	public String getNU_FONE_COMPR() {
		return NU_FONE_COMPR;
	}
	public void setNU_FONE_COMPR(String nU_FONE_COMPR) {
		NU_FONE_COMPR = nU_FONE_COMPR;
	}
	public Date getDT_NASC() {
		return DT_NASC;
	}
	public void setDT_NASC(Date dT_NASC) {
		DT_NASC = dT_NASC;
	}
	public String getSG_GNRO() {
		return SG_GNRO;
	}
	public void setSG_GNRO(String sG_GNRO) {
		SG_GNRO = sG_GNRO;
	}
	public Integer getID_TP_FONE() {
		return ID_TP_FONE;
	}
	public void setID_TP_FONE(Integer iD_TP_FONE) {
		ID_TP_FONE = iD_TP_FONE;
	}
	public Date getDT_ALT() {
		return DT_ALT;
	}
	public void setDT_ALT(Date dT_ALT) {
		DT_ALT = dT_ALT;
	}
	public String getUSUAR_ALT() {
		return USUAR_ALT;
	}
	public void setUSUAR_ALT(String uSUAR_ALT) {
		USUAR_ALT = uSUAR_ALT;
	}
	public String getUSUAR_INCL() {
		return USUAR_INCL;
	}
	public void setUSUAR_INCL(String uSUAR_INCL) {
		USUAR_INCL = uSUAR_INCL;
	}
	public Integer getCD_CANAL_ORIGEM() {
		return CD_CANAL_ORIGEM;
	}
	public void setCD_CANAL_ORIGEM(Integer cD_CANAL_ORIGEM) {
		CD_CANAL_ORIGEM = cD_CANAL_ORIGEM;
	}
	public Date getDT_CAD_COMPR_EC() {
		return DT_CAD_COMPR_EC;
	}
	public void setDT_CAD_COMPR_EC(Date dT_CAD_COMPR_EC) {
		DT_CAD_COMPR_EC = dT_CAD_COMPR_EC;
	}
	public String getNU_REFT() {
		return NU_REFT;
	}
	public void setNU_REFT(String nU_REFT) {
		NU_REFT = nU_REFT;
	}
	public Integer getVR_TX_JURO_PCELA() {
		return VR_TX_JURO_PCELA;
	}
	public void setVR_TX_JURO_PCELA(Integer vR_TX_JURO_PCELA) {
		VR_TX_JURO_PCELA = vR_TX_JURO_PCELA;
	}
	public Integer getVR_REFT() {
		return VR_REFT;
	}
	public void setVR_REFT(Integer vR_REFT) {
		VR_REFT = vR_REFT;
	}
	public String getEN_NOTIF_EC() {
		return EN_NOTIF_EC;
	}
	public void setEN_NOTIF_EC(String eN_NOTIF_EC) {
		EN_NOTIF_EC = eN_NOTIF_EC;
	}
	public String getNU_NSU_STELO() {
		return NU_NSU_STELO;
	}
	public void setNU_NSU_STELO(String nU_NSU_STELO) {
		NU_NSU_STELO = nU_NSU_STELO;
	}
	public String getCD_TID_STELO() {
		return CD_TID_STELO;
	}
	public void setCD_TID_STELO(String cD_TID_STELO) {
		CD_TID_STELO = cD_TID_STELO;
	}
	public String getNM_FANTS() {
		return NM_FANTS;
	}
	public void setNM_FANTS(String nM_FANTS) {
		NM_FANTS = nM_FANTS;
	}
	public String getNM_RESP() {
		return NM_RESP;
	}
	public void setNM_RESP(String nM_RESP) {
		NM_RESP = nM_RESP;
	}
	public String getTPO_COMPR_PSSOA() {
		return TPO_COMPR_PSSOA;
	}
	public void setTPO_COMPR_PSSOA(String tPO_COMPR_PSSOA) {
		TPO_COMPR_PSSOA = tPO_COMPR_PSSOA;
	}
	public Integer getCD_STTUS_SOLCT_MAQNA() {
		return CD_STTUS_SOLCT_MAQNA;
	}
	public void setCD_STTUS_SOLCT_MAQNA(Integer cD_STTUS_SOLCT_MAQNA) {
		CD_STTUS_SOLCT_MAQNA = cD_STTUS_SOLCT_MAQNA;
	}
	public String getIC_CAD_COPLT() {
		return IC_CAD_COPLT;
	}
	public void setIC_CAD_COPLT(String iC_CAD_COPLT) {
		IC_CAD_COPLT = iC_CAD_COPLT;
	}
	public Integer getID_STTUS_PROCM_TRANS_TIF() {
		return ID_STTUS_PROCM_TRANS_TIF;
	}
	public void setID_STTUS_PROCM_TRANS_TIF(Integer iD_STTUS_PROCM_TRANS_TIF) {
		ID_STTUS_PROCM_TRANS_TIF = iD_STTUS_PROCM_TRANS_TIF;
	}
	public String getNU_INSCR_EST() {
		return NU_INSCR_EST;
	}
	public void setNU_INSCR_EST(String nU_INSCR_EST) {
		NU_INSCR_EST = nU_INSCR_EST;
	}
	public String getNM_RZ_SCIAL() {
		return NM_RZ_SCIAL;
	}
	public void setNM_RZ_SCIAL(String nM_RZ_SCIAL) {
		NM_RZ_SCIAL = nM_RZ_SCIAL;
	}
	public String getNM_URL_LJ_CMPLTO() {
		return NM_URL_LJ_CMPLTO;
	}
	public void setNM_URL_LJ_CMPLTO(String nM_URL_LJ_CMPLTO) {
		NM_URL_LJ_CMPLTO = nM_URL_LJ_CMPLTO;
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
		result = prime * result + ((CD_CANAL_ORIGEM == null) ? 0 : CD_CANAL_ORIGEM.hashCode());
		result = prime * result + ((CD_COMPR == null) ? 0 : CD_COMPR.hashCode());
		result = prime * result + ((CD_ORIGEM_PRODUTO == null) ? 0 : CD_ORIGEM_PRODUTO.hashCode());
		result = prime * result + ((CD_PDIDO == null) ? 0 : CD_PDIDO.hashCode());
		result = prime * result + ((CD_PLATF == null) ? 0 : CD_PLATF.hashCode());
		result = prime * result + ((CD_STTUS_PDIDO == null) ? 0 : CD_STTUS_PDIDO.hashCode());
		result = prime * result + ((CD_STTUS_SOLCT_MAQNA == null) ? 0 : CD_STTUS_SOLCT_MAQNA.hashCode());
		result = prime * result + ((CD_TID_STELO == null) ? 0 : CD_TID_STELO.hashCode());
		result = prime * result + ((CD_VDDOR_ROOT_PDIDO == null) ? 0 : CD_VDDOR_ROOT_PDIDO.hashCode());
		result = prime * result + ((DS_EMAIL_COMPR == null) ? 0 : DS_EMAIL_COMPR.hashCode());
		result = prime * result + ((DT_ALT == null) ? 0 : DT_ALT.hashCode());
		result = prime * result + ((DT_CAD_COMPR_EC == null) ? 0 : DT_CAD_COMPR_EC.hashCode());
		result = prime * result + ((DT_INCL == null) ? 0 : DT_INCL.hashCode());
		result = prime * result + ((DT_NASC == null) ? 0 : DT_NASC.hashCode());
		result = prime * result + ((EN_NOTIF_EC == null) ? 0 : EN_NOTIF_EC.hashCode());
		result = prime * result + ((IC_CAD_COPLT == null) ? 0 : IC_CAD_COPLT.hashCode());
		result = prime * result + ((ID_STTUS_PROCM_TRANS_TIF == null) ? 0 : ID_STTUS_PROCM_TRANS_TIF.hashCode());
		result = prime * result + ((ID_TP_FONE == null) ? 0 : ID_TP_FONE.hashCode());
		result = prime * result + ((ID_TRANS_CANAL == null) ? 0 : ID_TRANS_CANAL.hashCode());
		result = prime * result + ((NM_COMPR == null) ? 0 : NM_COMPR.hashCode());
		result = prime * result + ((NM_FANTS == null) ? 0 : NM_FANTS.hashCode());
		result = prime * result + ((NM_RESP == null) ? 0 : NM_RESP.hashCode());
		result = prime * result + ((NM_RZ_SCIAL == null) ? 0 : NM_RZ_SCIAL.hashCode());
		result = prime * result + ((NM_URL_LJ_CMPLTO == null) ? 0 : NM_URL_LJ_CMPLTO.hashCode());
		result = prime * result + ((NU_DCTO_COMPR == null) ? 0 : NU_DCTO_COMPR.hashCode());
		result = prime * result + ((NU_FONE_COMPR == null) ? 0 : NU_FONE_COMPR.hashCode());
		result = prime * result + ((NU_INSCR_EST == null) ? 0 : NU_INSCR_EST.hashCode());
		result = prime * result + ((NU_NSU_STELO == null) ? 0 : NU_NSU_STELO.hashCode());
		result = prime * result + ((NU_REFT == null) ? 0 : NU_REFT.hashCode());
		result = prime * result + ((SG_GNRO == null) ? 0 : SG_GNRO.hashCode());
		result = prime * result + ((TPO_COMPR_PSSOA == null) ? 0 : TPO_COMPR_PSSOA.hashCode());
		result = prime * result + ((USUAR_ALT == null) ? 0 : USUAR_ALT.hashCode());
		result = prime * result + ((USUAR_INCL == null) ? 0 : USUAR_INCL.hashCode());
		result = prime * result + ((VR_REFT == null) ? 0 : VR_REFT.hashCode());
		result = prime * result + ((VR_TX_JURO_PCELA == null) ? 0 : VR_TX_JURO_PCELA.hashCode());
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
		PedidoEntity other = (PedidoEntity) obj;
		if (CD_CANAL_ORIGEM == null) {
			if (other.CD_CANAL_ORIGEM != null)
				return false;
		} else if (!CD_CANAL_ORIGEM.equals(other.CD_CANAL_ORIGEM))
			return false;
		if (CD_COMPR == null) {
			if (other.CD_COMPR != null)
				return false;
		} else if (!CD_COMPR.equals(other.CD_COMPR))
			return false;
		if (CD_ORIGEM_PRODUTO == null) {
			if (other.CD_ORIGEM_PRODUTO != null)
				return false;
		} else if (!CD_ORIGEM_PRODUTO.equals(other.CD_ORIGEM_PRODUTO))
			return false;
		if (CD_PDIDO == null) {
			if (other.CD_PDIDO != null)
				return false;
		} else if (!CD_PDIDO.equals(other.CD_PDIDO))
			return false;
		if (CD_PLATF == null) {
			if (other.CD_PLATF != null)
				return false;
		} else if (!CD_PLATF.equals(other.CD_PLATF))
			return false;
		if (CD_STTUS_PDIDO == null) {
			if (other.CD_STTUS_PDIDO != null)
				return false;
		} else if (!CD_STTUS_PDIDO.equals(other.CD_STTUS_PDIDO))
			return false;
		if (CD_STTUS_SOLCT_MAQNA == null) {
			if (other.CD_STTUS_SOLCT_MAQNA != null)
				return false;
		} else if (!CD_STTUS_SOLCT_MAQNA.equals(other.CD_STTUS_SOLCT_MAQNA))
			return false;
		if (CD_TID_STELO == null) {
			if (other.CD_TID_STELO != null)
				return false;
		} else if (!CD_TID_STELO.equals(other.CD_TID_STELO))
			return false;
		if (CD_VDDOR_ROOT_PDIDO == null) {
			if (other.CD_VDDOR_ROOT_PDIDO != null)
				return false;
		} else if (!CD_VDDOR_ROOT_PDIDO.equals(other.CD_VDDOR_ROOT_PDIDO))
			return false;
		if (DS_EMAIL_COMPR == null) {
			if (other.DS_EMAIL_COMPR != null)
				return false;
		} else if (!DS_EMAIL_COMPR.equals(other.DS_EMAIL_COMPR))
			return false;
		if (DT_ALT == null) {
			if (other.DT_ALT != null)
				return false;
		} else if (!DT_ALT.equals(other.DT_ALT))
			return false;
		if (DT_CAD_COMPR_EC == null) {
			if (other.DT_CAD_COMPR_EC != null)
				return false;
		} else if (!DT_CAD_COMPR_EC.equals(other.DT_CAD_COMPR_EC))
			return false;
		if (DT_INCL == null) {
			if (other.DT_INCL != null)
				return false;
		} else if (!DT_INCL.equals(other.DT_INCL))
			return false;
		if (DT_NASC == null) {
			if (other.DT_NASC != null)
				return false;
		} else if (!DT_NASC.equals(other.DT_NASC))
			return false;
		if (EN_NOTIF_EC == null) {
			if (other.EN_NOTIF_EC != null)
				return false;
		} else if (!EN_NOTIF_EC.equals(other.EN_NOTIF_EC))
			return false;
		if (IC_CAD_COPLT == null) {
			if (other.IC_CAD_COPLT != null)
				return false;
		} else if (!IC_CAD_COPLT.equals(other.IC_CAD_COPLT))
			return false;
		if (ID_STTUS_PROCM_TRANS_TIF == null) {
			if (other.ID_STTUS_PROCM_TRANS_TIF != null)
				return false;
		} else if (!ID_STTUS_PROCM_TRANS_TIF.equals(other.ID_STTUS_PROCM_TRANS_TIF))
			return false;
		if (ID_TP_FONE == null) {
			if (other.ID_TP_FONE != null)
				return false;
		} else if (!ID_TP_FONE.equals(other.ID_TP_FONE))
			return false;
		if (ID_TRANS_CANAL == null) {
			if (other.ID_TRANS_CANAL != null)
				return false;
		} else if (!ID_TRANS_CANAL.equals(other.ID_TRANS_CANAL))
			return false;
		if (NM_COMPR == null) {
			if (other.NM_COMPR != null)
				return false;
		} else if (!NM_COMPR.equals(other.NM_COMPR))
			return false;
		if (NM_FANTS == null) {
			if (other.NM_FANTS != null)
				return false;
		} else if (!NM_FANTS.equals(other.NM_FANTS))
			return false;
		if (NM_RESP == null) {
			if (other.NM_RESP != null)
				return false;
		} else if (!NM_RESP.equals(other.NM_RESP))
			return false;
		if (NM_RZ_SCIAL == null) {
			if (other.NM_RZ_SCIAL != null)
				return false;
		} else if (!NM_RZ_SCIAL.equals(other.NM_RZ_SCIAL))
			return false;
		if (NM_URL_LJ_CMPLTO == null) {
			if (other.NM_URL_LJ_CMPLTO != null)
				return false;
		} else if (!NM_URL_LJ_CMPLTO.equals(other.NM_URL_LJ_CMPLTO))
			return false;
		if (NU_DCTO_COMPR == null) {
			if (other.NU_DCTO_COMPR != null)
				return false;
		} else if (!NU_DCTO_COMPR.equals(other.NU_DCTO_COMPR))
			return false;
		if (NU_FONE_COMPR == null) {
			if (other.NU_FONE_COMPR != null)
				return false;
		} else if (!NU_FONE_COMPR.equals(other.NU_FONE_COMPR))
			return false;
		if (NU_INSCR_EST == null) {
			if (other.NU_INSCR_EST != null)
				return false;
		} else if (!NU_INSCR_EST.equals(other.NU_INSCR_EST))
			return false;
		if (NU_NSU_STELO == null) {
			if (other.NU_NSU_STELO != null)
				return false;
		} else if (!NU_NSU_STELO.equals(other.NU_NSU_STELO))
			return false;
		if (NU_REFT == null) {
			if (other.NU_REFT != null)
				return false;
		} else if (!NU_REFT.equals(other.NU_REFT))
			return false;
		if (SG_GNRO == null) {
			if (other.SG_GNRO != null)
				return false;
		} else if (!SG_GNRO.equals(other.SG_GNRO))
			return false;
		if (TPO_COMPR_PSSOA == null) {
			if (other.TPO_COMPR_PSSOA != null)
				return false;
		} else if (!TPO_COMPR_PSSOA.equals(other.TPO_COMPR_PSSOA))
			return false;
		if (USUAR_ALT == null) {
			if (other.USUAR_ALT != null)
				return false;
		} else if (!USUAR_ALT.equals(other.USUAR_ALT))
			return false;
		if (USUAR_INCL == null) {
			if (other.USUAR_INCL != null)
				return false;
		} else if (!USUAR_INCL.equals(other.USUAR_INCL))
			return false;
		if (VR_REFT == null) {
			if (other.VR_REFT != null)
				return false;
		} else if (!VR_REFT.equals(other.VR_REFT))
			return false;
		if (VR_TX_JURO_PCELA == null) {
			if (other.VR_TX_JURO_PCELA != null)
				return false;
		} else if (!VR_TX_JURO_PCELA.equals(other.VR_TX_JURO_PCELA))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PedidoEntity [CD_PDIDO=" + CD_PDIDO + ", CD_STTUS_PDIDO=" + CD_STTUS_PDIDO + ", CD_PLATF=" + CD_PLATF
				+ ", DT_INCL=" + DT_INCL + ", CD_VDDOR_ROOT_PDIDO=" + CD_VDDOR_ROOT_PDIDO + ", CD_ORIGEM_PRODUTO="
				+ CD_ORIGEM_PRODUTO + ", CD_COMPR=" + CD_COMPR + ", NU_DCTO_COMPR=" + NU_DCTO_COMPR + ", NM_COMPR="
				+ NM_COMPR + ", DS_EMAIL_COMPR=" + DS_EMAIL_COMPR + ", NU_FONE_COMPR=" + NU_FONE_COMPR + ", DT_NASC="
				+ DT_NASC + ", SG_GNRO=" + SG_GNRO + ", ID_TP_FONE=" + ID_TP_FONE + ", DT_ALT=" + DT_ALT
				+ ", USUAR_ALT=" + USUAR_ALT + ", USUAR_INCL=" + USUAR_INCL + ", CD_CANAL_ORIGEM=" + CD_CANAL_ORIGEM
				+ ", DT_CAD_COMPR_EC=" + DT_CAD_COMPR_EC + ", NU_REFT=" + NU_REFT + ", VR_TX_JURO_PCELA="
				+ VR_TX_JURO_PCELA + ", VR_REFT=" + VR_REFT + ", EN_NOTIF_EC=" + EN_NOTIF_EC + ", NU_NSU_STELO="
				+ NU_NSU_STELO + ", CD_TID_STELO=" + CD_TID_STELO + ", NM_FANTS=" + NM_FANTS + ", NM_RESP=" + NM_RESP
				+ ", TPO_COMPR_PSSOA=" + TPO_COMPR_PSSOA + ", CD_STTUS_SOLCT_MAQNA=" + CD_STTUS_SOLCT_MAQNA
				+ ", IC_CAD_COPLT=" + IC_CAD_COPLT + ", ID_STTUS_PROCM_TRANS_TIF=" + ID_STTUS_PROCM_TRANS_TIF
				+ ", NU_INSCR_EST=" + NU_INSCR_EST + ", NM_RZ_SCIAL=" + NM_RZ_SCIAL + ", NM_URL_LJ_CMPLTO="
				+ NM_URL_LJ_CMPLTO + ", ID_TRANS_CANAL=" + ID_TRANS_CANAL + "]";
	}
	
}
