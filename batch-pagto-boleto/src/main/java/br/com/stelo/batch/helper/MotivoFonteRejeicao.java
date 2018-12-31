package br.com.stelo.batch.helper;

public enum MotivoFonteRejeicao {
	RISCO(1, 1, "RISCO"), BLACKLIST(2, 1, "BLACKLIST"), BLACKOFFICE(3, 1, "BLACKOFFICE"),
	ERRO_SISTEMICO(4, 1, "ERRO_SISTEMICO"), MAPEAR_RETORNO_HP(1, 2, "MAPEAR_RETORNO_HP"),
	CANCELAMENTO_EC(1, 3, "CANCELAMENTO_EC"), BOLETO(1, 4, "BOLETO"), SOFTWARE_EXPRESS(1, 6, "SOFTWARE_EXPRESS");

	private Integer mtvCodFonteRejeicao;

	private Integer idFonteRejeicao;

	private String descricao;

	private MotivoFonteRejeicao(final Integer mtvCodFonteRejeicao, final Integer idFonteRejeicao,
			final String descricao) {
		this.mtvCodFonteRejeicao = mtvCodFonteRejeicao;
		this.idFonteRejeicao = idFonteRejeicao;
		this.descricao = descricao;
	}

	public Integer getMtvCodFonteRejeicao() {
		return mtvCodFonteRejeicao;
	}

	public void setMtvCodFonteRejeicao(final Integer mtvCodFonteRejeicao) {
		this.mtvCodFonteRejeicao = mtvCodFonteRejeicao;
	}

	public Integer getIdFonteRejeicao() {
		return idFonteRejeicao;
	}

	public void setIdFonteRejeicao(final Integer idFonteRejeicao) {
		this.idFonteRejeicao = idFonteRejeicao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}
}
