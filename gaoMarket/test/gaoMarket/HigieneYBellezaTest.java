package gaoMarket;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.HigieneYBelleza;
import domain.TipoHigieneYBelleza;

public class HigieneYBellezaTest {
	private HigieneYBelleza hb;
	
	@Before
	public void setup() {
		hb = new HigieneYBelleza(1, "Desodorante", "descripcion", "pan.jpg", 1.8, 2, TipoHigieneYBelleza.CUIDADO_CORPORAL);
	}
	
	@Test
	public void testLimpieza() {
		HigieneYBelleza hb1 = new HigieneYBelleza();
		assertEquals(TipoHigieneYBelleza.AFEITADO_DEPILACION, hb1.getTipoHigieneYBelleza());
	}
	
	@Test
	public void testGetTipoHigieneYBelleza() {
		assertEquals(TipoHigieneYBelleza.CUIDADO_CORPORAL, hb.getTipoHigieneYBelleza());
	}
	@Test
	public void testSetTipoHigieneYBelleza() {
		hb.setTipoHigieneYBelleza(TipoHigieneYBelleza.AFEITADO_DEPILACION);
		assertEquals(TipoHigieneYBelleza.AFEITADO_DEPILACION, hb.getTipoHigieneYBelleza());
	}
	
	@Test
    public void testToString() {
		assertEquals("HigieneYBelleza [tipoHigieneYBelleza=CUIDADO_CORPORAL, nombre=Desodorante, "
				+ "imagen=pan.jpg, precio=1.8, cantidad=2]", hb.toString());
	}
}
