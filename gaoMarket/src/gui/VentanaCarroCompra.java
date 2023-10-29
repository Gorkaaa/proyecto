package gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import domain.GestorMarket;
import domain.Producto;

public class VentanaCarroCompra extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	protected DefaultListModel<Producto> modeloCarrito;
	protected JList<Producto> lstCarrito;
	protected JButton btnEliminar;
    
	public VentanaCarroCompra(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		this.setLayout(new BorderLayout());
		
		modeloCarrito = new DefaultListModel<>();
		lstCarrito = new JList<>(modeloCarrito);
		btnEliminar = new JButton("Eliminar del carrito");
		
		JScrollPane carritoScrollPane = new JScrollPane(lstCarrito);
		JPanel panelInferior = new JPanel();
		panelInferior.add(btnEliminar);
		
		cp.add(carritoScrollPane, BorderLayout.CENTER);
		cp.add(panelInferior, BorderLayout.SOUTH);
		
		
		this.setTitle("Carrito de la Compra");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 300);
		this.setVisible(false);
	}

}
