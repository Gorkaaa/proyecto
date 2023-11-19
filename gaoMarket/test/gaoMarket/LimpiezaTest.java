package gaoMarket;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Limpieza;
import domain.TipoLimpieza;

public class LimpiezaTest {
	private Limpieza l;
	
	@Before
	public void setup() {
		l = new Limpieza(1, "Escoba", "descripcion", "pan.jpg", 1.8, 2, TipoLimpieza.UTENSILIOS);
	}
	
	@Test
	public void testLimpieza() {
		Limpieza l1 = new Limpieza();
		assertEquals(TipoLimpieza.UTENSILIOS, l1.getTipoLimpieza());
	}
	
	@Test
	public void testGetTipoLimpieza() {
		assertEquals(TipoLimpieza.UTENSILIOS, l.getTipoLimpieza());
	}
	
	@Test
	public void testSetTipoLimpieza() {
		l.setTipoLimpieza(TipoLimpieza.PRODUCTOS_LIMPIEZA);
		assertEquals(TipoLimpieza.PRODUCTOS_LIMPIEZA, l.getTipoLimpieza());
	}
	
	@Test
    public void testToString() {
    	assertEquals("Limpieza [tipoLimpieza=UTENSILIOS, id=1, nombre=Escoba, imagen=pan.jpg, precio=1.8, cantidad=2]", l.toString());
	}
}
