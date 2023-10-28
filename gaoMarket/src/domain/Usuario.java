package domain;

public class Usuario extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", nomUsuario=" + nomUsuario
				+ ", numTelefono=" + numTelefono + ", correoElectronico=" + correoElectronico + ", contrasenya="
				+ contrasenya + "]";
	}

	@Override
	public boolean verificarUsuario(String nomUsuario, String contraseña) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaRegistrado() {
		// TODO Auto-generated method stub
		return false;
	}
	

}
