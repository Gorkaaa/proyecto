import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ejercicio05 {
	
	static class ComparadorProductosId implements Comparator<Producto> {

		@Override
		public int compare(Producto p1, Producto p2) {
			return Integer.compare(p1.getId(), p2.getId());
		}
		
	}
	
	
	public static List<Producto> ordenarProductosId(List<Producto> productos) {
//		Collections.sort(productos, new MiComparadorNumeros());
//		Collections.sort(productos, new ComparadorProductosId());
		
		// Mejor opción.
		Collections.sort(productos); // Ya esta implementada la interfaz comparable en la clase producto.
		return productos;
		
	}

}
