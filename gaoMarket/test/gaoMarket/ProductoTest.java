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
	public void testSetNombre() {
		p.setNombre("Lechuga");
		assertEquals("Lechuga", p.getNombre());
	}
	
	@Test
	public void testGetImagen() {
		assertEquals("pan.jpg", p.getImagen());
	}
	
	@Test
	public void testSetImagen() {
		p.setImagen("lechuga.jpg");
		assertEquals("lechuga.jpg", p.getImagen());
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(15.7, p.getPrecio(), 0.1);
	}
	
	@Test
	public void testSetPrecio() {
		p.setPrecio(10.5);
		assertEquals(10.5, p.getPrecio(), 0.1);
	}
	
	@Test
	public void testGetCantidad() {
		assertEquals(2, p.getCantidad());
	}
	
	@Test
	public void testSetCantidad() {
		p.setCantidad(1);
		assertEquals(1, p.getCantidad());
	}
	
	@Test
    public void testToString() {
    	assertEquals("Producto [nombre=Pan, imagen=pan.jpg, precio=15.7, cantidad=2]", p.toString());
	}
}
