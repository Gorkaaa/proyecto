package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domain.GestorMarket;
import domain.Producto;
import domain.TipoAlimento;
import domain.TipoHigieneYBelleza;
import domain.TipoLimpieza;
import domain.TipoProducto;

public class VentanaPrincipal extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(VentanaPrincipal.class.getName());
	
	protected GestorMarket gestor;
	protected VentanaInicioSesion ventanaInicioSesion;
	protected VentanaCarroCompra ventanaCarroCompra;
	protected VentanaAdministracionUsuarios ventanaAdministracionUsuarios;
	protected VentanaAdministracionEmpleados ventanaAdministracionEmpleados;
	protected ImageIcon iconoUsuario;
	protected ImageIcon iconoCesta;
	protected ImageIcon iconoGestorUsuario;
	protected ImageIcon iconoGAO;
	protected JButton botonCesta;
	protected static JButton botonUsuario;
	protected static JButton botonGestionUsuario;
	protected JPanel backgroundPanel;
	protected List<Producto> productos;
	
	public VentanaPrincipal(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu jMenu = new JMenu("Menú");
		jMenu.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				updatePanelProductos(gestor.getGestorBD().listarProductos());
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		JMenu jProductos = new JMenu("Productos");

		// Menú Alimentos
		JMenu jAlimentos = new JMenu("Alimentos");
		for (TipoAlimento tipo : TipoAlimento.values()) {
			JMenuItem menuTipo = new JMenuItem(tipo.toString());
			jAlimentos.add(menuTipo);
			menuTipo.addActionListener(e -> updatePanelProductos(gestor.getGestorBD().listarProductosPorCategoria(tipo.toString())));
		}
		
		// Menú Higiene y Belleza
		JMenu jHigieneYBelleza = new JMenu("Higiene y Belleza");
		for (TipoHigieneYBelleza tipo : TipoHigieneYBelleza.values()) {
			JMenuItem menuTipo = new JMenuItem(tipo.toString());
			jHigieneYBelleza.add(menuTipo);
			menuTipo.addActionListener(e -> updatePanelProductos(gestor.getGestorBD().listarProductosPorCategoria(tipo.toString())));
		}
		
		// Menú Limpieza
		JMenu jLimpieza = new JMenu("Limpieza");
		for (TipoLimpieza tipo : TipoLimpieza.values()) {
			JMenuItem menuTipo = new JMenuItem(tipo.toString());
			jLimpieza.add(menuTipo);
			menuTipo.addActionListener(e -> updatePanelProductos(gestor.getGestorBD().listarProductosPorCategoria(tipo.toString())));
		}

		jProductos.add(jAlimentos);
		jProductos.add(jHigieneYBelleza);
		jProductos.add(jLimpieza);

		// Menú Promociones
		JMenu jPromociones = new JMenu("Promociones");
		jPromociones.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				updatePanelProductos(gestor.getGestorBD().listarProductosConDescuento());
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});

		menuBar.add(jMenu);
		menuBar.add(jProductos);
		menuBar.add(jPromociones);

		this.setJMenuBar(menuBar);
		
		
		ventanaInicioSesion = new VentanaInicioSesion(gestor);
		ventanaAdministracionUsuarios = new VentanaAdministracionUsuarios(gestor);
		ventanaAdministracionEmpleados = new VentanaAdministracionEmpleados(gestor);
		
		iconoCesta = new ImageIcon("resources/iconos/carritoCompra.png");
		iconoCesta = new ImageIcon(iconoCesta.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonCesta = new JButton(iconoCesta);
		botonCesta.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(gestor.getUsuario() == null && gestor.getEmpleado() == null) {
					JOptionPane.showMessageDialog(null, "Primero tienes que iniciar sesión");
					ventanaInicioSesion.setVisible(true);
				}else {
					ventanaCarroCompra = new VentanaCarroCompra(gestor);
					ventanaCarroCompra.setVisible(true);
				}
			}
			
		});
		
		iconoUsuario = new ImageIcon("resources/iconos/usuario.png");
		iconoUsuario = new ImageIcon(iconoUsuario.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonUsuario = new JButton(iconoUsuario);
		botonUsuario.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(gestor.getUsuario() == null && gestor.getEmpleado() == null)
					ventanaInicioSesion.setVisible(true);
				else {
					int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
					if (confirmacion == JOptionPane.YES_OPTION) {
						GestorMarket.usuario = null;
						GestorMarket.empleado = null;
						botonUsuario.setIcon(iconoUsuario);
						botonGestionUsuario.setEnabled(false);
					}
				}	
			}
		});
		
		iconoGestorUsuario = new ImageIcon("resources/iconos/gestionUsuario.png");
		iconoGestorUsuario = new ImageIcon(iconoGestorUsuario.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonGestionUsuario = new JButton(iconoGestorUsuario);
		botonGestionUsuario.setEnabled(false);
		botonGestionUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] gestion = {"Administrar Usuarios", "Administrar Empleados", "Administrar Stock"};
				String resp = (String) JOptionPane.showInputDialog( null, "Selecciona que quiere administar", "Modo de trabajo", JOptionPane.QUESTION_MESSAGE, null, gestion, "Administrar Usuarios");
				if(resp != null){
					if (resp.equals(gestion[2])) {
						VentanaStock stock = new VentanaStock(gestor);
						stock.setVisible(true);
					}else {
						if(gestor.getEmpleado().getNomUsuario().equals("admin")) {
							if (resp.equals(gestion[0])) ventanaAdministracionUsuarios.setVisible(true);
							else ventanaAdministracionEmpleados.setVisible(true);
						}else
							JOptionPane.showMessageDialog(null, "Solo el usuario Administrador puede gestionar esta sección.");

					}
				}
			}
		});
		
		iconoGAO = new ImageIcon("resources/imagenes/GAOmarket.png");
		iconoGAO = new ImageIcon(iconoGAO.getImage().getScaledInstance(404, 114, Image.SCALE_SMOOTH));
		
		JLabel imagenGAO = new JLabel();
		imagenGAO.setIcon(iconoGAO);
			
		JPanel panelArriba = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));		
		JPanel panelIconos = new JPanel(new BorderLayout());
		JPanel panelSubIconos = new JPanel(new FlowLayout());

		panelArriba.add(imagenGAO);
		panelArriba.add(panelIconos);
		panelSubIconos.add(botonGestionUsuario);
		panelSubIconos.add(botonUsuario);
		panelIconos.add(panelSubIconos, "North");
		panelIconos.add(botonCesta, "Center");
		
		cp.add(panelArriba, BorderLayout.NORTH);
		
		
		backgroundPanel = new JPanel();
		backgroundPanel.setBackground(Color.GREEN);
		JScrollPane scrollPane = new JScrollPane(backgroundPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        cp.add(scrollPane);
        
        productos = gestor.getGestorBD().listarProductos();
		createRowPanels(backgroundPanel);

		
        
		 
		ImageIcon iconoPrincipal = new ImageIcon("resources/iconos/iconoGAO.png");
		iconoPrincipal = new ImageIcon(iconoPrincipal.getImage().getScaledInstance(207, 207, Image.SCALE_SMOOTH));
			
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas cerrar el programa por completo?", "Confirmar cierre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (confirm == JOptionPane.YES_OPTION) {
					gestor.getGestorBD().disconnect();
					logger.info("Programa finalizado");
					System.exit(0);
                }
            }

			@Override
			public void windowOpened(WindowEvent e) {
				
			}

        });

		
		this.setIconImage(iconoPrincipal.getImage());
		
		this.setTitle("GAO Market");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(85, 30, 1400, 730);
		this.setVisible(false);
		logger.info("Progrma iniciado");
	}
	
	
	
	
	private void createRowPanels(JPanel backgroundPanel) {
		int numColumnas = 4;
		
		int filas = productos.size() / numColumnas;
		
		JPanel panelProductos = new JPanel();
		panelProductos.setLayout(new GridLayout(filas, numColumnas, 15, 15));
		panelProductos.setOpaque(false);
		panelProductos.setBorder(BorderFactory.createEmptyBorder());
		
		for (Producto producto : productos) {
			JPanel productPanel = createRowPanel(producto, 100, 100);
			panelProductos.add(productPanel);
		}
		backgroundPanel.add(panelProductos);
	}
	

	// 	#IAG gorkaBidaurratzagaPérez_2023-11-05_18-30.txt  El uso de la IAG se ha utilizado para la creación de los metodos createRowPanels y createRowPanel
	// 		y se han realizado cambios a ambos metodos para garantizar su funcionalidad, mejor aspecto y buen rendimiento del programa.
	
	private JPanel createRowPanel(Producto producto, int rowHeight, int colWidth) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new LineBorder(Color.BLACK));
    	
    	
    	JPanel panelContenido = new JPanel();
    	panelContenido.setLayout(new BorderLayout());
    	panelContenido.setBorder(new LineBorder(Color.BLACK));
    	
    	JPanel panelFoto = new JPanel();            
		
		ImageIcon productoIcono = new ImageIcon("resources/productos/" + producto.getImagen());
		productoIcono = new ImageIcon(productoIcono.getImage().getScaledInstance(colWidth, rowHeight, Image.SCALE_SMOOTH));
		
		JLabel imageLabel = new JLabel(productoIcono);
		panelFoto.add(imageLabel);
		
		JPanel jPanelArriba = new JPanel();
		jPanelArriba.setLayout(new BorderLayout());
		jPanelArriba.setBorder(new LineBorder(Color.BLACK));
		
		JLabel textLabel = new JLabel(producto.getNombre());
		JLabel priceLabel = new JLabel(String.format("Precio %.2f €  ", producto.getPrecio()));
		
		jPanelArriba.add(textLabel, BorderLayout.WEST);
		jPanelArriba.add(priceLabel, BorderLayout.EAST);
		jPanelArriba.setBorder(new EmptyBorder(0, 10, 0, 10));

        JPanel jPanelMedio = new JPanel();
		jPanelMedio.setLayout(new BorderLayout());
		jPanelMedio.setBorder(new LineBorder(Color.BLACK));
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
		
		JButton addButton = new JButton("Añadir a la cesta");
		addButton.addActionListener(e -> {
			if(gestor.getUsuario() == null && gestor.getEmpleado() == null) {
				JOptionPane.showMessageDialog(null, "Primero tienes que iniciar sesión");
				ventanaInicioSesion.setVisible(true);
			}else {
				int cantidad = (int) spinner.getValue();
				productos.add(new Producto(producto.getId(), producto.getImagen(), producto.getNombre(), producto.getPrecio(), cantidad, producto.getTipoProducto(), producto.getCategoria(), producto.getEstado(), producto.getDescuento()));
				
				gestor.getProductos().add(new Producto(producto.getId(), producto.getImagen(), producto.getNombre(), producto.getPrecio(), cantidad, producto.getTipoProducto(), producto.getCategoria(), producto.getEstado(), producto.getDescuento()));
				spinner.setValue(1);
				if(gestor.getUsuario() != null)
					gestor.getGestorXML().anadirProducto(producto, cantidad, gestor.getUsuario().getNomUsuario());
				else
					gestor.getGestorXML().anadirProducto(producto, cantidad, gestor.getEmpleado().getNomUsuario());
				JOptionPane.showMessageDialog(null, "Producto añadido a la cesta");
			}
		});
		
		if (producto.getDescuento() > 0) {
			double resultado = (Double) producto.getPrecio() * (1 - ((double) producto.getDescuento()) / 100);
			resultado = Math.round(resultado * 100.0) / 100.0;
			JLabel labelPrecioConDescuento = new JLabel(String.format("¡PROMOCION! Descuento del %d %%  ->  %.2f €  ", producto.getDescuento(),resultado));
			jPanelMedio.add(labelPrecioConDescuento, BorderLayout.WEST);
			jPanelMedio.add(spinner, BorderLayout.EAST);
		} else {
			jPanelMedio.add(spinner, BorderLayout.CENTER);
		}
		
		

		
		JPanel panelAnyadir = new JPanel();
		panelAnyadir.setBorder(new LineBorder(Color.BLACK));
		panelAnyadir.add(addButton);
	
		panelContenido.add(jPanelArriba, BorderLayout.NORTH);
		panelContenido.add(jPanelMedio, BorderLayout.CENTER);
		panelContenido.add(panelAnyadir, BorderLayout.SOUTH);
		
		panel.add(panelFoto, BorderLayout.NORTH);
		panel.add(panelContenido, BorderLayout.SOUTH);

		return panel;
	}
	
	private void updatePanelProductos(List<Producto> nuevosProductos) {
		backgroundPanel.removeAll();
		productos = nuevosProductos;
		createRowPanels(backgroundPanel);
		backgroundPanel.revalidate(); // Importante para actualizar el layout
		backgroundPanel.repaint();    // Importante para repintar
	}

}
