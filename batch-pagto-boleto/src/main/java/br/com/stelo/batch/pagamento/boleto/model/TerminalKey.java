package br.com.stelo.batch.pagamento.boleto.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TerminalKey {
	private Long codigoVendedor;
	private Long idTecnologia;
}