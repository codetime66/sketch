package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PagamentoMateraProc{

	private String status;
	private String numeroPedidoEC;
	private String pedidoStelo;
	private String codigoVendedor;
	private String codigoPagamento;
	private String valorBoleto;
	private String dataVencimento;
	private String origemPedido;
	private String codigoComprador;
	private String cpf;
	private String emailComprador;
		
	private TipoRegistroMatera tipoRegistro;
	
}
