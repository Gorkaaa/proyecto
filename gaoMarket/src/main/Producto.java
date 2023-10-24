package main;

import java.io.Serializable;
import java.util.Objects;

public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private static int contadorId = 0;
	
	protected int id;
	protected String nombre;
	protected int cantidad;
	
	
	
	public Producto(String nombre, int cantidad) {
		super();
		this.id = Producto.contadorId;
		Producto.contadorId++;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public Producto() {
		super();
		this.id = Producto.contadorId;
		Producto.contadorId++;
		this.nombre = "";
		this.cantidad = 0;
	}
	

	public static int getContadorId() {
		return contadorId;
	}

	public static void setContadorId(int contadorId) {
		Producto.contadorId = contadorId;
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


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

}
