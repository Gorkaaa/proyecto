package gui;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
	protected ImageIcon iconoCarro;
	protected JButton botonCesta;
	protected JButton botonUsuario;

	public VentanaPrincipal(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		ventanaCarroCompra = new VentanaCarroCompra(gestor);
		ventanaInicioSesion = new VentanaInicioSesion(gestor);
		
		iconoUsuario = new ImageIcon("resources/iconos/carritoCompra.png");
		iconoCarro = new ImageIcon("resources/iconos/usuario.png");
		
		
		
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
		
		JPanel panel = new JPanel();
		
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiar a DISPOSE_ON_CLOSE por si el usuario se equivoca, que no cierre todo el programa.
		this.setSize(640, 480);
		this.setTitle("GAO Market");
		this.setVisible(false);
	}		
}
