package domain;

public class Limpieza extends Producto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected TipoLimpieza tipoLimpieza;

	public Limpieza(int id, String nombre, String descripcion, String imagen, Double precio, int cantidad,
			TipoLimpieza tipoLimpieza) {
		super(id, nombre, imagen, precio, cantidad, TipoProducto.LIMPIEZA);
		this.tipoLimpieza = tipoLimpieza;
	}

	public Limpieza() {
		super();
		this.tipoLimpieza = TipoLimpieza.UTENSILIOS;
		this.tipoProducto = TipoProducto.LIMPIEZA;
	}
	

	public TipoLimpieza getTipoLimpieza() {
		return tipoLimpieza;
	}

	public void setTipoLimpieza(TipoLimpieza tipoLimpieza) {
		this.tipoLimpieza = tipoLimpieza;
	}

	@Override
	public String toString() {
		return "Limpieza [tipoLimpieza=" + tipoLimpieza + ", id=" + id + ", nombre=" + nombre + ", imagen=" + imagen
				+ ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}
	

}
