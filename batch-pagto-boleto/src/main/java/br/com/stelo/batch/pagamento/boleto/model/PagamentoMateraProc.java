package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PagamentoMateraProc{
	public String status;
	public String numeroPedidoEC;
	public String pedidoStelo;
	public String codigoVendedor;
	public String codigoPagamento;
	public String valorBoleto;
	public String dataVencimento;
	public String origemPedido;
	public String codigoComprador;
	public String cpf;
	public String emailComprador;
	
	private TipoRegistroMatera tipoRegistro;
}
