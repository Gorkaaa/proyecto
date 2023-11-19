package domain;

import bd.GestorBD;

public class Usuario extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final GestorBD GESTOR_BD = new GestorBD();
	
	public Usuario(String nombre, String apellidos, String nomUsuario, int numTelefono, String correoElectronico,
			String contrasenya) {
		super(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasenya);
	}
	
	public Usuario() {
		super();
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", nomUsuario=" + nomUsuario
				+ ", numTelefono=" + numTelefono + ", correoElectronico=" + correoElectronico + ", contrasenya="
				+ contrasenya + "]";
	}

	@Override
	public boolean verificarUsuario(String correo, String contraseña) {
		if(GESTOR_BD.verificarCredenciales(correo, contraseña) == null)
			return false;
		return true;
	}

	@Override
	public boolean esEmpleado() {
		return false;
	}
	

}
