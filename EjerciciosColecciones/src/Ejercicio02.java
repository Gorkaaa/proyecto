import java.util.Collections;
import java.util.List;

public class Ejercicio02 {
	public static List<String> ordenarPalabrasInverso(List<String> lista) {
		Collections.sort(lista);
		Collections.reverse(lista);
		return lista;
		
	}

}
