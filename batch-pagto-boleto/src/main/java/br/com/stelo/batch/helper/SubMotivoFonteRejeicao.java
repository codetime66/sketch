package br.com.stelo.batch.helper;

public enum SubMotivoFonteRejeicao {
	MANUAL(1, MotivoFonteRejeicao.RISCO.getMtvCodFonteRejeicao(), FonteRejeicao.STELO.getValue(), "Manual"),
	AUTOMATICO(2, MotivoFonteRejeicao.RISCO.getMtvCodFonteRejeicao(), FonteRejeicao.STELO.getValue(), "Automatico"),
	BLACKLIST(1, MotivoFonteRejeicao.BLACKLIST.getMtvCodFonteRejeicao(), FonteRejeicao.STELO.getValue(), "BlackList"),
	BACKOFFICE(1, MotivoFonteRejeicao.BLACKOFFICE.getMtvCodFonteRejeicao(), FonteRejeicao.STELO.getValue(),
			"BackOffice"),
	ERRO_SISTEMICO(4, MotivoFonteRejeicao.ERRO_SISTEMICO.getMtvCodFonteRejeicao(), FonteRejeicao.STELO.getValue(),
			"Erro Sistemico"),
	TOKEN(5, MotivoFonteRejeicao.ERRO_SISTEMICO.getMtvCodFonteRejeicao(), FonteRejeicao.STELO.getValue(),
			"Token Invalido"),
	MAPEAR_RETORNO_HP(1, MotivoFonteRejeicao.MAPEAR_RETORNO_HP.getMtvCodFonteRejeicao(), FonteRejeicao.HP.getValue(),
			"Mapear Retorno HP"),
	CANCELAMENTO_EC(1, MotivoFonteRejeicao.CANCELAMENTO_EC.getMtvCodFonteRejeicao(), FonteRejeicao.EC.getValue(),
			"Cancelamento EC"),
	A_MAIOR(1, MotivoFonteRejeicao.BOLETO.getMtvCodFonteRejeicao(), FonteRejeicao.COMPRADOR.getValue(), "A maior"),
	A_MENOR(2, MotivoFonteRejeicao.BOLETO.getMtvCodFonteRejeicao(), FonteRejeicao.COMPRADOR.getValue(), "A menor"),
	VENCIDO(3, MotivoFonteRejeicao.BOLETO.getMtvCodFonteRejeicao(), FonteRejeicao.COMPRADOR.getValue(), "Vencido"),
	PAGAMENTO_DE_PEDIDO_CANCELADO(5, MotivoFonteRejeicao.BOLETO.getMtvCodFonteRejeicao(),
			FonteRejeicao.COMPRADOR.getValue(), "Pagamento de Pedido Cancelado"),
	PAGAMENTO_DUPLICADO(6, MotivoFonteRejeicao.BOLETO.getMtvCodFonteRejeicao(), FonteRejeicao.COMPRADOR.getValue(),
			"Pagamento Duplicado"),
	PAGAMENTO_NAO_ENCONTRADO(7, MotivoFonteRejeicao.BOLETO.getMtvCodFonteRejeicao(), FonteRejeicao.COMPRADOR.getValue(),
			"Pagamento não encontrado"),
	ESTORNO_CREDITO(7, MotivoFonteRejeicao.SOFTWARE_EXPRESS.getMtvCodFonteRejeicao(),
			FonteRejeicao.SOFTWARE_EXPRESS.getValue(), "Estorno Crédito"),
	ESTORNO_DEBITO(7, MotivoFonteRejeicao.SOFTWARE_EXPRESS.getMtvCodFonteRejeicao(),
			FonteRejeicao.SOFTWARE_EXPRESS.getValue(), "Estorno Débito");

	private SubMotivoFonteRejeicao(final Integer subMtvCodFonteRejeicao, final Integer mtvCodFonteRejeicao,
			final Integer idFonteRejeicao, final String descricao) {
		this.subMtvCodFonteRejeicao = subMtvCodFonteRejeicao;
		this.mtvCodFonteRejeicao = mtvCodFonteRejeicao;
		this.idFonteRejeicao = idFonteRejeicao;
		this.descricao = descricao;
	}

	private Integer subMtvCodFonteRejeicao;

	private Integer mtvCodFonteRejeicao;

	private Integer idFonteRejeicao;

	private String descricao;

	public Integer getSubMtvCodFonteRejeicao() {
		return subMtvCodFonteRejeicao;
	}

	public void setSubMtvCodFonteRejeicao(final Integer subMtvCodFonteRejeicao) {
		this.subMtvCodFonteRejeicao = subMtvCodFonteRejeicao;
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
