package domain;

import java.util.ArrayList;

public class Producto {

	private String nombre;
	private String descripcion;
	private String imagen;
	private Double precio;
	private int cantidad;
	private ArrayList<String> categorias;
	
	//Constructores
	public Producto(String nombre, String descripcion, String imagen, Double precio, int cantidad,
			ArrayList<String> categorias) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.precio = precio;
		this.cantidad = cantidad;
		this.categorias = categorias;
	}

	//Get/Set
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public ArrayList<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<String> categorias) {
		this.categorias = categorias;
	}

	//toString
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", descripcion=" + descripcion + ", precio="
				+ precio + ", cantidad=" + cantidad + ", categorias=" + categorias + "]";
	}
	
}
