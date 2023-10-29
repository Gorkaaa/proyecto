package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.GestorMarket;

public class VentanaPrincipal extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	protected VentanaInicioSesion ventanaInicioSesion;
	protected VentanaCarroCompra ventanaCarroCompra;
	protected ImageIcon iconoUsuario;
	protected ImageIcon iconoCesta;
	protected JButton botonCesta;
	protected JButton botonUsuario;
	protected JTextField barraBusqueda;
	
	public VentanaPrincipal(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		ventanaCarroCompra = new VentanaCarroCompra(gestor);
		ventanaInicioSesion = new VentanaInicioSesion(gestor);
		
		iconoCesta = new ImageIcon("resources/iconos/carritoCompra.png");
		iconoCesta = new ImageIcon(iconoCesta.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonCesta = new JButton(iconoCesta);
		
		botonCesta.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaCarroCompra.setVisible(true);
			}
			
		});
		
		iconoUsuario = new ImageIcon("resources/iconos/usuario.png");
		iconoUsuario = new ImageIcon(iconoUsuario.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonUsuario = new JButton(iconoUsuario);
		
		botonUsuario.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaInicioSesion.setVisible(true);
				
			}
		});
		
		
		barraBusqueda = new JTextField(20);
		
		
			
		
		//Prueba de como añadir un panel "carta" de cada producto
		/*
		Producto p = new Producto("Producto1", "descipcion 1........................",
				"Acer_Wallpaper_01_3840x2400.jpg", 15.7, 2, null);
		CartaProducto cartaP1 = new CartaProducto();
		cartaP1.Dibujar(p);
		
		CartaProducto cp1 = new CartaProducto();
		JPanel cartaProducto = cp1.Dibujar(p);
		this.add(cartaProducto);
		*/
		
		JPanel panelArriba = new JPanel();
		JPanel panelArribaDerecha = new JPanel();
		
		panelArriba.add(barraBusqueda, BorderLayout.CENTER);
		panelArribaDerecha.add(botonCesta, BorderLayout.EAST);
		panelArribaDerecha.add(botonUsuario, BorderLayout.EAST);

		cp.add(panelArribaDerecha, BorderLayout.EAST);
		cp.add(panelArriba, BorderLayout.NORTH);
		
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiar a DISPOSE_ON_CLOSE por si el usuario se equivoca, que no cierre todo el programa.
		this.setSize(640, 480);
		this.setTitle("GAO Market");
		this.setVisible(false);
	}		
}
