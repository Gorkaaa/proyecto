package gui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import domain.Producto;

public class VentanaCarroCompra extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel<Producto> modeloCarrito;
	private JList<Producto> lstCarrito;
	private JButton btnEliminar;
    
	public VentanaCarroCompra() {
		setTitle("Carrito de la Compra");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLayout(new BorderLayout());
		
		modeloCarrito = new DefaultListModel<>();
		lstCarrito = new JList<>(modeloCarrito);
		btnEliminar = new JButton("Eliminar del carrito");
		
		JScrollPane carritoScrollPane = new JScrollPane(lstCarrito);
		JPanel panelInferior = new JPanel();
		panelInferior.add(btnEliminar);
		
		add(carritoScrollPane, BorderLayout.CENTER);
		add(panelInferior, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new VentanaCarroCompra();
			}
		});
	}
}
