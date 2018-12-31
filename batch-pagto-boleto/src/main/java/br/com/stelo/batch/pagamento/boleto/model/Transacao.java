package br.com.stelo.batch.pagamento.boleto.model;

import java.math.BigInteger;
import java.util.Date;

public class Transacao {

    protected Long codigoTransacao;
    protected Integer tipoMensagem;
    protected Double valorTransacao;
    protected Date dataTransacao;
    protected Date dataTransmissao;
    protected Long codigoProcessamento;
    protected Integer codigoCredenciadora;
    protected Integer codigoTrace;
    protected String codigoRRN;
    protected String codigoAutorizador;
    protected String codigoRespostaAutorizacao;
    protected String codigoTerminal;
    protected String codigoResposta;
    protected Integer codigoRejeicao;
    protected String motivoRejeicao;
    protected String codigoRetorno;
    protected BigInteger codigoPedido;
    protected BigInteger codigoMoeda;
    protected BigInteger codigoCupom;
    protected String descricaoRetorno;
    protected String formaCaptura;
    protected Date dataCaptura;
    protected Long numeroIdentificacaoRede;
    protected Long numeroSequencialCartao;
    protected Long posConditionCode;
    protected Long idAdquirente;
    protected Long trilha2Cartao;
    protected String indicadorPreAutorizacao;
    protected String dadosTransacaoOriginal;
    protected String numeroTerminal;
    protected String planoPagamento;
    protected Integer quantidadeParcelas;
    protected String iata;
    protected String icc;
    protected String indicativoCVV;
    protected String canalOrigem;
	public Long getCodigoTransacao() {
		return codigoTransacao;
	}
	public void setCodigoTransacao(Long codigoTransacao) {
		this.codigoTransacao = codigoTransacao;
	}
	public Integer getTipoMensagem() {
		return tipoMensagem;
	}
	public void setTipoMensagem(Integer tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}
	public Double getValorTransacao() {
		return valorTransacao;
	}
	public void setValorTransacao(Double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}
	public Date getDataTransacao() {
		return dataTransacao;
	}
	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	public Date getDataTransmissao() {
		return dataTransmissao;
	}
	public void setDataTransmissao(Date dataTransmissao) {
		this.dataTransmissao = dataTransmissao;
	}
	public Long getCodigoProcessamento() {
		return codigoProcessamento;
	}
	public void setCodigoProcessamento(Long codigoProcessamento) {
		this.codigoProcessamento = codigoProcessamento;
	}
	public Integer getCodigoCredenciadora() {
		return codigoCredenciadora;
	}
	public void setCodigoCredenciadora(Integer codigoCredenciadora) {
		this.codigoCredenciadora = codigoCredenciadora;
	}
	public Integer getCodigoTrace() {
		return codigoTrace;
	}
	public void setCodigoTrace(Integer codigoTrace) {
		this.codigoTrace = codigoTrace;
	}
	public String getCodigoRRN() {
		return codigoRRN;
	}
	public void setCodigoRRN(String codigoRRN) {
		this.codigoRRN = codigoRRN;
	}
	public String getCodigoAutorizador() {
		return codigoAutorizador;
	}
	public void setCodigoAutorizador(String codigoAutorizador) {
		this.codigoAutorizador = codigoAutorizador;
	}
	public String getCodigoRespostaAutorizacao() {
		return codigoRespostaAutorizacao;
	}
	public void setCodigoRespostaAutorizacao(String codigoRespostaAutorizacao) {
		this.codigoRespostaAutorizacao = codigoRespostaAutorizacao;
	}
	public String getCodigoTerminal() {
		return codigoTerminal;
	}
	public void setCodigoTerminal(String codigoTerminal) {
		this.codigoTerminal = codigoTerminal;
	}
	public String getCodigoResposta() {
		return codigoResposta;
	}
	public void setCodigoResposta(String codigoResposta) {
		this.codigoResposta = codigoResposta;
	}
	public Integer getCodigoRejeicao() {
		return codigoRejeicao;
	}
	public void setCodigoRejeicao(Integer codigoRejeicao) {
		this.codigoRejeicao = codigoRejeicao;
	}
	public String getMotivoRejeicao() {
		return motivoRejeicao;
	}
	public void setMotivoRejeicao(String motivoRejeicao) {
		this.motivoRejeicao = motivoRejeicao;
	}
	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	public BigInteger getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(BigInteger codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public BigInteger getCodigoMoeda() {
		return codigoMoeda;
	}
	public void setCodigoMoeda(BigInteger codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}
	public BigInteger getCodigoCupom() {
		return codigoCupom;
	}
	public void setCodigoCupom(BigInteger codigoCupom) {
		this.codigoCupom = codigoCupom;
	}
	public String getDescricaoRetorno() {
		return descricaoRetorno;
	}
	public void setDescricaoRetorno(String descricaoRetorno) {
		this.descricaoRetorno = descricaoRetorno;
	}
	public String getFormaCaptura() {
		return formaCaptura;
	}
	public void setFormaCaptura(String formaCaptura) {
		this.formaCaptura = formaCaptura;
	}
	public Date getDataCaptura() {
		return dataCaptura;
	}
	public void setDataCaptura(Date dataCaptura) {
		this.dataCaptura = dataCaptura;
	}
	public Long getNumeroIdentificacaoRede() {
		return numeroIdentificacaoRede;
	}
	public void setNumeroIdentificacaoRede(Long numeroIdentificacaoRede) {
		this.numeroIdentificacaoRede = numeroIdentificacaoRede;
	}
	public Long getNumeroSequencialCartao() {
		return numeroSequencialCartao;
	}
	public void setNumeroSequencialCartao(Long numeroSequencialCartao) {
		this.numeroSequencialCartao = numeroSequencialCartao;
	}
	public Long getPosConditionCode() {
		return posConditionCode;
	}
	public void setPosConditionCode(Long posConditionCode) {
		this.posConditionCode = posConditionCode;
	}
	public Long getIdAdquirente() {
		return idAdquirente;
	}
	public void setIdAdquirente(Long idAdquirente) {
		this.idAdquirente = idAdquirente;
	}
	public Long getTrilha2Cartao() {
		return trilha2Cartao;
	}
	public void setTrilha2Cartao(Long trilha2Cartao) {
		this.trilha2Cartao = trilha2Cartao;
	}
	public String getIndicadorPreAutorizacao() {
		return indicadorPreAutorizacao;
	}
	public void setIndicadorPreAutorizacao(String indicadorPreAutorizacao) {
		this.indicadorPreAutorizacao = indicadorPreAutorizacao;
	}
	public String getDadosTransacaoOriginal() {
		return dadosTransacaoOriginal;
	}
	public void setDadosTransacaoOriginal(String dadosTransacaoOriginal) {
		this.dadosTransacaoOriginal = dadosTransacaoOriginal;
	}
	public String getNumeroTerminal() {
		return numeroTerminal;
	}
	public void setNumeroTerminal(String numeroTerminal) {
		this.numeroTerminal = numeroTerminal;
	}
	public String getPlanoPagamento() {
		return planoPagamento;
	}
	public void setPlanoPagamento(String planoPagamento) {
		this.planoPagamento = planoPagamento;
	}
	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getIcc() {
		return icc;
	}
	public void setIcc(String icc) {
		this.icc = icc;
	}
	public String getIndicativoCVV() {
		return indicativoCVV;
	}
	public void setIndicativoCVV(String indicativoCVV) {
		this.indicativoCVV = indicativoCVV;
	}
	public String getCanalOrigem() {
		return canalOrigem;
	}
	public void setCanalOrigem(String canalOrigem) {
		this.canalOrigem = canalOrigem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((canalOrigem == null) ? 0 : canalOrigem.hashCode());
		result = prime * result + ((codigoAutorizador == null) ? 0 : codigoAutorizador.hashCode());
		result = prime * result + ((codigoCredenciadora == null) ? 0 : codigoCredenciadora.hashCode());
		result = prime * result + ((codigoCupom == null) ? 0 : codigoCupom.hashCode());
		result = prime * result + ((codigoMoeda == null) ? 0 : codigoMoeda.hashCode());
		result = prime * result + ((codigoPedido == null) ? 0 : codigoPedido.hashCode());
		result = prime * result + ((codigoProcessamento == null) ? 0 : codigoProcessamento.hashCode());
		result = prime * result + ((codigoRRN == null) ? 0 : codigoRRN.hashCode());
		result = prime * result + ((codigoRejeicao == null) ? 0 : codigoRejeicao.hashCode());
		result = prime * result + ((codigoResposta == null) ? 0 : codigoResposta.hashCode());
		result = prime * result + ((codigoRespostaAutorizacao == null) ? 0 : codigoRespostaAutorizacao.hashCode());
		result = prime * result + ((codigoRetorno == null) ? 0 : codigoRetorno.hashCode());
		result = prime * result + ((codigoTerminal == null) ? 0 : codigoTerminal.hashCode());
		result = prime * result + ((codigoTrace == null) ? 0 : codigoTrace.hashCode());
		result = prime * result + ((codigoTransacao == null) ? 0 : codigoTransacao.hashCode());
		result = prime * result + ((dadosTransacaoOriginal == null) ? 0 : dadosTransacaoOriginal.hashCode());
		result = prime * result + ((dataCaptura == null) ? 0 : dataCaptura.hashCode());
		result = prime * result + ((dataTransacao == null) ? 0 : dataTransacao.hashCode());
		result = prime * result + ((dataTransmissao == null) ? 0 : dataTransmissao.hashCode());
		result = prime * result + ((descricaoRetorno == null) ? 0 : descricaoRetorno.hashCode());
		result = prime * result + ((formaCaptura == null) ? 0 : formaCaptura.hashCode());
		result = prime * result + ((iata == null) ? 0 : iata.hashCode());
		result = prime * result + ((icc == null) ? 0 : icc.hashCode());
		result = prime * result + ((idAdquirente == null) ? 0 : idAdquirente.hashCode());
		result = prime * result + ((indicadorPreAutorizacao == null) ? 0 : indicadorPreAutorizacao.hashCode());
		result = prime * result + ((indicativoCVV == null) ? 0 : indicativoCVV.hashCode());
		result = prime * result + ((motivoRejeicao == null) ? 0 : motivoRejeicao.hashCode());
		result = prime * result + ((numeroIdentificacaoRede == null) ? 0 : numeroIdentificacaoRede.hashCode());
		result = prime * result + ((numeroSequencialCartao == null) ? 0 : numeroSequencialCartao.hashCode());
		result = prime * result + ((numeroTerminal == null) ? 0 : numeroTerminal.hashCode());
		result = prime * result + ((planoPagamento == null) ? 0 : planoPagamento.hashCode());
		result = prime * result + ((posConditionCode == null) ? 0 : posConditionCode.hashCode());
		result = prime * result + ((quantidadeParcelas == null) ? 0 : quantidadeParcelas.hashCode());
		result = prime * result + ((tipoMensagem == null) ? 0 : tipoMensagem.hashCode());
		result = prime * result + ((trilha2Cartao == null) ? 0 : trilha2Cartao.hashCode());
		result = prime * result + ((valorTransacao == null) ? 0 : valorTransacao.hashCode());
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
		Transacao other = (Transacao) obj;
		if (canalOrigem == null) {
			if (other.canalOrigem != null)
				return false;
		} else if (!canalOrigem.equals(other.canalOrigem))
			return false;
		if (codigoAutorizador == null) {
			if (other.codigoAutorizador != null)
				return false;
		} else if (!codigoAutorizador.equals(other.codigoAutorizador))
			return false;
		if (codigoCredenciadora == null) {
			if (other.codigoCredenciadora != null)
				return false;
		} else if (!codigoCredenciadora.equals(other.codigoCredenciadora))
			return false;
		if (codigoCupom == null) {
			if (other.codigoCupom != null)
				return false;
		} else if (!codigoCupom.equals(other.codigoCupom))
			return false;
		if (codigoMoeda == null) {
			if (other.codigoMoeda != null)
				return false;
		} else if (!codigoMoeda.equals(other.codigoMoeda))
			return false;
		if (codigoPedido == null) {
			if (other.codigoPedido != null)
				return false;
		} else if (!codigoPedido.equals(other.codigoPedido))
			return false;
		if (codigoProcessamento == null) {
			if (other.codigoProcessamento != null)
				return false;
		} else if (!codigoProcessamento.equals(other.codigoProcessamento))
			return false;
		if (codigoRRN == null) {
			if (other.codigoRRN != null)
				return false;
		} else if (!codigoRRN.equals(other.codigoRRN))
			return false;
		if (codigoRejeicao == null) {
			if (other.codigoRejeicao != null)
				return false;
		} else if (!codigoRejeicao.equals(other.codigoRejeicao))
			return false;
		if (codigoResposta == null) {
			if (other.codigoResposta != null)
				return false;
		} else if (!codigoResposta.equals(other.codigoResposta))
			return false;
		if (codigoRespostaAutorizacao == null) {
			if (other.codigoRespostaAutorizacao != null)
				return false;
		} else if (!codigoRespostaAutorizacao.equals(other.codigoRespostaAutorizacao))
			return false;
		if (codigoRetorno == null) {
			if (other.codigoRetorno != null)
				return false;
		} else if (!codigoRetorno.equals(other.codigoRetorno))
			return false;
		if (codigoTerminal == null) {
			if (other.codigoTerminal != null)
				return false;
		} else if (!codigoTerminal.equals(other.codigoTerminal))
			return false;
		if (codigoTrace == null) {
			if (other.codigoTrace != null)
				return false;
		} else if (!codigoTrace.equals(other.codigoTrace))
			return false;
		if (codigoTransacao == null) {
			if (other.codigoTransacao != null)
				return false;
		} else if (!codigoTransacao.equals(other.codigoTransacao))
			return false;
		if (dadosTransacaoOriginal == null) {
			if (other.dadosTransacaoOriginal != null)
				return false;
		} else if (!dadosTransacaoOriginal.equals(other.dadosTransacaoOriginal))
			return false;
		if (dataCaptura == null) {
			if (other.dataCaptura != null)
				return false;
		} else if (!dataCaptura.equals(other.dataCaptura))
			return false;
		if (dataTransacao == null) {
			if (other.dataTransacao != null)
				return false;
		} else if (!dataTransacao.equals(other.dataTransacao))
			return false;
		if (dataTransmissao == null) {
			if (other.dataTransmissao != null)
				return false;
		} else if (!dataTransmissao.equals(other.dataTransmissao))
			return false;
		if (descricaoRetorno == null) {
			if (other.descricaoRetorno != null)
				return false;
		} else if (!descricaoRetorno.equals(other.descricaoRetorno))
			return false;
		if (formaCaptura == null) {
			if (other.formaCaptura != null)
				return false;
		} else if (!formaCaptura.equals(other.formaCaptura))
			return false;
		if (iata == null) {
			if (other.iata != null)
				return false;
		} else if (!iata.equals(other.iata))
			return false;
		if (icc == null) {
			if (other.icc != null)
				return false;
		} else if (!icc.equals(other.icc))
			return false;
		if (idAdquirente == null) {
			if (other.idAdquirente != null)
				return false;
		} else if (!idAdquirente.equals(other.idAdquirente))
			return false;
		if (indicadorPreAutorizacao == null) {
			if (other.indicadorPreAutorizacao != null)
				return false;
		} else if (!indicadorPreAutorizacao.equals(other.indicadorPreAutorizacao))
			return false;
		if (indicativoCVV == null) {
			if (other.indicativoCVV != null)
				return false;
		} else if (!indicativoCVV.equals(other.indicativoCVV))
			return false;
		if (motivoRejeicao == null) {
			if (other.motivoRejeicao != null)
				return false;
		} else if (!motivoRejeicao.equals(other.motivoRejeicao))
			return false;
		if (numeroIdentificacaoRede == null) {
			if (other.numeroIdentificacaoRede != null)
				return false;
		} else if (!numeroIdentificacaoRede.equals(other.numeroIdentificacaoRede))
			return false;
		if (numeroSequencialCartao == null) {
			if (other.numeroSequencialCartao != null)
				return false;
		} else if (!numeroSequencialCartao.equals(other.numeroSequencialCartao))
			return false;
		if (numeroTerminal == null) {
			if (other.numeroTerminal != null)
				return false;
		} else if (!numeroTerminal.equals(other.numeroTerminal))
			return false;
		if (planoPagamento == null) {
			if (other.planoPagamento != null)
				return false;
		} else if (!planoPagamento.equals(other.planoPagamento))
			return false;
		if (posConditionCode == null) {
			if (other.posConditionCode != null)
				return false;
		} else if (!posConditionCode.equals(other.posConditionCode))
			return false;
		if (quantidadeParcelas == null) {
			if (other.quantidadeParcelas != null)
				return false;
		} else if (!quantidadeParcelas.equals(other.quantidadeParcelas))
			return false;
		if (tipoMensagem == null) {
			if (other.tipoMensagem != null)
				return false;
		} else if (!tipoMensagem.equals(other.tipoMensagem))
			return false;
		if (trilha2Cartao == null) {
			if (other.trilha2Cartao != null)
				return false;
		} else if (!trilha2Cartao.equals(other.trilha2Cartao))
			return false;
		if (valorTransacao == null) {
			if (other.valorTransacao != null)
				return false;
		} else if (!valorTransacao.equals(other.valorTransacao))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Transacao [codigoTransacao=" + codigoTransacao + ", tipoMensagem=" + tipoMensagem + ", valorTransacao="
				+ valorTransacao + ", dataTransacao=" + dataTransacao + ", dataTransmissao=" + dataTransmissao
				+ ", codigoProcessamento=" + codigoProcessamento + ", codigoCredenciadora=" + codigoCredenciadora
				+ ", codigoTrace=" + codigoTrace + ", codigoRRN=" + codigoRRN + ", codigoAutorizador="
				+ codigoAutorizador + ", codigoRespostaAutorizacao=" + codigoRespostaAutorizacao + ", codigoTerminal="
				+ codigoTerminal + ", codigoResposta=" + codigoResposta + ", codigoRejeicao=" + codigoRejeicao
				+ ", motivoRejeicao=" + motivoRejeicao + ", codigoRetorno=" + codigoRetorno + ", codigoPedido="
				+ codigoPedido + ", codigoMoeda=" + codigoMoeda + ", codigoCupom=" + codigoCupom + ", descricaoRetorno="
				+ descricaoRetorno + ", formaCaptura=" + formaCaptura + ", dataCaptura=" + dataCaptura
				+ ", numeroIdentificacaoRede=" + numeroIdentificacaoRede + ", numeroSequencialCartao="
				+ numeroSequencialCartao + ", posConditionCode=" + posConditionCode + ", idAdquirente=" + idAdquirente
				+ ", trilha2Cartao=" + trilha2Cartao + ", indicadorPreAutorizacao=" + indicadorPreAutorizacao
				+ ", dadosTransacaoOriginal=" + dadosTransacaoOriginal + ", numeroTerminal=" + numeroTerminal
				+ ", planoPagamento=" + planoPagamento + ", quantidadeParcelas=" + quantidadeParcelas + ", iata=" + iata
				+ ", icc=" + icc + ", indicativoCVV=" + indicativoCVV + ", canalOrigem=" + canalOrigem + "]";
	}

    
}
