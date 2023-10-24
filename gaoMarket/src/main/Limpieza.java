package main;

public class Limpieza extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected TipoLimpieza tipoLimpieza;

	public Limpieza(String nombre, int cantidad, TipoLimpieza tipoLimpieza) {
		super(nombre, cantidad);
		this.tipoLimpieza = tipoLimpieza;
	}
	
	public Limpieza() {
		super();
		this.tipoLimpieza = TipoLimpieza.UTENSILIOS;
	}

	public TipoLimpieza getTipoLimpieza() {
		return tipoLimpieza;
	}

	public void setTipoLimpieza(TipoLimpieza tipoLimpieza) {
		this.tipoLimpieza = tipoLimpieza;
	}
	

	@Override
	public String toString() {
		return "Limpieza [tipoLimpieza=" + tipoLimpieza + ", id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad
				+ "]";
	}
	

}
