import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ejercicio06 {
	
	static class ComparadorEntrega implements Comparator<Producto> {

		@Override
		public int compare(Producto a, Producto b) {
			
			return a.getEntrega().compareTo(b.getEntrega());
		}
		
	}
	
	public static List<Producto> ordenarProductosEntrega(List<Producto> productos) {
		
		Collections.sort(productos, new ComparadorEntrega().reversed());
		return productos;
		
	}

}
