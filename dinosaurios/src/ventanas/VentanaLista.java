package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;
import dinosaurio.*;

public class VentanaLista extends JFrame {
	protected GestorDinos gestor;	
	protected DefaultListModel<Dinosaurio> modeloListaDinos;
	protected JList<Listable> listaDinos;
	protected JComboBox<Caracteristica> comboCaracteristicas;
	protected JComboBox<Informacion> comboInformaciones;
	protected JButton botonCaracteristicas;
	protected JButton botonCrearCaracteristicas;
	protected JButton botonInformaciones;
	protected JButton botonCrearInformaciones;
	protected JButton botonBorrar;
	protected JButton botonBorrarCaracteristica;
	protected JButton botonBorrarInformacion;
	
	protected VentanaTablaCaracteristicas ventanaTablaCaracteristicas;
	protected VentanaTablaInformaciones ventanaTablaInformaciones;
	
	
	public VentanaLista(GestorDinos gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		ventanaTablaCaracteristicas = new VentanaTablaCaracteristicas(gestor);
		ventanaTablaInformaciones = new VentanaTablaInformaciones(this.gestor);
		
		botonCrearCaracteristicas = new JButton("Crear Característica");
		botonCrearCaracteristicas.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Conducta conducta;
				Caracteristica nuevo = new Caracteristica();
				
				double alturas = (double) (Math.random() * 12) + 0.2;
				double altura =  Math.round(alturas * 100.0) / 100.0;
				
				if (altura <= 1) {
					double pesos = (double) (Math.random() * 2895) + 1.0;
					double peso =  Math.round(pesos * 100.0) / 100.0;
					nuevo.setPeso(peso);
					
				} else if (altura <= 6){
					double pesos = (double) (Math.random() * 6295) + 350.0;
					double peso =  Math.round(pesos * 100.0) / 100.0;
					nuevo.setPeso(peso);
				} else {
					double pesos = (double) (Math.random() * 11005) + 900.0;
					double peso =  Math.round(pesos * 100.0) / 100.0;
					nuevo.setPeso(peso);
				}
				
				int aleatorio = (int) (Math.random() * 3);
				if (aleatorio == 0) {
					conducta = Conducta.neutral;
					nuevo.setConducta(conducta);
				} else if (aleatorio == 1) {
					conducta = Conducta.pasivo;
					nuevo.setConducta(conducta);
				} else {
					conducta = Conducta.agresivo;
					nuevo.setConducta(conducta);
				}				
				
				nuevo.setAltura(altura);
				
				gestor.getCaracteristicas().add(nuevo);	
				
				ventanaTablaCaracteristicas.actualizarTabla();
			}
			
		});
		
		
		botonCrearInformaciones = new JButton("Crear Información");
		botonCrearInformaciones.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Informacion nueva = new Informacion();
				Comida comida;
				Vida vida;
				
				int extinto = (int) (Math.random() * 181) + 65;
				int aleatorio = (int) (Math.random() * 3);
				if (aleatorio == 0) {
					comida = Comida.carnivoro;
				} else if (aleatorio == 1) {
					comida = Comida.omnivoro;
				} else {
					comida = Comida.herbivoro;
				}
				
				int aleatorio2 = (int) (Math.random() * 3);
				if (aleatorio2 == 0) {
					vida = Vida.volador;
				} else if (aleatorio2 == 1) {
					vida = Vida.terrestre;
				} else {
					vida = Vida.acuatico;
				}
				
				Periodo periodo;
				if (extinto >= 201) {
					periodo = Periodo.triasico;
					nueva.setPeriodo(periodo);
				} else if (extinto < 201 && extinto >= 145) {
					periodo = Periodo.jurasico;
					nueva.setPeriodo(periodo);
				} else if (extinto >= 66 && extinto < 145){
					periodo = Periodo.cretacico;
					nueva.setPeriodo(periodo);
				}
			
				nueva.setComida(comida);
				nueva.setVida(vida);
				nueva.setExtinto(extinto);
				
				gestor.getInformaciones().add(nueva);
			}
		});
		
				
		modeloListaDinos = new DefaultListModel<Dinosaurio>();
		listaDinos = new JList(modeloListaDinos);
		for (Dinosaurio dinosaurio : gestor.getDinosaurios()) {
			modeloListaDinos.addElement(dinosaurio);
		}
		
		comboCaracteristicas = new JComboBox();
		for (Caracteristica caracteristica: gestor.getCaracteristicas()) {
			comboCaracteristicas.addItem(caracteristica);
		}
		comboInformaciones = new JComboBox();
		for (Informacion informacion: gestor.getInformaciones()) {
			comboInformaciones.addItem(informacion);
		}
		
		botonCaracteristicas = new JButton("Añadir Característica");
		botonInformaciones = new JButton("Añadir Información");
		botonBorrar = new JButton("BORRAR");
		botonBorrarInformacion = new JButton("Borrar Información");
		botonBorrarCaracteristica = new JButton("Borrar Característica");
		
		comboCaracteristicas.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				comboCaracteristicas.removeAllItems();
				for (Caracteristica caracteristica: gestor.getCaracteristicas()) {
					comboCaracteristicas.addItem(caracteristica);
				}
			}
		});
		
		
		comboInformaciones.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				comboInformaciones.removeAllItems();
				for (Informacion informacion: gestor.getInformaciones()) {
					comboInformaciones.addItem(informacion);
				}
			}
		});
		
		botonCaracteristicas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Caracteristica seleccionado = (Caracteristica) comboCaracteristicas.getSelectedItem();
				gestor.getLista().getLista().add(seleccionado);
				
				modeloListaDinos.addElement(seleccionado);
				
				gestor.getDinosaurios().add(seleccionado);
				
				System.out.println(gestor.getDinosaurios());
				
				
				System.out.println(gestor.getLista().getLista());
			}
		});
		
		botonInformaciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Informacion seleccionado = (Informacion) comboInformaciones.getSelectedItem();
				gestor.getLista().getLista().add(seleccionado);
				
				modeloListaDinos.addElement(seleccionado);
			
				gestor.getDinosaurios().add(seleccionado);
			}
		});
		
		botonBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Listable seleccionada = listaDinos.getSelectedValue();
				gestor.getLista().getLista().remove(seleccionada);
				
				modeloListaDinos.removeElement(seleccionada);
				
				gestor.getDinosaurios().remove(seleccionada);
			}
		});
		
		
		botonBorrarCaracteristica.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Caracteristica seleccionado = (Caracteristica) comboCaracteristicas.getSelectedItem();				
				gestor.getLista().getLista().remove(seleccionado);
				
				gestor.getCaracteristicas().remove(seleccionado);// ESTO LO ELIMINA DEL COMBOBOX
				
				gestor.getDinosaurios().remove(seleccionado);
			}
		});
		
		
		botonBorrarInformacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Informacion seleccionado = (Informacion) comboInformaciones.getSelectedItem();
				gestor.getLista().getLista().remove(seleccionado);
				
				gestor.getInformaciones().remove(seleccionado);// ESTO LO ELIMINA DEL COMBOBOX
				
				gestor.getDinosaurios().remove(seleccionado);
			}
		});	

		
		JPanel izquierda = new JPanel();
		JPanel medio = new JPanel();
		JPanel derecha = new JPanel();
		
		JScrollPane scrollComboCaracteristicas = new JScrollPane(comboCaracteristicas);
		JScrollPane scrollComboInformaciones = new JScrollPane(comboInformaciones);
		
		izquierda.setLayout(new GridLayout(2, 4));
		izquierda.add(new JLabel("               Características "));
		izquierda.add(scrollComboCaracteristicas);
		
		izquierda.add(new JLabel("               Información "));
		izquierda.add(scrollComboInformaciones);
	
		
		medio.setLayout(new GridLayout(6, 2));
		medio.add(botonCaracteristicas, BorderLayout.NORTH);
		medio.add(botonCrearCaracteristicas, BorderLayout.CENTER);
		medio.add(botonBorrarCaracteristica, BorderLayout.SOUTH);

		medio.add(botonInformaciones, BorderLayout.NORTH);
		medio.add(botonCrearInformaciones, BorderLayout.CENTER);
		medio.add(botonBorrarInformacion, BorderLayout.SOUTH);
		
		
		JScrollPane scrollListaDinos = new JScrollPane(listaDinos); 
		
		derecha.setLayout(new BorderLayout());
		derecha.add(new JLabel("Lista de Dinosaurios: "), BorderLayout.NORTH);
		derecha.add(scrollListaDinos, BorderLayout.CENTER);
		derecha.add(new JLabel("BORRAR"), BorderLayout.SOUTH);
		derecha.add(botonBorrar, BorderLayout.SOUTH);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				gestor.guardarDatos("dinosaurios.dat");
				gestor.guardarCaracteristicasCSV("caracteristicas.csv");
				gestor.guardarInformacionesCSV("informaciones.csv");
			}
		});
		

		cp.setLayout(new GridLayout(1, 3));
		cp.add(izquierda);
		cp.add(medio);
		cp.add(derecha);
		

		this.setTitle("DINOSAURIOS");
		this.setSize(1500, 800);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(false);
	}
	
}