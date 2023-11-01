package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GestorMarket {
	protected List<Persona> personas;
	protected List<Producto> productos;
	protected Map<String, List<Persona>> mapaPersonas;
	protected Map<TipoAlimento, Set<Producto>> mapaAlimentos;
	protected Map<TipoHigieneYBelleza, Set<Producto>> mapaHigieneYBelleza;
	protected Map<TipoLimpieza, Set<Producto>> mapaLimpieza;

	public GestorMarket(List<Persona> personas, List<Producto> productos, Map<String, List<Persona>> mapaPersonas,
			Map<TipoAlimento, Set<Producto>> mapaAlimentos,
			Map<TipoHigieneYBelleza, Set<Producto>> mapaHigieneYBelleza,
			Map<TipoLimpieza, Set<Producto>> mapaLimpieza) {
		super();
		this.personas = personas;
		this.productos = productos;
		this.mapaPersonas = mapaPersonas;
		this.mapaAlimentos = mapaAlimentos;
		this.mapaHigieneYBelleza = mapaHigieneYBelleza;
		this.mapaLimpieza = mapaLimpieza;
	}


	public GestorMarket() {
		super();
		this.personas = new ArrayList<>();
		this.productos = new ArrayList<>();
		this.mapaPersonas = new HashMap<>();
		this.mapaAlimentos = new HashMap<>();
		for (TipoAlimento alimento : TipoAlimento.values()) {
			mapaAlimentos.put(alimento, new TreeSet<Producto>());
		}
		
		this.mapaHigieneYBelleza = new HashMap<>();
		for (TipoHigieneYBelleza higiene : TipoHigieneYBelleza.values()) {
			mapaHigieneYBelleza.put(higiene, new TreeSet<Producto>());
		}
		
		this.mapaLimpieza = new HashMap<>();
		for (TipoLimpieza limpieza : TipoLimpieza.values()) {
			mapaLimpieza.put(limpieza, new TreeSet<Producto>());
		}
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

	public Map<TipoAlimento, Set<Producto>> getMapaAlimentos() {
		return mapaAlimentos;
	}

	public void setMapaAlimentos(Map<TipoAlimento, Set<Producto>> mapaAlimentos) {
		this.mapaAlimentos = mapaAlimentos;
	}


	public Map<TipoHigieneYBelleza, Set<Producto>> getMapaHigieneYBelleza() {
		return mapaHigieneYBelleza;
	}


	public void setMapaHigieneYBelleza(Map<TipoHigieneYBelleza, Set<Producto>> mapaHigieneYBelleza) {
		this.mapaHigieneYBelleza = mapaHigieneYBelleza;
	}


	public Map<TipoLimpieza, Set<Producto>> getMapaLimpieza() {
		return mapaLimpieza;
	}


	public void setMapaLimpieza(Map<TipoLimpieza, Set<Producto>> mapaLimpieza) {
		this.mapaLimpieza = mapaLimpieza;
	}
	

}
