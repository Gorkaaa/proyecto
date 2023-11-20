package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
	protected JButton btnComprar;
	protected JButton btnVaciar;
    
	public VentanaCarroCompra(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		this.setLayout(new BorderLayout());
		
		modeloCarrito = new DefaultListModel<>();
		lstCarrito = new JList<>(modeloCarrito);
		btnEliminar = new JButton("Eliminar del carrito");
		btnComprar = new JButton("Comprar");
		btnVaciar = new JButton("Vaciar carrito");
		
		btnVaciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gestor.getGestorXML().vaciarCarrito("usuario");
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gestor.getGestorXML().eliminarProducto("producto", "usuario");
			}
		});
		
		btnComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Actualizar la bbdd primero
				
				gestor.getGestorXML().vaciarCarrito("usuario");
			}
		});
		
		JScrollPane carritoScrollPane = new JScrollPane(lstCarrito);
		JPanel panelInferior = new JPanel();
		panelInferior.add(btnVaciar);
		panelInferior.add(btnEliminar);
		panelInferior.add(btnComprar);
		
		cp.add(carritoScrollPane, BorderLayout.CENTER);
		cp.add(panelInferior, BorderLayout.SOUTH);
		
		ImageIcon iconoPrincipal = new ImageIcon("resources/iconos/iconoGAO.png");
		iconoPrincipal = new ImageIcon(iconoPrincipal.getImage().getScaledInstance(207, 207, Image.SCALE_SMOOTH));
		
		this.setIconImage(iconoPrincipal.getImage());
		
		this.setTitle("Carrito de la Compra");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 300);
		this.setVisible(false);
	}

}
