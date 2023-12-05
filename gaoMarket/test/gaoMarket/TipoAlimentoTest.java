package gaoMarket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import domain.TipoAlimento;

public class TipoAlimentoTest {
	
	@Test
	public void testEnumValues() {
		assertEquals("CARNICOS", TipoAlimento.CARNICOS.name());
		assertEquals("VEGETALES", TipoAlimento.VEGETALES.name());
		assertEquals("BEBIDAS", TipoAlimento.BEBIDAS.name());
		assertEquals("CONGELADOS", TipoAlimento.CONGELADOS.name());
		assertEquals("DULCES", TipoAlimento.DULCES.name());
	}
	
	@Test
	public void testEnumComparisons() {
		assertEquals(TipoAlimento.CARNICOS, TipoAlimento.CARNICOS);
		assertEquals(TipoAlimento.VEGETALES, TipoAlimento.VEGETALES);
		assertEquals(TipoAlimento.BEBIDAS, TipoAlimento.BEBIDAS);
		assertEquals(TipoAlimento.CONGELADOS, TipoAlimento.CONGELADOS);
		assertEquals(TipoAlimento.DULCES, TipoAlimento.DULCES);
		assertNotEquals(TipoAlimento.CARNICOS, TipoAlimento.VEGETALES);
		assertNotEquals(TipoAlimento.BEBIDAS, TipoAlimento.CONGELADOS);
		assertNotEquals(TipoAlimento.DULCES, TipoAlimento.CARNICOS);
    }

    @Test
    public void testEnumConversion() {
        assertEquals(TipoAlimento.CARNICOS, TipoAlimento.valueOf("CARNICOS"));
        assertEquals(TipoAlimento.VEGETALES, TipoAlimento.valueOf("VEGETALES"));
        assertEquals(TipoAlimento.BEBIDAS, TipoAlimento.valueOf("BEBIDAS"));
        assertEquals(TipoAlimento.CONGELADOS, TipoAlimento.valueOf("CONGELADOS"));
        assertEquals(TipoAlimento.DULCES, TipoAlimento.valueOf("DULCES"));
    }
}
