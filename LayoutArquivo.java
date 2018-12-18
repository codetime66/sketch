import java.util.List;

public class LayoutArquivo {

    public static final int[] TAMANHO_CAMPOS = {1,8,8,5,14,15,18,18,18,18};	
	
    public String DCTIPO;
    public String DCDATOP;
    public String DCDETENTOR;
    public String DCTIPOIF;
    public String DCIF;
    public String DCQUANTIDADE;
    public String DCVALORNOMINAL;
    public String DCVALORJUROSDIA;
    public String DCPUCURVA;
    public String DCVALOR;


    public LayoutArquivo() {}

    public void carregar(List valores) throws Exception {
    	if(valores==null || valores.size()!=10){
    		throw new Exception("Lista de valores não está correta.");
    	} else {
    		DCTIPO = (String)valores.get(0);
    		DCDATOP = (String)valores.get(1);
    		DCDETENTOR = (String)valores.get(2);
    		DCTIPOIF = (String)valores.get(3);
    		DCIF = (String)valores.get(4);
    		DCQUANTIDADE = (String)valores.get(5);
    		DCVALORNOMINAL = (String)valores.get(6);
    		DCVALORJUROSDIA = (String)valores.get(7);
    		DCPUCURVA = (String)valores.get(8);
    		DCVALOR = (String)valores.get(9);
    	}
    }
}
