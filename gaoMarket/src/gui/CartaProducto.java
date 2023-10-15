package gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Producto;

public class CartaProducto extends JPanel{

	public static final String RUTA_CARPETA_IMG = "../resources/Imagenes/";
	public static final String MONEDA = "€";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void Dibujar(Producto p) {
		JLabel imgProd = new JLabel(new ImageIcon(RUTA_CARPETA_IMG+p.getImagen()));
		JLabel nombre = new JLabel(p.getNombre());
		JComboBox<Integer> stock = new JComboBox<Integer>();
		for(int i = 0; i<p.getCantidad(); i++) {
			stock.addItem(i);
		}
		JLabel precio = new JLabel("Precio: " + p.getPrecio() + MONEDA);
		JButton bot_Aniadir = new JButton("AÑADIR");
		bot_Aniadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int cantidadSeleccionada = (Integer)stock.getSelectedItem();
				System.out.println("Añadir producto");
			}
		});
		
		this.setLayout(new BorderLayout());
		
		JPanel panel_Sur = new JPanel();
		JPanel panelCant_Precio = new JPanel();
		panelCant_Precio.add(stock);
		panelCant_Precio.add(precio);
		panel_Sur.setLayout(new BorderLayout());
		panel_Sur.add(nombre, "North");
		panel_Sur.add(panelCant_Precio, "Center");
		panel_Sur.add(bot_Aniadir, "South");
		
		this.add(imgProd, "Center");
		this.add(panel_Sur, "South");
	}
	
	
	//Prueba main
	public static void main(String[] args) {
		Producto p = new Producto("Producto1", "descipcion 1........................",
				"Acer_Wallpaper_01_3840x2400.jpg", 15.7, 2, null);
		CartaProducto cartaP1 = new CartaProducto();
		cartaP1.Dibujar(p);
		
	}
}
