package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;

import dinosaurio.*;

public class VentanaPrincipal extends JFrame {
	
	protected GestorDinos gestor;
	protected VentanaLista ventanaLista;
	protected VentanaImagen ventanaImagen;
	protected VentanaTablaInformaciones ventanaInformaciones;
	protected VentanaTablaCaracteristicas ventanaCaracteristicas;
	protected JButton botonImagenes;
	protected JButton botonLista;
	protected JButton botonSalir;
	protected JButton botonTablaInformaciones;
	protected JButton botonTablaCaracteristicas;
	
	public VentanaPrincipal() {
		gestor = new GestorDinos();	
		
		gestor.cargarDatos("dinosaurios.dat");
		
		ventanaLista = new VentanaLista(gestor);
		ventanaImagen = new VentanaImagen(gestor);
		ventanaInformaciones = new VentanaTablaInformaciones(gestor);
		ventanaCaracteristicas = new VentanaTablaCaracteristicas(gestor);
				
		Container cp = this.getContentPane();
		
		
		botonImagenes = new JButton("Crear Imagen");
		botonLista = new JButton("Lista");
		botonSalir = new JButton("Salir");
		botonTablaInformaciones = new JButton("Tabla Informaciones");
		botonTablaCaracteristicas = new JButton("Tabla Caracteristicas");
		
		botonImagenes.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilo = new Thread(new Runnable() {		
					@Override
					public void run() {
						System.out.println("Estamos preparando todas las fotos para ti...");
						for (int i = 0; i < 65000000; i++) {
							Math.pow(i, 3);
						}
						ventanaImagen.setVisible(true);
						
						System.out.println("Ya está todo listo, Bienvenido.");
					}
				});
				hilo.start();				
			}
		});		
		

		
		botonLista.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Thread hilo = new Thread(new Runnable() {		
					@Override
					public void run() {
						System.out.println("Estamos preparando todo para ti...");
						for (int i = 0; i < 57000000; i++) {
							Math.pow(i, 3);
						}
						ventanaLista.setVisible(true);
						
						System.out.println("Todo listo");
					}
				});
				hilo.start();				
			}
		});
		
		
		botonTablaCaracteristicas.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaCaracteristicas.guardarCaracteristicasTXT("caracteristicas.txt");
				ventanaCaracteristicas.actualizarTabla();
				ventanaCaracteristicas.setVisible(true);
			}
		});
		
		
		botonTablaInformaciones.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaInformaciones.guardarInformacionesTXT("informaciones.txt");
				ventanaInformaciones.actualizarTabla();
				ventanaInformaciones.setVisible(true);
			}
		});
		
			
		
		botonSalir.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
					dispose();
					gestor.guardarDatos("dinosaurios.dat");
					gestor.guardarCaracteristicasCSV("caracteristicas.csv");
					gestor.guardarInformacionesCSV("informaciones.csv");
					
					ventanaCaracteristicas.guardarCaracteristicasTXT("caracteristicas.txt");
					ventanaInformaciones.guardarInformacionesTXT("informaciones.txt");
					
					System.out.println("Guardando datos...");
					
					ventanaImagen.dispose();
					ventanaLista.dispose();
					ventanaInformaciones.dispose();
					ventanaCaracteristicas.dispose();	
			}
		});
		
		
		JPanel arriba = new JPanel();
		JPanel medio = new JPanel();
		JPanel abajo = new JPanel();
		JPanel salir = new JPanel();
		
		
		arriba.setLayout(new GridLayout());
		arriba.add(botonImagenes);
		
		medio.setLayout(new GridLayout());
		medio.add(botonLista);
		
		abajo.setLayout(new GridLayout(1, 2));
		abajo.add(botonTablaCaracteristicas, BorderLayout.EAST);
		abajo.add(botonTablaInformaciones, BorderLayout.WEST);
		
		salir.setLayout(new FlowLayout());
		salir.add(botonSalir);
		
		cp.setLayout(new GridLayout(4, 1));
		cp.add(arriba);
		cp.add(medio);
		cp.add(abajo);
		cp.add(salir);
		
		
		this.addWindowListener(new WindowAdapter() {			
			@Override
			public void windowClosing(WindowEvent e) {
				
				gestor.guardarDatos("dinosaurios.dat");
				gestor.guardarCaracteristicasCSV("caracteristicas.csv");
				gestor.guardarInformacionesCSV("informaciones.csv");
				ventanaCaracteristicas.guardarCaracteristicasTXT("caracteristicas.txt");
				ventanaInformaciones.guardarInformacionesTXT("informaciones.txt");
				
				System.out.println("Guardando datos...");
			}
			
		});
		
		
		
		this.setTitle("DINOSAURIOS");
		this.setSize(330, 280);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Thread hilo = new Thread(new Runnable() {		
			@Override
			public void run() {
				System.out.println("Cargando Datos. Espere...");
				for (int i = 0; i < 55000000; i++) {
					Math.pow(i, 3);
				}
				VentanaPrincipal v = new VentanaPrincipal();
				
				System.out.println("Ya está todo listo, Bienvenido.");
			}
		});
		hilo.start();
	}

}
