package dinosaurio;

import java.awt.Color;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class GestorDinos {
	protected ArrayList<Dinosaurio> dinosaurios;
	protected ArrayList<Caracteristica> caracteristicas;
	protected ArrayList<Informacion> informaciones;
	protected Lista lista;
	protected HashMap <Periodo, ArrayList<Informacion>> dinosPorPeriodo;
	
	
	public GestorDinos(ArrayList<Dinosaurio> dinosaurios, ArrayList<Caracteristica> caracteristicas,
			ArrayList<Informacion> informaciones, Lista lista,
			HashMap<Periodo, ArrayList<Informacion>> dinosPorPeriodo) {
		super();
		this.dinosaurios = dinosaurios;
		this.caracteristicas = caracteristicas;
		this.informaciones = informaciones;
		this.lista = lista;
		this.dinosPorPeriodo = dinosPorPeriodo;
		
	}
	
	public GestorDinos() {
		super();
		this.dinosaurios = new ArrayList<Dinosaurio>();
		this.caracteristicas = new ArrayList<Caracteristica>();
		this.informaciones = new ArrayList<Informacion>();
		this.lista =  new Lista();
		this.dinosPorPeriodo = new HashMap<Periodo, ArrayList<Informacion>>();
		dinosPorPeriodo.put(Periodo.cretacico, new ArrayList<Informacion>());
		dinosPorPeriodo.put(Periodo.jurasico, new ArrayList<Informacion>());
		dinosPorPeriodo.put(Periodo.triasico, new ArrayList<Informacion>());
	}

	public ArrayList<Dinosaurio> getDinosaurios() {
		return dinosaurios;
	}

	public void setDinosaurios(ArrayList<Dinosaurio> dinosaurios) {
		this.dinosaurios = dinosaurios;
	}

	public ArrayList<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(ArrayList<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public ArrayList<Informacion> getInformaciones() {
		return informaciones;
	}

	public void setInformaciones(ArrayList<Informacion> informaciones) {
		this.informaciones = informaciones;
	}

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public HashMap<Periodo, ArrayList<Informacion>> getDinosPorPeriodo() {
		return dinosPorPeriodo;
	}

	public void setDinosPorPeriodo(HashMap<Periodo, ArrayList<Informacion>> dinosPorPeriodo) {
		this.dinosPorPeriodo = dinosPorPeriodo;
	}

	@Override
	public String toString() {
		return "GestorDinos [dinosaurios=" + dinosaurios + ", caracteristicas=" + caracteristicas + ", informaciones="
				+ informaciones + ", lista=" + lista + ", dinosPorPeriodo=" + dinosPorPeriodo + "]";
	}
	
	
	public void guardarDatos(String fichero) {
		try {			
			FileOutputStream fos = new FileOutputStream(fichero);
			ObjectOutputStream oos = new ObjectOutputStream(fos); 
			
			oos.writeObject(dinosaurios);
			oos.writeObject(caracteristicas);
			oos.writeObject(informaciones);
			oos.writeObject(dinosPorPeriodo);

			oos.close();
			fos.close();
			
		} catch (IOException e) {
			System.err.println("Error al guardar los datos");
		} 
	}

	public void cargarDatos(String fichero) {
		try {
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream (fis); 
			
			this.dinosaurios = (ArrayList<Dinosaurio>) ois.readObject();
			this.caracteristicas = (ArrayList<Caracteristica>) ois.readObject();
			this.informaciones = (ArrayList<Informacion>) ois.readObject();
			this.dinosPorPeriodo = (HashMap<Periodo, ArrayList<Informacion>>) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException e) {
			System.err.println("Error de fichero al cargar los datos");
		} catch (ClassNotFoundException e) {
			System.err.println("Error de formato de datos");
		} 
	}
	
	
	public void guardarCaracteristicasCSV(String ficheroCaracteristicas) {
		try {
			PrintWriter pw = new PrintWriter(ficheroCaracteristicas);
		
			pw.println(ficheroCaracteristicas);
		
			for (Caracteristica caracteristica : getCaracteristicas()) {
				pw.println(caracteristica.getNombre() +";"+ caracteristica.getId() +";"+ caracteristica.getAltura() + " m" +";"+  
						caracteristica.getPeso()+" Kg" +";"+ caracteristica.getConducta()+";");
			}
		
			pw.close();
		
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar datos en CSV.");
		}
	}
	
	
	public void guardarInformacionesCSV(String ficheroInformaciones) {
		try {
			PrintWriter pw = new PrintWriter(ficheroInformaciones);
		
			pw.println(ficheroInformaciones);
		
			for (Informacion informacion : getInformaciones()) {
				pw.println(informacion.getNombre() +";"+ informacion.getId() +";"+ informacion.getComida() +";"+ 
							informacion.getVida() +";"+ informacion.getExtinto() + " M de años"+";"+ informacion.getPeriodo()+";");
			}
		
			pw.close();
		
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar datos en CSV.");
		}
	
	}
}