import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa un producto
 */
public class Producto implements Comparable<Producto>{
    
    private int id; // id del producto
    private String nombre; // nombre del producto
    private int unidades; // número de unidades compradas
    private LocalDate entrega; // fecha de entrega del producto

    public Producto(int id, String nombre, int unidades, LocalDate entrega) {
        this.id = id;
        this.nombre = nombre;
        this.unidades = unidades;
        this.entrega = entrega;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public LocalDate getEntrega() {
        return entrega;
    }

	@Override
	public int compareTo(Producto o) {
		return this.id - o.id;
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Producto other = (Producto) obj;
//		return id == other.id;
//	}

	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Producto))
			return false;
		Producto p = (Producto) obj;
		 return this.id == p.id;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(this.id);
	}

	
}
