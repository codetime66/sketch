import java.util.ArrayList;
import java.util.List;

public class LayoutPosicional {
	
  public static List ler(String linha, int[] campos) {
	List list = new ArrayList();
	int inicio = 0;
	int fim = 0;
	for (int i = 0; i < campos.length; i++) {
		fim = inicio +  campos[i];
		if (fim < linha.length()) {
			list.add(linha.substring(inicio, fim));
		} else {
			list.add(linha.substring(inicio));
		}
		inicio = fim;
	}		
	return list;
  }

}
