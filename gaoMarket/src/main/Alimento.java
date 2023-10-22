package main;

public class Alimento extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected TipoAlimentacion tipoAlimentacion;

	public Alimento(String nombre, int cantidad, TipoAlimentacion tipoAlimentacion) {
		super(nombre, cantidad);
		this.tipoAlimentacion = tipoAlimentacion;
	}
	
	public Alimento() {
		super();
		this.tipoAlimentacion = TipoAlimentacion.CARNICOS;
	}

	public TipoAlimentacion getTipoAlimentacion() {
		return tipoAlimentacion;
	}

	public void setTipoAlimentacion(TipoAlimentacion tipoAlimentacion) {
		this.tipoAlimentacion = tipoAlimentacion;
	}

	
	@Override
	public String toString() {
		return "Alimento [tipoAlimentacion=" + tipoAlimentacion + ", id=" + id + ", nombre=" + nombre + ", cantidad="
				+ cantidad + "]";
	}
	
	

}
