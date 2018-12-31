package br.com.stelo.batch.pagamento.boleto.repository.entity;

import java.io.Serializable;
import java.util.Date;

public class VendaEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long CD_VDDOR;
	private Long CD_PDIDO;
	private Date DT_INCL_VDA;
	private String CD_PDIDO_ESTBL_COML;
	private String DS_VDA_ESTBL_COML;
	private Double VR_TOT_VDA;
	private Character IC_RETRD_LJ;
	private Double VR_TOT_DESC_PDIDO;
	
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
	public Date getDT_INCL_VDA() {
		return DT_INCL_VDA;
	}
	public void setDT_INCL_VDA(Date dT_INCL_VDA) {
		DT_INCL_VDA = dT_INCL_VDA;
	}
	public String getCD_PDIDO_ESTBL_COML() {
		return CD_PDIDO_ESTBL_COML;
	}
	public void setCD_PDIDO_ESTBL_COML(String cD_PDIDO_ESTBL_COML) {
		CD_PDIDO_ESTBL_COML = cD_PDIDO_ESTBL_COML;
	}
	public String getDS_VDA_ESTBL_COML() {
		return DS_VDA_ESTBL_COML;
	}
	public void setDS_VDA_ESTBL_COML(String dS_VDA_ESTBL_COML) {
		DS_VDA_ESTBL_COML = dS_VDA_ESTBL_COML;
	}
	public Double getVR_TOT_VDA() {
		return VR_TOT_VDA;
	}
	public void setVR_TOT_VDA(Double vR_TOT_VDA) {
		VR_TOT_VDA = vR_TOT_VDA;
	}
	public Character getIC_RETRD_LJ() {
		return IC_RETRD_LJ;
	}
	public void setIC_RETRD_LJ(Character iC_RETRD_LJ) {
		IC_RETRD_LJ = iC_RETRD_LJ;
	}
	public Double getVR_TOT_DESC_PDIDO() {
		return VR_TOT_DESC_PDIDO;
	}
	public void setVR_TOT_DESC_PDIDO(Double vR_TOT_DESC_PDIDO) {
		VR_TOT_DESC_PDIDO = vR_TOT_DESC_PDIDO;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CD_PDIDO == null) ? 0 : CD_PDIDO.hashCode());
		result = prime * result + ((CD_PDIDO_ESTBL_COML == null) ? 0 : CD_PDIDO_ESTBL_COML.hashCode());
		result = prime * result + ((CD_VDDOR == null) ? 0 : CD_VDDOR.hashCode());
		result = prime * result + ((DS_VDA_ESTBL_COML == null) ? 0 : DS_VDA_ESTBL_COML.hashCode());
		result = prime * result + ((DT_INCL_VDA == null) ? 0 : DT_INCL_VDA.hashCode());
		result = prime * result + ((IC_RETRD_LJ == null) ? 0 : IC_RETRD_LJ.hashCode());
		result = prime * result + ((VR_TOT_DESC_PDIDO == null) ? 0 : VR_TOT_DESC_PDIDO.hashCode());
		result = prime * result + ((VR_TOT_VDA == null) ? 0 : VR_TOT_VDA.hashCode());
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
		VendaEntity other = (VendaEntity) obj;
		if (CD_PDIDO == null) {
			if (other.CD_PDIDO != null)
				return false;
		} else if (!CD_PDIDO.equals(other.CD_PDIDO))
			return false;
		if (CD_PDIDO_ESTBL_COML == null) {
			if (other.CD_PDIDO_ESTBL_COML != null)
				return false;
		} else if (!CD_PDIDO_ESTBL_COML.equals(other.CD_PDIDO_ESTBL_COML))
			return false;
		if (CD_VDDOR == null) {
			if (other.CD_VDDOR != null)
				return false;
		} else if (!CD_VDDOR.equals(other.CD_VDDOR))
			return false;
		if (DS_VDA_ESTBL_COML == null) {
			if (other.DS_VDA_ESTBL_COML != null)
				return false;
		} else if (!DS_VDA_ESTBL_COML.equals(other.DS_VDA_ESTBL_COML))
			return false;
		if (DT_INCL_VDA == null) {
			if (other.DT_INCL_VDA != null)
				return false;
		} else if (!DT_INCL_VDA.equals(other.DT_INCL_VDA))
			return false;
		if (IC_RETRD_LJ == null) {
			if (other.IC_RETRD_LJ != null)
				return false;
		} else if (!IC_RETRD_LJ.equals(other.IC_RETRD_LJ))
			return false;
		if (VR_TOT_DESC_PDIDO == null) {
			if (other.VR_TOT_DESC_PDIDO != null)
				return false;
		} else if (!VR_TOT_DESC_PDIDO.equals(other.VR_TOT_DESC_PDIDO))
			return false;
		if (VR_TOT_VDA == null) {
			if (other.VR_TOT_VDA != null)
				return false;
		} else if (!VR_TOT_VDA.equals(other.VR_TOT_VDA))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "VendaEntity [CD_VDDOR=" + CD_VDDOR + ", CD_PDIDO=" + CD_PDIDO + ", DT_INCL_VDA=" + DT_INCL_VDA
				+ ", CD_PDIDO_ESTBL_COML=" + CD_PDIDO_ESTBL_COML + ", DS_VDA_ESTBL_COML=" + DS_VDA_ESTBL_COML
				+ ", VR_TOT_VDA=" + VR_TOT_VDA + ", IC_RETRD_LJ=" + IC_RETRD_LJ + ", VR_TOT_DESC_PDIDO="
				+ VR_TOT_DESC_PDIDO + "]";
	}

}
