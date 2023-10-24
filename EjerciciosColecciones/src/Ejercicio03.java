import java.util.Collections;
import java.util.List;

public class Ejercicio03 {
	
	public static List<String> ordenarPalabrasLongitud(List<String> palabras) {
		Collections.sort(palabras, new MiComparador());
		return palabras;
	}

}
