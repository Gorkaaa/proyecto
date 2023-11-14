package gaoMarket;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Usuario;

public class UsuarioTest {
	private Usuario u;
	
	@Before
	public void setup() {
		u = new Usuario("Juan", "Fernandez", "juanfer", 660044567, "juan@gmail.com", "juan123");
		Usuario u2 = new Usuario();
	}
	
	@Test
    public void testToString() {
    	assertEquals("Usuario [nombre=Juan, apellidos=Fernandez, nomUsuario=juanfer, numTelefono=660044567, correoElectronico=juan@gmail.com, contrasenya=juan123]", u.toString());
	}
}
