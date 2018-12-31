package br.com.stelo.batch.pagamento.boleto.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InconsistenciaBoletoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long ID_BLETO_INCOT;
	private Long NU_PDIDO;
	private String NU_PDIDO_EC;
	private Long CPF;
	private String EMAIL_COMPR;
	private Long CD_VDDOR;
	private BigDecimal VR_PDIDO;
	private BigDecimal VR_BLETO_PG;
	private Date DT_PGTO;
	private Date DT_VCTO;
	private String CD_ORIGE;
	private Integer CD_FONTE_REJEI;
	private Integer CD_MTV_REJEI;
	private Integer CD_SUB_MTV_REJEI;
	private String DS_INCOT;

	public Long getID_BLETO_INCOT() {
		return ID_BLETO_INCOT;
	}
	public void setID_BLETO_INCOT(Long iD_BLETO_INCOT) {
		ID_BLETO_INCOT = iD_BLETO_INCOT;
	}
	public Long getNU_PDIDO() {
		return NU_PDIDO;
	}
	public void setNU_PDIDO(Long nU_PDIDO) {
		NU_PDIDO = nU_PDIDO;
	}
	public String getNU_PDIDO_EC() {
		return NU_PDIDO_EC;
	}
	public void setNU_PDIDO_EC(String nU_PDIDO_EC) {
		NU_PDIDO_EC = nU_PDIDO_EC;
	}
	public Long getCPF() {
		return CPF;
	}
	public void setCPF(Long cPF) {
		CPF = cPF;
	}
	public String getEMAIL_COMPR() {
		return EMAIL_COMPR;
	}
	public void setEMAIL_COMPR(String eMAIL_COMPR) {
		EMAIL_COMPR = eMAIL_COMPR;
	}
	public Long getCD_VDDOR() {
		return CD_VDDOR;
	}
	public void setCD_VDDOR(Long cD_VDDOR) {
		CD_VDDOR = cD_VDDOR;
	}
	public BigDecimal getVR_PDIDO() {
		return VR_PDIDO;
	}
	public void setVR_PDIDO(BigDecimal vR_PDIDO) {
		VR_PDIDO = vR_PDIDO;
	}
	public BigDecimal getVR_BLETO_PG() {
		return VR_BLETO_PG;
	}
	public void setVR_BLETO_PG(BigDecimal vR_BLETO_PG) {
		VR_BLETO_PG = vR_BLETO_PG;
	}
	public Date getDT_PGTO() {
		return DT_PGTO;
	}
	public void setDT_PGTO(Date dT_PGTO) {
		DT_PGTO = dT_PGTO;
	}
	public Date getDT_VCTO() {
		return DT_VCTO;
	}
	public void setDT_VCTO(Date dT_VCTO) {
		DT_VCTO = dT_VCTO;
	}
	public String getCD_ORIGE() {
		return CD_ORIGE;
	}
	public void setCD_ORIGE(String cD_ORIGE) {
		CD_ORIGE = cD_ORIGE;
	}
	public Integer getCD_FONTE_REJEI() {
		return CD_FONTE_REJEI;
	}
	public void setCD_FONTE_REJEI(Integer cD_FONTE_REJEI) {
		CD_FONTE_REJEI = cD_FONTE_REJEI;
	}
	public Integer getCD_MTV_REJEI() {
		return CD_MTV_REJEI;
	}
	public void setCD_MTV_REJEI(Integer cD_MTV_REJEI) {
		CD_MTV_REJEI = cD_MTV_REJEI;
	}
	public Integer getCD_SUB_MTV_REJEI() {
		return CD_SUB_MTV_REJEI;
	}
	public void setCD_SUB_MTV_REJEI(Integer cD_SUB_MTV_REJEI) {
		CD_SUB_MTV_REJEI = cD_SUB_MTV_REJEI;
	}
	public String getDS_INCOT() {
		return DS_INCOT;
	}
	public void setDS_INCOT(String dS_INCOT) {
		DS_INCOT = dS_INCOT;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CD_FONTE_REJEI == null) ? 0 : CD_FONTE_REJEI.hashCode());
		result = prime * result + ((CD_MTV_REJEI == null) ? 0 : CD_MTV_REJEI.hashCode());
		result = prime * result + ((CD_ORIGE == null) ? 0 : CD_ORIGE.hashCode());
		result = prime * result + ((CD_SUB_MTV_REJEI == null) ? 0 : CD_SUB_MTV_REJEI.hashCode());
		result = prime * result + ((CD_VDDOR == null) ? 0 : CD_VDDOR.hashCode());
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ((DS_INCOT == null) ? 0 : DS_INCOT.hashCode());
		result = prime * result + ((DT_PGTO == null) ? 0 : DT_PGTO.hashCode());
		result = prime * result + ((DT_VCTO == null) ? 0 : DT_VCTO.hashCode());
		result = prime * result + ((EMAIL_COMPR == null) ? 0 : EMAIL_COMPR.hashCode());
		result = prime * result + ((ID_BLETO_INCOT == null) ? 0 : ID_BLETO_INCOT.hashCode());
		result = prime * result + ((NU_PDIDO == null) ? 0 : NU_PDIDO.hashCode());
		result = prime * result + ((NU_PDIDO_EC == null) ? 0 : NU_PDIDO_EC.hashCode());
		result = prime * result + ((VR_BLETO_PG == null) ? 0 : VR_BLETO_PG.hashCode());
		result = prime * result + ((VR_PDIDO == null) ? 0 : VR_PDIDO.hashCode());
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
		InconsistenciaBoletoEntity other = (InconsistenciaBoletoEntity) obj;
		if (CD_FONTE_REJEI == null) {
			if (other.CD_FONTE_REJEI != null)
				return false;
		} else if (!CD_FONTE_REJEI.equals(other.CD_FONTE_REJEI))
			return false;
		if (CD_MTV_REJEI == null) {
			if (other.CD_MTV_REJEI != null)
				return false;
		} else if (!CD_MTV_REJEI.equals(other.CD_MTV_REJEI))
			return false;
		if (CD_ORIGE == null) {
			if (other.CD_ORIGE != null)
				return false;
		} else if (!CD_ORIGE.equals(other.CD_ORIGE))
			return false;
		if (CD_SUB_MTV_REJEI == null) {
			if (other.CD_SUB_MTV_REJEI != null)
				return false;
		} else if (!CD_SUB_MTV_REJEI.equals(other.CD_SUB_MTV_REJEI))
			return false;
		if (CD_VDDOR == null) {
			if (other.CD_VDDOR != null)
				return false;
		} else if (!CD_VDDOR.equals(other.CD_VDDOR))
			return false;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (DS_INCOT == null) {
			if (other.DS_INCOT != null)
				return false;
		} else if (!DS_INCOT.equals(other.DS_INCOT))
			return false;
		if (DT_PGTO == null) {
			if (other.DT_PGTO != null)
				return false;
		} else if (!DT_PGTO.equals(other.DT_PGTO))
			return false;
		if (DT_VCTO == null) {
			if (other.DT_VCTO != null)
				return false;
		} else if (!DT_VCTO.equals(other.DT_VCTO))
			return false;
		if (EMAIL_COMPR == null) {
			if (other.EMAIL_COMPR != null)
				return false;
		} else if (!EMAIL_COMPR.equals(other.EMAIL_COMPR))
			return false;
		if (ID_BLETO_INCOT == null) {
			if (other.ID_BLETO_INCOT != null)
				return false;
		} else if (!ID_BLETO_INCOT.equals(other.ID_BLETO_INCOT))
			return false;
		if (NU_PDIDO == null) {
			if (other.NU_PDIDO != null)
				return false;
		} else if (!NU_PDIDO.equals(other.NU_PDIDO))
			return false;
		if (NU_PDIDO_EC == null) {
			if (other.NU_PDIDO_EC != null)
				return false;
		} else if (!NU_PDIDO_EC.equals(other.NU_PDIDO_EC))
			return false;
		if (VR_BLETO_PG == null) {
			if (other.VR_BLETO_PG != null)
				return false;
		} else if (!VR_BLETO_PG.equals(other.VR_BLETO_PG))
			return false;
		if (VR_PDIDO == null) {
			if (other.VR_PDIDO != null)
				return false;
		} else if (!VR_PDIDO.equals(other.VR_PDIDO))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InconsistenciaBoleto [ID_BLETO_INCOT=" + ID_BLETO_INCOT + ", NU_PDIDO=" + NU_PDIDO + ", NU_PDIDO_EC="
				+ NU_PDIDO_EC + ", CPF=" + CPF + ", EMAIL_COMPR=" + EMAIL_COMPR + ", CD_VDDOR=" + CD_VDDOR
				+ ", VR_PDIDO=" + VR_PDIDO + ", VR_BLETO_PG=" + VR_BLETO_PG + ", DT_PGTO=" + DT_PGTO + ", DT_VCTO="
				+ DT_VCTO + ", CD_ORIGE=" + CD_ORIGE + ", CD_FONTE_REJEI=" + CD_FONTE_REJEI + ", CD_MTV_REJEI="
				+ CD_MTV_REJEI + ", CD_SUB_MTV_REJEI=" + CD_SUB_MTV_REJEI + ", DS_INCOT=" + DS_INCOT + "]";
	}
	
}
