package dinosaurio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

abstract public class Dinosaurio implements Serializable, Listable { // Lo de serializable es para que se pueda guardar en disco
	protected String nombre;
	protected int id;	

	public Dinosaurio(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}
	
	public Dinosaurio() {
		super();
		this.nombre = generarDino();
		this.id = generarId();
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	

	@Override
	public String toString() {
		return "Su nombre es " + nombre + " y su número ID es: " + id;
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
		Dinosaurio other = (Dinosaurio) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}

	abstract public String generarDino();
	
	abstract public int generarId();

}
