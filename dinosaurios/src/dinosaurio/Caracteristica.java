package dinosaurio;

import java.util.ArrayList;

public class Caracteristica extends Dinosaurio implements Listable {
	protected double altura;
	protected double peso;
	protected Conducta conducta;	

	public Caracteristica(String nombre, int id, double altura, double peso, Conducta conducta) {
		super(nombre, id);
		this.altura = altura;
		this.peso = peso;
		this.conducta = conducta;
	}

	public Caracteristica() {
		super();
		this.altura = 0.0;
		this.peso = 0.0;
		this.conducta = Conducta.neutral;
	}
	

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Conducta getConducta() {
		return conducta;
	}

	public void setConducta(Conducta conducta) {
		this.conducta = conducta;
	}
	
	

	@Override
	public String toString() {
		return "ID: " + id + ". El " + nombre + " medía " + altura + "m y pesaba " + peso + "Kg. Conducta " + conducta;
	}
	
	
	
	@Override
	public String generarDino() {
		String nombre = "";
		String consonantes = "BCDFGHJKLMNPRSTVWXYZ";
		String vocales = "AEIOU";
		
		// Generar un nombre para luego añadirle un de 4 sufijos disponibles.
		int opcion = (int) (Math.random() * 16);
		
		// Empezando con consonante y acabando en vocal, 3 consonantes y 3 vocales.
		if (opcion >= 0 && opcion <= 2) {
			for (int i = 0; i < 3; i++) {
				int consonante = (int) (Math.random() * consonantes.length());
				nombre = nombre + consonantes.charAt(consonante);
				for (int j = 0; j < 1; j++) {
					int vocal = (int) (Math.random() * vocales.length());
					nombre = nombre + vocales.charAt(vocal);
				}	
			}
		// Empezando con vocal y acabando en vocal, 4 vocales y 3 consonantes.
		} else if (opcion >= 3 && opcion <= 5) {
			for (int i = 0; i < 3; i++) {
				int vocal = (int) (Math.random() * vocales.length());
				nombre = nombre + vocales.charAt(vocal);
				for (int j = 0; j < 1; j++) {
					int consonante = (int) (Math.random() * consonantes.length());
					nombre = nombre + consonantes.charAt(consonante);
				}	
			}
			int vocal = (int) (Math.random() * vocales.length());
			nombre = nombre + vocales.charAt(vocal);
		
		// 2 vocales
		} else if (opcion >= 6 && opcion <= 8) {
			String vocales1 = "AEI";
			String vocales2 = "OU";
			int vocal = (int) (Math.random() * vocales1.length());
			nombre = nombre + vocales1.charAt(vocal);

			int vocal2 = (int) (Math.random() * vocales2.length());
			nombre = nombre + vocales2.charAt(vocal2);
			
		// 2 vocales tambien	
		} else if (opcion == 9 || opcion == 10) {
			String vocales1 = "AEI";
			String vocales2 = "OU";
			int vocal = (int) (Math.random() * vocales1.length());
			nombre = nombre + vocales1.charAt(vocal);

			int vocal2 = (int) (Math.random() * vocales2.length());
			nombre = nombre + vocales2.charAt(vocal2);
		
		// Empezando con consonante y acabando en vocal, 1 consonante y 1 vocal.	
		} else if (opcion == 11) {
			String vocales1 = "OU";
			String vocales2 = "AEI";
			int vocal = (int) (Math.random() * vocales1.length());
			nombre = nombre + vocales1.charAt(vocal);

			int vocal2 = (int) (Math.random() * vocales2.length());
			nombre = nombre + vocales2.charAt(vocal2);
	
		
		// Empezando con vocal y acabando en vocal, 5 vocales y 4 consonantes.	
		} else if (opcion == 12) {
			for (int i = 0; i < 4; i++) {
				int vocal = (int) (Math.random() * vocales.length());
				nombre = nombre + vocales.charAt(vocal);
				for (int j = 0; j < 1; j++) {
					int consonante = (int) (Math.random() * consonantes.length());
					nombre = nombre + consonantes.charAt(consonante);
					System.out.println("OPCION GUAPA");
				}
			}
			int vocal = (int) (Math.random() * vocales.length());
			nombre = nombre + vocales.charAt(vocal);
				
			
		// Empezando con consonante y acabando en vocal, 2 consonantes y 2 vocales.
		} else {
			for (int i = 0; i < 2; i++) {
				int consonante = (int) (Math.random() * consonantes.length());
				nombre = nombre + consonantes.charAt(consonante);
				for (int j = 0; j < 1; j++) {
					int vocal = (int) (Math.random() * vocales.length());
					nombre = nombre + vocales.charAt(vocal);
				}	
			}
		}
		
		// Añadir al nombre generado anteriormente un sufijo para crear un nombre de dinosaurio.
		int aleatorio = (int) (Math.random() * 4);
		if (aleatorio == 0) {
			nombre = nombre + "SAURUS";
		} else if (aleatorio == 1) {
			nombre = nombre + "DOCUS";
		} else if (aleatorio == 2) {
			nombre = nombre + "RAPTOR";
		} else {
			nombre = nombre + "DON";
		}

		return nombre.substring(0,1).toUpperCase() + nombre.substring(1).toLowerCase();
	}

	@Override
	public int generarId() {
		int id = (int) (Math.random() * 10000000);
		return id;
	}

}
