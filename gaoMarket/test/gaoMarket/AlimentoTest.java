package gaoMarket;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Alimento;
import domain.TipoAlimento;

public class AlimentoTest {
	private Alimento a;
	
	@Before
	public void setup() {
		a = new Alimento(1, "Pan", "descripcion", "pan.jpg", 15.7, 2, TipoAlimento.VEGETALES);
	}
	
	@Test
	public void testAlimento() {
		Alimento a1 = new Alimento();
		assertEquals(TipoAlimento.CARNICOS, a1.getTipoAlimento());
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Pan", a.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		a.setNombre("Lechuga");
		assertEquals("Lechuga", a.getNombre());
	}
	
	@Test
	public void testGetTipoAlimento() {
		assertEquals(TipoAlimento.VEGETALES, a.getTipoAlimento());;
	}

	@Test
	public void testSetTipoAlimento() {
		a.setTipoAlimento(TipoAlimento.CARNICOS);;
		assertEquals(TipoAlimento.CARNICOS, a.getTipoAlimento());
	}
	
	@Test
    public void testToString() {
    	assertEquals("Alimento [tipoAlimento=VEGETALES, nombre=Pan, imagen=pan.jpg, precio=15.7, cantidad=2]", a.toString());
	}
}
