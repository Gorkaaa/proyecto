package domain;

public class Alimento extends Producto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected TipoAlimento tipoAlimento;

	
	public Alimento(int id, String nombre, String descripcion, String imagen, Double precio, int cantidad,
			TipoAlimento tipoAlimento) {
		super(id, nombre, imagen, precio, cantidad, TipoProducto.ALIMENTO);
		this.tipoAlimento = tipoAlimento;
	}

	public Alimento() {
		super();
		this.tipoAlimento = TipoAlimento.CARNICOS;
		this.tipoProducto = TipoProducto.ALIMENTO;
	}

	public TipoAlimento getTipoAlimento() {
		return tipoAlimento;
	}

	public void setTipoAlimento(TipoAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}

	@Override
	public String toString() {
		return "Alimento [tipoAlimento=" + tipoAlimento + ", nombre=" + nombre + ", imagen="
				+ imagen + ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}
	

}
