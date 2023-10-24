import java.util.Comparator;

public class MiComparadorNumeros implements Comparator<Producto>{

	@Override
	 public int compare(Producto p1, Producto p2) {
        return Integer.compare(p1.getId(), p2.getId());
    }
	

}
