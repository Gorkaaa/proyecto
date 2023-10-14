package gui;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Producto;

public class CartaProducto extends JPanel{

	public static final String RUTA_CARPETA_IMG = "../resources/Imagenes/";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void Dibujar(Producto p) {
		JLabel impProd = new JLabel(new ImageIcon(RUTA_CARPETA_IMG+p.getImagen()));
		JLabel cantidad = new JLabel("Stock: " + p.getCantidad());
	}
	
	
	//Prueba main
	public static void main(String[] args) {
		Producto p = new Producto("Producto1", "descipcion 1........................",
				"Acer_Wallpaper_01_3840x2400.jpg", 15.7, 2, null);
		
	}
}
