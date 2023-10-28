package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorMarket {
	protected List<Persona> personas;
	protected List<Producto> productos;
	protected Map<String, List<Persona>> mapaPersonas;
	protected Map<TipoAlimento,List<Producto>> mapaAlimentos;
	protected Map<TipoHigieneYBelleza, List<Producto>> mapaHigieneYBelleza;

	public GestorMarket(List<Persona> personas, List<Producto> productos, Map<String, List<Persona>> mapaPersonas,
			Map<TipoAlimento, List<Producto>> mapaAlimentos,
			Map<TipoHigieneYBelleza, List<Producto>> mapaHigieneYBelleza) {
		super();
		this.personas = personas;
		this.productos = productos;
		this.mapaPersonas = mapaPersonas;
		this.mapaAlimentos = mapaAlimentos;
		this.mapaHigieneYBelleza = mapaHigieneYBelleza;
	}


	public GestorMarket() {
		super();
		this.personas = new ArrayList<>();
		this.productos = new ArrayList<>();
		this.mapaPersonas = new HashMap<>();
		this.mapaAlimentos = new HashMap<>();
		mapaAlimentos.put(TipoAlimento.CARNICOS, new ArrayList<Producto>());
		mapaAlimentos.put(TipoAlimento.VEGETALES, new ArrayList<Producto>());
		mapaAlimentos.put(TipoAlimento.BEBIDAS, new ArrayList<Producto>());
		mapaAlimentos.put(TipoAlimento.CONGELADOS, new ArrayList<Producto>());
		mapaAlimentos.put(TipoAlimento.DULCES, new ArrayList<Producto>());
		
		this.mapaHigieneYBelleza = new HashMap<>();
		mapaHigieneYBelleza.put(TipoHigieneYBelleza.AFEITADO_DEPILACION, new ArrayList<Producto>());
		mapaHigieneYBelleza.put(TipoHigieneYBelleza.HIGIENE_BUCAL, new ArrayList<Producto>());
		mapaHigieneYBelleza.put(TipoHigieneYBelleza.HIGIENE_INTIMA, new ArrayList<Producto>());
		mapaHigieneYBelleza.put(TipoHigieneYBelleza.CUIDADO_CORPORAL, new ArrayList<Producto>());
		mapaHigieneYBelleza.put(TipoHigieneYBelleza.PARAFARMACIA_SOLARES, new ArrayList<Producto>());
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Map<String, List<Persona>> getMapaPersonas() {
		return mapaPersonas;
	}

	public void setMapaPersonas(Map<String, List<Persona>> mapaPersonas) {
		this.mapaPersonas = mapaPersonas;
	}

	public Map<TipoAlimento, List<Producto>> getMapaAlimentos() {
		return mapaAlimentos;
	}

	public void setMapaAlimentos(Map<TipoAlimento, List<Producto>> mapaAlimentos) {
		this.mapaAlimentos = mapaAlimentos;
	}
	
	

	
	
	
	

}
