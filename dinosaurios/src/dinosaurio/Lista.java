package dinosaurio;

import java.io.Serializable;
import java.util.ArrayList;

public class Lista implements Serializable {
	protected ArrayList<Listable> lista;

	public Lista(ArrayList<Listable> lista) {
		super();
		this.lista = lista;
	}
	
	public Lista() {
		super();
		this.lista = new ArrayList<Listable>();
	}

	public ArrayList<Listable> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Listable> lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return lista + "";
	}
	
	
}