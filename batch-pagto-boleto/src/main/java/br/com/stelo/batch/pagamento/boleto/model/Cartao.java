package br.com.stelo.batch.pagamento.boleto.model;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Cartao {

	protected String id;
	protected String idTemporario;
	protected String descricaoCartao;
	protected Integer status;
	protected Boolean preferencial;
	protected Integer tipoCartao;
	protected String apelidoCartao;
	protected String numeroCartao;
	protected String numeroCartaoMascarado;
	protected String nomePortador;
	protected Date dataExpirar;
	protected Integer diaVencimento;
	protected String cvv;
	protected Boolean inclusaoCartao;
	protected Integer tipoBandeira;
	protected Integer codigoDoBanco;
	protected Endereco enderecoCobranca;
	protected String nomePortadorCriptografado;
	protected String numeroCartaoCriptografado;
	protected String cvvCriptografado;
	protected String dataExpirarCriptografada;
	protected FonteDados fonteDados;
	protected Date dataCriacao;
	protected Date dataInativacao;
	protected Boolean validado;
}