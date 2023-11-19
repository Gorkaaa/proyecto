package domain;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable, Comparable<Producto>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int id;
	protected String nombre;
	protected String imagen;
	protected Double precio;
	protected int cantidad;
	
	
	public Producto(int id, String nombre, String imagen, Double precio, int cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.imagen = imagen;
		this.precio = precio;
		this.cantidad = cantidad;
	}
	
	public Producto() {
		super();
		this.id = id;
		this.nombre = "";
		this.imagen = "";
		this.precio = 0.0;
		this.cantidad = 0;
	}


	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", imagen=" + imagen
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

//	Ordena los productos por su nombre alfabeticamente
	@Override
	public int compareTo(Producto o) {
		return this.nombre.compareTo(o.nombre);
	}
	
}
