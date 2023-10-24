import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Ejercicio12 {
	
	public static List<Producto> eliminarDuplicados(List<Producto> productos) {
		
		Set<Producto> setProductos = new LinkedHashSet<>(productos);
		return new ArrayList<>(setProductos);
		
	}

}
