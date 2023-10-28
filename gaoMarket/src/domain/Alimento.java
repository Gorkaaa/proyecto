package domain;

public class Alimento extends Producto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected TipoAlimento tipoAlimento;

	
	public Alimento(String nombre, String descripcion, String imagen, Double precio, int cantidad,
			TipoAlimento tipoAlimento) {
		super(nombre, imagen, precio, cantidad);
		this.tipoAlimento = tipoAlimento;
	}

	public Alimento() {
		super();
		this.tipoAlimento = TipoAlimento.CARNICOS;
	}

	public TipoAlimento getTipoAliment0() {
		return tipoAlimento;
	}

	public void setTipoAlimento(TipoAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}

	@Override
	public String toString() {
		return "Alimento [tipoAlimento=" + tipoAlimento + ", id=" + id + ", nombre=" + nombre + ", imagen="
				+ imagen + ", precio=" + precio + ", cantidad=" + cantidad + "]";
	}
	

}
