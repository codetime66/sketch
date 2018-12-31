package br.com.stelo.batch.pagamento.boleto.model;


import java.util.ArrayList;
import java.util.List;

public class MaquinasCartoes {

    protected List<MaquinaCartao> maquinaCartao;

    public List<MaquinaCartao> getMaquinaCartao() {
        if (maquinaCartao == null) {
            maquinaCartao = new ArrayList<MaquinaCartao>();
        }
        return this.maquinaCartao;
    }

}
