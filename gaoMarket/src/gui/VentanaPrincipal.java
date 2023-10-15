package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import domain.Producto;

public class VentanaPrincipal extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setTitle("GAOmarket");
		
		Producto p = new Producto("Producto1", "descipcion 1........................",
				"Acer_Wallpaper_01_3840x2400.jpg", 15.7, 2, null);
		CartaProducto cartaP1 = new CartaProducto();
		cartaP1.Dibujar(p);
		
		
		setVisible(true);
	}
	
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaPrincipal();
			}
			
		});

	}

}
