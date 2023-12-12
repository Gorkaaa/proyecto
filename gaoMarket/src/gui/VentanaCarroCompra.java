package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.GestorMarket;
import domain.Producto;
import domain.TipoProducto;

public class VentanaCarroCompra extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	protected List<Producto> productos;
	protected JTable tabla;
	protected JScrollPane tablaScroll;
	protected ModeloCarroCompra modeloCarrito;
	protected RendererCarroCompra rendererCompra;
	protected JList<Producto> lstCarrito;
	protected JButton btnEliminar;
	protected JButton btnComprar;
	protected JButton btnVaciar;
	protected JPanel panelIzq;
	protected JPanel panelMedio;
	protected JPanel panelArriba;
	protected JPanel panelAbajo;
    
	public VentanaCarroCompra(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		this.setLayout(new BorderLayout());
		productos = new ArrayList<Producto>();
		
		productos.add(new Producto(1, "Producto 1", "imagen1.png", 19.99, 2, TipoProducto.ALIMENTO));
		productos.add(new Producto(5, "Producto 5", "imagen5.png", 4.20, 6, TipoProducto.ALIMENTO));
		productos.add(new Producto(6, "Producto 6", "imagen6.png", 7.72, 1, TipoProducto.ALIMENTO));
		productos.add(new Producto(7, "Producto 7", "imagen7.png", 8.35, 1, TipoProducto.ALIMENTO));
		
		
		modeloCarrito = new ModeloCarroCompra(productos);
		tabla = new JTable(modeloCarrito);
		tabla.setDefaultRenderer(Object.class, new RendererCarroCompra());
		tabla.setRowHeight(35);
		tablaScroll = new JScrollPane(tabla);
		
		btnEliminar = new JButton("Eliminar del carrito");
		btnComprar = new JButton("Comprar");
		btnVaciar = new JButton("Vaciar carrito");
		
		panelMedio = new JPanel();
		panelArriba = new JPanel();
		panelAbajo = new JPanel();
		
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
		
		panelAbajo.add(btnVaciar);
		panelAbajo.add(btnEliminar);
		panelAbajo.add(btnComprar);
		
		cp.add(tablaScroll);
		cp.add(panelAbajo, BorderLayout.SOUTH);
		
		ImageIcon iconoPrincipal = new ImageIcon("resources/iconos/iconoGAO.png");
		iconoPrincipal = new ImageIcon(iconoPrincipal.getImage().getScaledInstance(207, 207, Image.SCALE_SMOOTH));
		
		this.setIconImage(iconoPrincipal.getImage());
		
		this.setTitle("Carrito de la Compra");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(680, 80, 500, 400);
		this.setVisible(false);
	}
	
	
	public void actualizarCarrito() {
		tabla.setModel(new ModeloCarroCompra(productos));//cambiar por  jModeloCarro.setModel(new ModeloCarroCompra(productosEnCesta));
		RendererCarroCompra compraRenderer = new RendererCarroCompra();

		for (int i=0; i<tabla.getColumnModel().getColumnCount(); i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(compraRenderer);
		}	
	}

}
