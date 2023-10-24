import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Ejercicio13 {
	
	static class ComparadorFechaEntrega implements Comparator<Producto> {

		@Override
		public int compare(Producto a, Producto b) {
			
			return a.getEntrega().compareTo(b.getEntrega());
		}
	}
		
	
	
	public static List<Producto> eliminarDuplicados(List<Producto> productos) {
		
//		Set<Producto> setProductos = new HashSet<>(productos);
//		Collections.sort(new ArrayList<>(setProductos), new ComparadorFechaEntrega().reversed());
//		return new ArrayList<>(setProductos);
		
		Set<Producto> setProductos = new TreeSet<>(new ComparadorFechaEntrega().reversed());
		setProductos.addAll(productos);
		return new ArrayList<>(setProductos);
		
	}
}
