import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Ejercicio11 {

	public static List<String> eliminarDuplicados(List<String> palabras) {
		
		Set<String> setPalabras = new LinkedHashSet<>(palabras);// Se mantiene el orden de insercion de los strings a la lista.
		
		return new ArrayList<>(setPalabras);
	}
}
