import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ejercicio04 {
	
	static class ComparadorLongitudInverso implements Comparator<String> {

		@Override
		public int compare(String a, String b) {
			
			return a.length()-b.length();
		}
		
	}
	
	public static List<String> ordenarPalabrasLongitudInv(List<String> palabras) {
//		Collections.sort(palabras, new MiComparadorInverso());
//		Collections.sort(palabras, new MiComparador().reversed());
		Collections.sort(palabras, new ComparadorLongitudInverso().reversed());
		
		return palabras;
		
	}

}
