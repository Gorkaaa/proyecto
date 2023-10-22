package main;

public class HigieneYBelleza extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected TipoHigieneYBelleza tipoHigieneYBelleza;

	public HigieneYBelleza(String nombre, int cantidad, TipoHigieneYBelleza tipoHigieneYBelleza) {
		super(nombre, cantidad);
		this.tipoHigieneYBelleza = tipoHigieneYBelleza;
	}
	
	public HigieneYBelleza() {
		super();
		this.tipoHigieneYBelleza = TipoHigieneYBelleza.AFEITADO_DEPILACION;
	}

	public TipoHigieneYBelleza getTipoHigieneYBelleza() {
		return tipoHigieneYBelleza;
	}

	public void setTipoHigieneYBelleza(TipoHigieneYBelleza tipoHigieneYBelleza) {
		this.tipoHigieneYBelleza = tipoHigieneYBelleza;
	}
	

	@Override
	public String toString() {
		return "HigieneYBelleza [tipoHigieneYBelleza=" + tipoHigieneYBelleza + ", id=" + id + ", nombre=" + nombre
				+ ", cantidad=" + cantidad + "]";
	}
	
	
}
