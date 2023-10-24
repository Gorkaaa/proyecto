import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ejercicio10 {
	
	public static List<String> eliminarDuplicados(List<String> palabras) {
		
		Set<String> setPalabras = new HashSet<>(palabras); // El set no garantiza orden lo cual puede estar desordenada la lista de strings.
		
		
		return new ArrayList<>(setPalabras);
		
	}

}
