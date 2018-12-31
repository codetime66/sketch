package br.com.stelo.batch.pagamento.boleto.repository.entity;

import java.io.Serializable;
import java.util.Date;

public class TerminalEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long ID_STELO;     
	private Long NU_TERM;     
	private Long TERM_PPRIO_EC;     
	private String CD_ADQRE;  
	private Long ID_STTUS_TERM;     
	private Date DT_INSTA_TERM;          
	private String USUAR_INCL; 
	private String USUAR_ALT; 
	private Long STTUS;     
	private Date DT_INCL;          
	private Date DT_ALT;          
	private Long ID_TECNO;

	public Long getID_STELO() {
		return ID_STELO;
	}
	public void setID_STELO(Long iD_STELO) {
		ID_STELO = iD_STELO;
	}
	public Long getNU_TERM() {
		return NU_TERM;
	}
	public void setNU_TERM(Long nU_TERM) {
		NU_TERM = nU_TERM;
	}
	public Long getTERM_PPRIO_EC() {
		return TERM_PPRIO_EC;
	}
	public void setTERM_PPRIO_EC(Long tERM_PPRIO_EC) {
		TERM_PPRIO_EC = tERM_PPRIO_EC;
	}
	public String getCD_ADQRE() {
		return CD_ADQRE;
	}
	public void setCD_ADQRE(String cD_ADQRE) {
		CD_ADQRE = cD_ADQRE;
	}
	public Long getID_STTUS_TERM() {
		return ID_STTUS_TERM;
	}
	public void setID_STTUS_TERM(Long iD_STTUS_TERM) {
		ID_STTUS_TERM = iD_STTUS_TERM;
	}
	public Date getDT_INSTA_TERM() {
		return DT_INSTA_TERM;
	}
	public void setDT_INSTA_TERM(Date dT_INSTA_TERM) {
		DT_INSTA_TERM = dT_INSTA_TERM;
	}
	public String getUSUAR_INCL() {
		return USUAR_INCL;
	}
	public void setUSUAR_INCL(String uSUAR_INCL) {
		USUAR_INCL = uSUAR_INCL;
	}
	public String getUSUAR_ALT() {
		return USUAR_ALT;
	}
	public void setUSUAR_ALT(String uSUAR_ALT) {
		USUAR_ALT = uSUAR_ALT;
	}
	public Long getSTTUS() {
		return STTUS;
	}
	public void setSTTUS(Long sTTUS) {
		STTUS = sTTUS;
	}
	public Date getDT_INCL() {
		return DT_INCL;
	}
	public void setDT_INCL(Date dT_INCL) {
		DT_INCL = dT_INCL;
	}
	public Date getDT_ALT() {
		return DT_ALT;
	}
	public void setDT_ALT(Date dT_ALT) {
		DT_ALT = dT_ALT;
	}
	public Long getID_TECNO() {
		return ID_TECNO;
	}
	public void setID_TECNO(Long iD_TECNO) {
		ID_TECNO = iD_TECNO;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CD_ADQRE == null) ? 0 : CD_ADQRE.hashCode());
		result = prime * result + ((DT_ALT == null) ? 0 : DT_ALT.hashCode());
		result = prime * result + ((DT_INCL == null) ? 0 : DT_INCL.hashCode());
		result = prime * result + ((DT_INSTA_TERM == null) ? 0 : DT_INSTA_TERM.hashCode());
		result = prime * result + ((ID_STELO == null) ? 0 : ID_STELO.hashCode());
		result = prime * result + ((ID_STTUS_TERM == null) ? 0 : ID_STTUS_TERM.hashCode());
		result = prime * result + ((ID_TECNO == null) ? 0 : ID_TECNO.hashCode());
		result = prime * result + ((NU_TERM == null) ? 0 : NU_TERM.hashCode());
		result = prime * result + ((STTUS == null) ? 0 : STTUS.hashCode());
		result = prime * result + ((TERM_PPRIO_EC == null) ? 0 : TERM_PPRIO_EC.hashCode());
		result = prime * result + ((USUAR_ALT == null) ? 0 : USUAR_ALT.hashCode());
		result = prime * result + ((USUAR_INCL == null) ? 0 : USUAR_INCL.hashCode());
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
		TerminalEntity other = (TerminalEntity) obj;
		if (CD_ADQRE == null) {
			if (other.CD_ADQRE != null)
				return false;
		} else if (!CD_ADQRE.equals(other.CD_ADQRE))
			return false;
		if (DT_ALT == null) {
			if (other.DT_ALT != null)
				return false;
		} else if (!DT_ALT.equals(other.DT_ALT))
			return false;
		if (DT_INCL == null) {
			if (other.DT_INCL != null)
				return false;
		} else if (!DT_INCL.equals(other.DT_INCL))
			return false;
		if (DT_INSTA_TERM == null) {
			if (other.DT_INSTA_TERM != null)
				return false;
		} else if (!DT_INSTA_TERM.equals(other.DT_INSTA_TERM))
			return false;
		if (ID_STELO == null) {
			if (other.ID_STELO != null)
				return false;
		} else if (!ID_STELO.equals(other.ID_STELO))
			return false;
		if (ID_STTUS_TERM == null) {
			if (other.ID_STTUS_TERM != null)
				return false;
		} else if (!ID_STTUS_TERM.equals(other.ID_STTUS_TERM))
			return false;
		if (ID_TECNO == null) {
			if (other.ID_TECNO != null)
				return false;
		} else if (!ID_TECNO.equals(other.ID_TECNO))
			return false;
		if (NU_TERM == null) {
			if (other.NU_TERM != null)
				return false;
		} else if (!NU_TERM.equals(other.NU_TERM))
			return false;
		if (STTUS == null) {
			if (other.STTUS != null)
				return false;
		} else if (!STTUS.equals(other.STTUS))
			return false;
		if (TERM_PPRIO_EC == null) {
			if (other.TERM_PPRIO_EC != null)
				return false;
		} else if (!TERM_PPRIO_EC.equals(other.TERM_PPRIO_EC))
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
		return true;
	}
	@Override
	public String toString() {
		return "TerminalEntity [ID_STELO=" + ID_STELO + ", NU_TERM=" + NU_TERM + ", TERM_PPRIO_EC=" + TERM_PPRIO_EC
				+ ", CD_ADQRE=" + CD_ADQRE + ", ID_STTUS_TERM=" + ID_STTUS_TERM + ", DT_INSTA_TERM=" + DT_INSTA_TERM
				+ ", USUAR_INCL=" + USUAR_INCL + ", USUAR_ALT=" + USUAR_ALT + ", STTUS=" + STTUS + ", DT_INCL="
				+ DT_INCL + ", DT_ALT=" + DT_ALT + ", ID_TECNO=" + ID_TECNO + "]";
	}
	
}
