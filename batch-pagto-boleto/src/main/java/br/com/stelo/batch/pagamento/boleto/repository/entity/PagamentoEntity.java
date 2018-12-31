package br.com.stelo.batch.pagamento.boleto.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PagamentoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer CD_PGTO;
	private Long CD_VDDOR;
	private Long CD_PDIDO;
	private Integer CD_TPO_PGTO;
	private Integer CD_PPRIE;
	private Double VR_PGTO;
	private Integer CD_PCMTO;
	private String NU_TARGET;
	private String CD_STTUS_PGTO;
	private Double VR_ESTRN;
	private Double VR_CANCT;
	private Date DT_INCL_PGTO;
	private String CD_AUTRZ;
	private Double VR_PG;
	private Double VR_FRETE;
	private String DS_MOEDA;
	private String DS_TP_PGTO;
	private Long CD_COMPR;
	private Date DT_VCTO;
	private Date DT_PGTO;
	private Integer CD_FNT_REJEI;
	private Integer CD_MOTVO_REJEI;
	private Integer CD_SUBMOTVO_REJEI;
	private Integer CD_TRACE;
	private String CD_RRN;
	private Long CD_CUPOM;
	private Integer NU_PCELA;
	private Date DT_ULT_CANC;
	private Double VR_ULT_CANC;
	private Date DT_APROV_ADQRE;
	private Long ID_BCO;
	private BigDecimal VR_MARKUP_BCO;
	private Long ID_PLATF;
	private BigDecimal VR_MARKUP_PLATF;
	private Long ID_PCERO;
	private BigDecimal VR_MARKUP_PCERO;
	private String ID_PGTO_EC;
	private String NU_DOCTO_PTDOR;
	private String CD_RESPT;
    private String CD_SERIE_LEITR;
	private String ID_PGTO_CIELO;
	private String CD_TID_CIELO;
	private String NU_NSU_CIELO;

	public Integer getCD_PGTO() {
		return CD_PGTO;
	}
	
	public void setCD_PGTO(Integer cD_PGTO) {
		CD_PGTO = cD_PGTO;
	}
	
	public Long getCD_VDDOR() {
		return CD_VDDOR;
	}
	
	public void setCD_VDDOR(Long cD_VDDOR) {
		CD_VDDOR = cD_VDDOR;
	}
	
	public Long getCD_PDIDO() {
		return CD_PDIDO;
	}
	
	public void setCD_PDIDO(Long cD_PDIDO) {
		CD_PDIDO = cD_PDIDO;
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
	
	public Double getVR_PGTO() {
		return VR_PGTO;
	}
	
	public void setVR_PGTO(Double vR_PGTO) {
		VR_PGTO = vR_PGTO;
	}
	
	public Integer getCD_PCMTO() {
		return CD_PCMTO;
	}
	
	public void setCD_PCMTO(Integer cD_PCMTO) {
		CD_PCMTO = cD_PCMTO;
	}
	
	public String getNU_TARGET() {
		return NU_TARGET;
	}
	
	public void setNU_TARGET(String nU_TARGET) {
		NU_TARGET = nU_TARGET;
	}
	
	public String getCD_STTUS_PGTO() {
		return CD_STTUS_PGTO;
	}
	
	public void setCD_STTUS_PGTO(String cD_STTUS_PGTO) {
		CD_STTUS_PGTO = cD_STTUS_PGTO;
	}
	
	public Double getVR_ESTRN() {
		return VR_ESTRN;
	} 
	
	public void setVR_ESTRN(Double vR_ESTRN) {
		VR_ESTRN = vR_ESTRN;
	} 
	
	public Double getVR_CANCT() {
		return VR_CANCT;
	}
	
	public void setVR_CANCT(Double vR_CANCT) {
		VR_CANCT = vR_CANCT;
	}
	
	public Date getDT_INCL_PGTO() {
		return DT_INCL_PGTO;
	}
	
	public void setDT_INCL_PGTO(Date dT_INCL_PGTO) {
		DT_INCL_PGTO = dT_INCL_PGTO;
	}
	
	public String getCD_AUTRZ() {
		return CD_AUTRZ;
	}
	
	public void setCD_AUTRZ(String cD_AUTRZ) {
		CD_AUTRZ = cD_AUTRZ;
	}
	
	public Double getVR_PG() {
		return VR_PG;
	}
	
	public void setVR_PG(Double vR_PG) {
		VR_PG = vR_PG;
	}
	
	public Double getVR_FRETE() {
		return VR_FRETE;
	}
	
	public void setVR_FRETE(Double vR_FRETE) {
		VR_FRETE = vR_FRETE;
	}
	
	public String getDS_MOEDA() {
		return DS_MOEDA;
	}
	
	public void setDS_MOEDA(String dS_MOEDA) {
		DS_MOEDA = dS_MOEDA;
	}
	
	public String getDS_TP_PGTO() {
		return DS_TP_PGTO;
	}
	
	public void setDS_TP_PGTO(String dS_TP_PGTO) {
		DS_TP_PGTO = dS_TP_PGTO;
	}
	
	public Long getCD_COMPR() {
		return CD_COMPR;
	}
	
	public void setCD_COMPR(Long cD_COMPR) {
		CD_COMPR = cD_COMPR;
	}
	
	public Date getDT_VCTO() {
		return DT_VCTO;
	}
	
	public void setDT_VCTO(Date dT_VCTO) {
		DT_VCTO = dT_VCTO;
	}
	
	public Date getDT_PGTO() {
		return DT_PGTO;
	}
	
	public void setDT_PGTO(Date dT_PGTO) {
		DT_PGTO = dT_PGTO;
	}
	
	public Integer getCD_FNT_REJEI() {
		return CD_FNT_REJEI;
	}
	
	public void setCD_FNT_REJEI(Integer cD_FNT_REJEI) {
		CD_FNT_REJEI = cD_FNT_REJEI;
	}
	
	public Integer getCD_MOTVO_REJEI() {
		return CD_MOTVO_REJEI;
	}
	
	public void setCD_MOTVO_REJEI(Integer cD_MOTVO_REJEI) {
		CD_MOTVO_REJEI = cD_MOTVO_REJEI;
	}
	
	public Integer getCD_SUBMOTVO_REJEI() {
		return CD_SUBMOTVO_REJEI;
	}
	
	public void setCD_SUBMOTVO_REJEI(Integer cD_SUBMOTVO_REJEI) {
		CD_SUBMOTVO_REJEI = cD_SUBMOTVO_REJEI;
	}
	
	public Integer getCD_TRACE() {
		return CD_TRACE;
	}
	
	public void setCD_TRACE(Integer cD_TRACE) {
		CD_TRACE = cD_TRACE;
	}
	
	public String getCD_RRN() {
		return CD_RRN;
	}
	
	public void setCD_RRN(String cD_RRN) {
		CD_RRN = cD_RRN;
	}
	
	public Long getCD_CUPOM() {
		return CD_CUPOM;
	}
	
	public void setCD_CUPOM(Long cD_CUPOM) {
		CD_CUPOM = cD_CUPOM;
	}
	
	public Integer getNU_PCELA() {
		return NU_PCELA;
	}
	
	public void setNU_PCELA(Integer nU_PCELA) {
		NU_PCELA = nU_PCELA;
	}
	
	public Date getDT_ULT_CANC() {
		return DT_ULT_CANC;
	}
	
	public void setDT_ULT_CANC(Date dT_ULT_CANC) {
		DT_ULT_CANC = dT_ULT_CANC;
	}
	
	public Double getVR_ULT_CANC() {
		return VR_ULT_CANC;
	}
	
	public void setVR_ULT_CANC(Double vR_ULT_CANC) {
		VR_ULT_CANC = vR_ULT_CANC;
	}
	
	public Date getDT_APROV_ADQRE() {
		return DT_APROV_ADQRE;
	}
	
	public void setDT_APROV_ADQRE(Date dT_APROV_ADQRE) {
		DT_APROV_ADQRE = dT_APROV_ADQRE;
	}
	
	public Long getID_BCO() {
		return ID_BCO;
	}
	
	public void setID_BCO(Long iD_BCO) {
		ID_BCO = iD_BCO;
	}
	
	public BigDecimal getVR_MARKUP_BCO() {
		return VR_MARKUP_BCO;
	}
	
	public void setVR_MARKUP_BCO(BigDecimal vR_MARKUP_BCO) {
		VR_MARKUP_BCO = vR_MARKUP_BCO;
	}
	
	public Long getID_PLATF() {
		return ID_PLATF;
	}
	
	public void setID_PLATF(Long iD_PLATF) {
		ID_PLATF = iD_PLATF;
	}
	
	public BigDecimal getVR_MARKUP_PLATF() {
		return VR_MARKUP_PLATF;
	}
	
	public void setVR_MARKUP_PLATF(BigDecimal vR_MARKUP_PLATF) {
		VR_MARKUP_PLATF = vR_MARKUP_PLATF;
	}
	
	public Long getID_PCERO() {
		return ID_PCERO;
	}
	
	public void setID_PCERO(Long iD_PCERO) {
		ID_PCERO = iD_PCERO;
	}
	
	public BigDecimal getVR_MARKUP_PCERO() {
		return VR_MARKUP_PCERO;
	}
	
	public void setVR_MARKUP_PCERO(BigDecimal vR_MARKUP_PCERO) {
		VR_MARKUP_PCERO = vR_MARKUP_PCERO;
	}
	
	public String getID_PGTO_EC() {
		return ID_PGTO_EC;
	}
	
	public void setID_PGTO_EC(String iD_PGTO_EC) {
		ID_PGTO_EC = iD_PGTO_EC;
	}
	
	public String getNU_DOCTO_PTDOR() {
		return NU_DOCTO_PTDOR;
	}
	
	public void setNU_DOCTO_PTDOR(String nU_DOCTO_PTDOR) {
		NU_DOCTO_PTDOR = nU_DOCTO_PTDOR;
	}
	
	public String getCD_RESPT() {
		return CD_RESPT;
	}
	
	public void setCD_RESPT(String cD_RESPT) {
		CD_RESPT = cD_RESPT;
	}
	
	public String getCD_SERIE_LEITR() {
		return CD_SERIE_LEITR;
	}
	
	public void setCD_SERIE_LEITR(String cD_SERIE_LEITR) {
		CD_SERIE_LEITR = cD_SERIE_LEITR;
	}
	
	public String getID_PGTO_CIELO() {
		return ID_PGTO_CIELO;
	}
	
	public void setID_PGTO_CIELO(String iD_PGTO_CIELO) {
		ID_PGTO_CIELO = iD_PGTO_CIELO;
	}
	
	public String getCD_TID_CIELO() {
		return CD_TID_CIELO;
	}
	
	public void setCD_TID_CIELO(String cD_TID_CIELO) {
		CD_TID_CIELO = cD_TID_CIELO;
	}
	
	public String getNU_NSU_CIELO() {
		return NU_NSU_CIELO;
	}
	
	public void setNU_NSU_CIELO(String nU_NSU_CIELO) {
		NU_NSU_CIELO = nU_NSU_CIELO;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CD_AUTRZ == null) ? 0 : CD_AUTRZ.hashCode());
		result = prime * result + ((CD_COMPR == null) ? 0 : CD_COMPR.hashCode());
		result = prime * result + ((CD_CUPOM == null) ? 0 : CD_CUPOM.hashCode());
		result = prime * result + ((CD_FNT_REJEI == null) ? 0 : CD_FNT_REJEI.hashCode());
		result = prime * result + ((CD_MOTVO_REJEI == null) ? 0 : CD_MOTVO_REJEI.hashCode());
		result = prime * result + ((CD_PCMTO == null) ? 0 : CD_PCMTO.hashCode());
		result = prime * result + ((CD_PDIDO == null) ? 0 : CD_PDIDO.hashCode());
		result = prime * result + ((CD_PGTO == null) ? 0 : CD_PGTO.hashCode());
		result = prime * result + ((CD_PPRIE == null) ? 0 : CD_PPRIE.hashCode());
		result = prime * result + ((CD_RESPT == null) ? 0 : CD_RESPT.hashCode());
		result = prime * result + ((CD_RRN == null) ? 0 : CD_RRN.hashCode());
		result = prime * result + ((CD_SERIE_LEITR == null) ? 0 : CD_SERIE_LEITR.hashCode());
		result = prime * result + ((CD_STTUS_PGTO == null) ? 0 : CD_STTUS_PGTO.hashCode());
		result = prime * result + ((CD_SUBMOTVO_REJEI == null) ? 0 : CD_SUBMOTVO_REJEI.hashCode());
		result = prime * result + ((CD_TID_CIELO == null) ? 0 : CD_TID_CIELO.hashCode());
		result = prime * result + ((CD_TPO_PGTO == null) ? 0 : CD_TPO_PGTO.hashCode());
		result = prime * result + ((CD_TRACE == null) ? 0 : CD_TRACE.hashCode());
		result = prime * result + ((CD_VDDOR == null) ? 0 : CD_VDDOR.hashCode());
		result = prime * result + ((DS_MOEDA == null) ? 0 : DS_MOEDA.hashCode());
		result = prime * result + ((DS_TP_PGTO == null) ? 0 : DS_TP_PGTO.hashCode());
		result = prime * result + ((DT_APROV_ADQRE == null) ? 0 : DT_APROV_ADQRE.hashCode());
		result = prime * result + ((DT_INCL_PGTO == null) ? 0 : DT_INCL_PGTO.hashCode());
		result = prime * result + ((DT_PGTO == null) ? 0 : DT_PGTO.hashCode());
		result = prime * result + ((DT_ULT_CANC == null) ? 0 : DT_ULT_CANC.hashCode());
		result = prime * result + ((DT_VCTO == null) ? 0 : DT_VCTO.hashCode());
		result = prime * result + ((ID_BCO == null) ? 0 : ID_BCO.hashCode());
		result = prime * result + ((ID_PCERO == null) ? 0 : ID_PCERO.hashCode());
		result = prime * result + ((ID_PGTO_CIELO == null) ? 0 : ID_PGTO_CIELO.hashCode());
		result = prime * result + ((ID_PGTO_EC == null) ? 0 : ID_PGTO_EC.hashCode());
		result = prime * result + ((ID_PLATF == null) ? 0 : ID_PLATF.hashCode());
		result = prime * result + ((NU_DOCTO_PTDOR == null) ? 0 : NU_DOCTO_PTDOR.hashCode());
		result = prime * result + ((NU_NSU_CIELO == null) ? 0 : NU_NSU_CIELO.hashCode());
		result = prime * result + ((NU_PCELA == null) ? 0 : NU_PCELA.hashCode());
		result = prime * result + ((NU_TARGET == null) ? 0 : NU_TARGET.hashCode());
		result = prime * result + ((VR_CANCT == null) ? 0 : VR_CANCT.hashCode());
		result = prime * result + ((VR_ESTRN == null) ? 0 : VR_ESTRN.hashCode());
		result = prime * result + ((VR_FRETE == null) ? 0 : VR_FRETE.hashCode());
		result = prime * result + ((VR_MARKUP_BCO == null) ? 0 : VR_MARKUP_BCO.hashCode());
		result = prime * result + ((VR_MARKUP_PCERO == null) ? 0 : VR_MARKUP_PCERO.hashCode());
		result = prime * result + ((VR_MARKUP_PLATF == null) ? 0 : VR_MARKUP_PLATF.hashCode());
		result = prime * result + ((VR_PG == null) ? 0 : VR_PG.hashCode());
		result = prime * result + ((VR_PGTO == null) ? 0 : VR_PGTO.hashCode());
		result = prime * result + ((VR_ULT_CANC == null) ? 0 : VR_ULT_CANC.hashCode());
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
		PagamentoEntity other = (PagamentoEntity) obj;
		if (CD_AUTRZ == null) {
			if (other.CD_AUTRZ != null)
				return false;
		} else if (!CD_AUTRZ.equals(other.CD_AUTRZ))
			return false;
		if (CD_COMPR == null) {
			if (other.CD_COMPR != null)
				return false;
		} else if (!CD_COMPR.equals(other.CD_COMPR))
			return false;
		if (CD_CUPOM == null) {
			if (other.CD_CUPOM != null)
				return false;
		} else if (!CD_CUPOM.equals(other.CD_CUPOM))
			return false;
		if (CD_FNT_REJEI == null) {
			if (other.CD_FNT_REJEI != null)
				return false;
		} else if (!CD_FNT_REJEI.equals(other.CD_FNT_REJEI))
			return false;
		if (CD_MOTVO_REJEI == null) {
			if (other.CD_MOTVO_REJEI != null)
				return false;
		} else if (!CD_MOTVO_REJEI.equals(other.CD_MOTVO_REJEI))
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
		if (CD_PGTO == null) {
			if (other.CD_PGTO != null)
				return false;
		} else if (!CD_PGTO.equals(other.CD_PGTO))
			return false;
		if (CD_PPRIE == null) {
			if (other.CD_PPRIE != null)
				return false;
		} else if (!CD_PPRIE.equals(other.CD_PPRIE))
			return false;
		if (CD_RESPT == null) {
			if (other.CD_RESPT != null)
				return false;
		} else if (!CD_RESPT.equals(other.CD_RESPT))
			return false;
		if (CD_RRN == null) {
			if (other.CD_RRN != null)
				return false;
		} else if (!CD_RRN.equals(other.CD_RRN))
			return false;
		if (CD_SERIE_LEITR == null) {
			if (other.CD_SERIE_LEITR != null)
				return false;
		} else if (!CD_SERIE_LEITR.equals(other.CD_SERIE_LEITR))
			return false;
		if (CD_STTUS_PGTO == null) {
			if (other.CD_STTUS_PGTO != null)
				return false;
		} else if (!CD_STTUS_PGTO.equals(other.CD_STTUS_PGTO))
			return false;
		if (CD_SUBMOTVO_REJEI == null) {
			if (other.CD_SUBMOTVO_REJEI != null)
				return false;
		} else if (!CD_SUBMOTVO_REJEI.equals(other.CD_SUBMOTVO_REJEI))
			return false;
		if (CD_TID_CIELO == null) {
			if (other.CD_TID_CIELO != null)
				return false;
		} else if (!CD_TID_CIELO.equals(other.CD_TID_CIELO))
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
		if (CD_VDDOR == null) {
			if (other.CD_VDDOR != null)
				return false;
		} else if (!CD_VDDOR.equals(other.CD_VDDOR))
			return false;
		if (DS_MOEDA == null) {
			if (other.DS_MOEDA != null)
				return false;
		} else if (!DS_MOEDA.equals(other.DS_MOEDA))
			return false;
		if (DS_TP_PGTO == null) {
			if (other.DS_TP_PGTO != null)
				return false;
		} else if (!DS_TP_PGTO.equals(other.DS_TP_PGTO))
			return false;
		if (DT_APROV_ADQRE == null) {
			if (other.DT_APROV_ADQRE != null)
				return false;
		} else if (!DT_APROV_ADQRE.equals(other.DT_APROV_ADQRE))
			return false;
		if (DT_INCL_PGTO == null) {
			if (other.DT_INCL_PGTO != null)
				return false;
		} else if (!DT_INCL_PGTO.equals(other.DT_INCL_PGTO))
			return false;
		if (DT_PGTO == null) {
			if (other.DT_PGTO != null)
				return false;
		} else if (!DT_PGTO.equals(other.DT_PGTO))
			return false;
		if (DT_ULT_CANC == null) {
			if (other.DT_ULT_CANC != null)
				return false;
		} else if (!DT_ULT_CANC.equals(other.DT_ULT_CANC))
			return false;
		if (DT_VCTO == null) {
			if (other.DT_VCTO != null)
				return false;
		} else if (!DT_VCTO.equals(other.DT_VCTO))
			return false;
		if (ID_BCO == null) {
			if (other.ID_BCO != null)
				return false;
		} else if (!ID_BCO.equals(other.ID_BCO))
			return false;
		if (ID_PCERO == null) {
			if (other.ID_PCERO != null)
				return false;
		} else if (!ID_PCERO.equals(other.ID_PCERO))
			return false;
		if (ID_PGTO_CIELO == null) {
			if (other.ID_PGTO_CIELO != null)
				return false;
		} else if (!ID_PGTO_CIELO.equals(other.ID_PGTO_CIELO))
			return false;
		if (ID_PGTO_EC == null) {
			if (other.ID_PGTO_EC != null)
				return false;
		} else if (!ID_PGTO_EC.equals(other.ID_PGTO_EC))
			return false;
		if (ID_PLATF == null) {
			if (other.ID_PLATF != null)
				return false;
		} else if (!ID_PLATF.equals(other.ID_PLATF))
			return false;
		if (NU_DOCTO_PTDOR == null) {
			if (other.NU_DOCTO_PTDOR != null)
				return false;
		} else if (!NU_DOCTO_PTDOR.equals(other.NU_DOCTO_PTDOR))
			return false;
		if (NU_NSU_CIELO == null) {
			if (other.NU_NSU_CIELO != null)
				return false;
		} else if (!NU_NSU_CIELO.equals(other.NU_NSU_CIELO))
			return false;
		if (NU_PCELA == null) {
			if (other.NU_PCELA != null)
				return false;
		} else if (!NU_PCELA.equals(other.NU_PCELA))
			return false;
		if (NU_TARGET == null) {
			if (other.NU_TARGET != null)
				return false;
		} else if (!NU_TARGET.equals(other.NU_TARGET))
			return false;
		if (VR_CANCT == null) {
			if (other.VR_CANCT != null)
				return false;
		} else if (!VR_CANCT.equals(other.VR_CANCT))
			return false;
		if (VR_ESTRN == null) {
			if (other.VR_ESTRN != null)
				return false;
		} else if (!VR_ESTRN.equals(other.VR_ESTRN))
			return false;
		if (VR_FRETE == null) {
			if (other.VR_FRETE != null)
				return false;
		} else if (!VR_FRETE.equals(other.VR_FRETE))
			return false;
		if (VR_MARKUP_BCO == null) {
			if (other.VR_MARKUP_BCO != null)
				return false;
		} else if (!VR_MARKUP_BCO.equals(other.VR_MARKUP_BCO))
			return false;
		if (VR_MARKUP_PCERO == null) {
			if (other.VR_MARKUP_PCERO != null)
				return false;
		} else if (!VR_MARKUP_PCERO.equals(other.VR_MARKUP_PCERO))
			return false;
		if (VR_MARKUP_PLATF == null) {
			if (other.VR_MARKUP_PLATF != null)
				return false;
		} else if (!VR_MARKUP_PLATF.equals(other.VR_MARKUP_PLATF))
			return false;
		if (VR_PG == null) {
			if (other.VR_PG != null)
				return false;
		} else if (!VR_PG.equals(other.VR_PG))
			return false;
		if (VR_PGTO == null) {
			if (other.VR_PGTO != null)
				return false;
		} else if (!VR_PGTO.equals(other.VR_PGTO))
			return false;
		if (VR_ULT_CANC == null) {
			if (other.VR_ULT_CANC != null)
				return false;
		} else if (!VR_ULT_CANC.equals(other.VR_ULT_CANC))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PagamentoEntity [CD_PGTO=" + CD_PGTO + ", CD_VDDOR=" + CD_VDDOR + ", CD_PDIDO=" + CD_PDIDO
				+ ", CD_TPO_PGTO=" + CD_TPO_PGTO + ", CD_PPRIE=" + CD_PPRIE + ", VR_PGTO=" + VR_PGTO + ", CD_PCMTO="
				+ CD_PCMTO + ", NU_TARGET=" + NU_TARGET + ", CD_STTUS_PGTO=" + CD_STTUS_PGTO + ", VR_ESTRN=" + VR_ESTRN
				+ ", VR_CANCT=" + VR_CANCT + ", DT_INCL_PGTO=" + DT_INCL_PGTO + ", CD_AUTRZ=" + CD_AUTRZ + ", VR_PG="
				+ VR_PG + ", VR_FRETE=" + VR_FRETE + ", DS_MOEDA=" + DS_MOEDA + ", DS_TP_PGTO=" + DS_TP_PGTO
				+ ", CD_COMPR=" + CD_COMPR + ", DT_VCTO=" + DT_VCTO + ", DT_PGTO=" + DT_PGTO + ", CD_FNT_REJEI="
				+ CD_FNT_REJEI + ", CD_MOTVO_REJEI=" + CD_MOTVO_REJEI + ", CD_SUBMOTVO_REJEI=" + CD_SUBMOTVO_REJEI
				+ ", CD_TRACE=" + CD_TRACE + ", CD_RRN=" + CD_RRN + ", CD_CUPOM=" + CD_CUPOM + ", NU_PCELA=" + NU_PCELA
				+ ", DT_ULT_CANC=" + DT_ULT_CANC + ", VR_ULT_CANC=" + VR_ULT_CANC + ", DT_APROV_ADQRE=" + DT_APROV_ADQRE
				+ ", ID_BCO=" + ID_BCO + ", VR_MARKUP_BCO=" + VR_MARKUP_BCO + ", ID_PLATF=" + ID_PLATF
				+ ", VR_MARKUP_PLATF=" + VR_MARKUP_PLATF + ", ID_PCERO=" + ID_PCERO + ", VR_MARKUP_PCERO="
				+ VR_MARKUP_PCERO + ", ID_PGTO_EC=" + ID_PGTO_EC + ", NU_DOCTO_PTDOR=" + NU_DOCTO_PTDOR + ", CD_RESPT="
				+ CD_RESPT + ", CD_SERIE_LEITR=" + CD_SERIE_LEITR + ", ID_PGTO_CIELO=" + ID_PGTO_CIELO
				+ ", CD_TID_CIELO=" + CD_TID_CIELO + ", NU_NSU_CIELO=" + NU_NSU_CIELO + "]";
	}
		
}
