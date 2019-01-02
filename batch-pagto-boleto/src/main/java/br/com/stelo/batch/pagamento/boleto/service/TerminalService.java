package br.com.stelo.batch.pagamento.boleto.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stelo.batch.pagamento.boleto.repository.dao.ITerminalDAO;
import br.com.stelo.batch.pagamento.boleto.repository.entity.PedidoEntity;
import br.com.stelo.batch.pagamento.boleto.repository.entity.TerminalEntity;

@Service
public class TerminalService implements ITerminalService {

	
	private final Map<Long, Long> terminaisConsultados = new HashMap<Long, Long>();
	private static final String ORIGEM_SUBADQUIRENCIA = "S";
	private static final String ORIGEM_M1 = "M1";
	private static final long ID_TECNOLOGIA_SUBADQUIRENCIA = 11l;
	private static final Object ORIGEM_MPOS = "M";
	private static final long ID_TECNOLOGIA_MPOS = 8l;
	private static final String ORIGEM_WALLET = "W";
	private static final Long ID_TECNOLOGIA_WALLET = 3l;

	@Autowired
	private ITerminalDAO terminalDAO;
	
	@Override
	public Long buscarTerminal(PedidoEntity pedidoEntity) throws Exception {

		if (pedidoEntity != null) {
			final Long codigoVendedor = pedidoEntity.getCD_VDDOR_ROOT_PDIDO();

			Long idTecnologia = null;

			if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(ORIGEM_SUBADQUIRENCIA)
					|| pedidoEntity.getCD_ORIGEM_PRODUTO().equals(ORIGEM_M1)) {
				idTecnologia = ID_TECNOLOGIA_SUBADQUIRENCIA;
			} else if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(ORIGEM_MPOS)) {
				idTecnologia = ID_TECNOLOGIA_MPOS;
			} else if (pedidoEntity.getCD_ORIGEM_PRODUTO().equals(ORIGEM_WALLET)) {
				idTecnologia = ID_TECNOLOGIA_WALLET;
			} else {
				throw new Exception("Erro ao buscar terminal: Nao foi possivel determinar a origem do Pedido!");
			}

			Long nu_term = terminaisConsultados.get(codigoVendedor);
			if (nu_term != null) {
				return nu_term;
			}
			TerminalEntity terminalEntity = terminalDAO.getTerminal(codigoVendedor, idTecnologia);
			if (terminalEntity != null) {
				nu_term = terminalEntity.getNU_TERM();
				terminaisConsultados.put(codigoVendedor, nu_term);
			}
			return nu_term;
		}

		throw new Exception("Erro ao buscar terminal: Nao foi possivel determinar a origem do Pedido!");
	}
}
