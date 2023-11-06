package gaoMarket;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Producto;

public class ProductoTest {
	private Producto p;
	
	@Before
	public void setup() {
		p = new Producto("Pan", "pan.jpg", 15.7, 2);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Pan", p.getNombre());
	}
	
	@Test
	public void testGetImagen() {
		assertEquals("pan.jpg", p.getImagen());
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(15.7, p.getPrecio(), 0.1);
	}
	
	@Test
	public void testGetCantidad() {
		assertEquals(2, p.getCantidad());
	}
}
