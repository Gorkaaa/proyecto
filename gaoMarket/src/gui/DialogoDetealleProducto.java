package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.Producto;

public class DialogoDetealleProducto extends JDialog{
	
	public static final String RUTA_CARPETA_IMG = "resources/Imagenes";
	public static final String MONEDA = "€";
	protected ImageIcon iconoProd;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogoDetealleProducto(JFrame parent, Producto p){
		super(parent, "Mi Ventana de Diálogo", true); // El segundo parámetro (true) indica que el diálogo es modal
		
		iconoProd = new ImageIcon(RUTA_CARPETA_IMG + p.getImagen());
		iconoProd = new ImageIcon(iconoProd.getImage().getScaledInstance(404, 114, Image.SCALE_SMOOTH));
		JLabel imgProd = new JLabel();
		imgProd.setIcon(iconoProd);
		JLabel nombre = new JLabel(p.getNombre());
		JLabel precio = new JLabel("Precio: " + p.getPrecio() + MONEDA);
		JLabel cantidadStock = new JLabel("Quedan " + p.getCantidad() + " de stock");
		
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
		
		JButton botonAceptar = new JButton("ACEPTAR");

        // Agrega un ActionListener al botón
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra el diálogo cuando se hace clic en el botón "Aceptar"
                dispose();
            }
        });
        
        panelDerecho.add(nombre);
        panelDerecho.add(cantidadStock);
        panelDerecho.add(precio);
        
        
        this.setLayout(new BorderLayout());
        this.add(imgProd, "Center");
        this.add(panelDerecho, "West");
		this.add(botonAceptar, "South");        
        
        setSize(300, 150);

        // Centra el diálogo en relación con su padre (ventana principal)
        setLocationRelativeTo(parent);
	}
}
