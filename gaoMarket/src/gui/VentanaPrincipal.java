package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domain.GestorMarket;
import domain.Producto;
import domain.TipoAlimento;
import domain.TipoHigieneYBelleza;
import domain.TipoLimpieza;
import domain.TipoProducto;
import domain.Usuario;
import domain.Producto.Estado;

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
	protected JButton botonUsuario;
	protected JButton botonGestionUsuario;
	protected JButton botonStock;
	protected JTextField barraBusqueda;
	
	protected List<Producto> productos;
	protected List<Producto> productosEnCesta;
	
	public VentanaPrincipal(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu jMenu = new JMenu("Menú");
		
		JMenu jProductos = new JMenu("Productos");

		JMenu jAlimentos = new JMenu("Alimentos");
		for (TipoAlimento tipo : TipoAlimento.values()) {
			JMenuItem menuTipo = new JMenuItem(tipo.toString());
			jAlimentos.add(menuTipo);
		}
		
		JMenu jHigieneYBelleza = new JMenu("Higiene y Belleza");
		for (TipoHigieneYBelleza tipo : TipoHigieneYBelleza.values()) {
			JMenuItem menuTipo = new JMenuItem(tipo.toString());
			jHigieneYBelleza.add(menuTipo);
		}
		
		JMenu jLimpieza = new JMenu("Limpieza");
		for (TipoLimpieza tipo : TipoLimpieza.values()) {
			JMenuItem menuTipo = new JMenuItem(tipo.toString());
			jLimpieza.add(menuTipo);
		}

		jProductos.add(jAlimentos);
		jProductos.add(jHigieneYBelleza);
		jProductos.add(jLimpieza);

		JMenu jPromociones = new JMenu("Promociones");

		menuBar.add(jMenu);
		menuBar.add(jProductos);
		menuBar.add(jPromociones);

		this.setJMenuBar(menuBar);
		
		ventanaCarroCompra = new VentanaCarroCompra(gestor);
		ventanaInicioSesion = new VentanaInicioSesion(gestor);
		ventanaAdministracionUsuarios = new VentanaAdministracionUsuarios(gestor);
		ventanaAdministracionEmpleados = new VentanaAdministracionEmpleados(gestor);
		
		iconoCesta = new ImageIcon("resources/iconos/carritoCompra.png");
		iconoCesta = new ImageIcon(iconoCesta.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonCesta = new JButton(iconoCesta);
		botonCesta.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(gestor.getUsuario() == null) {
					JOptionPane.showMessageDialog(null, "Primero tienes que iniciar sesión");
					ventanaInicioSesion.setVisible(true);
				}else {
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
				ventanaInicioSesion.setVisible(true);
				
			}
		});
		
		iconoGestorUsuario = new ImageIcon("resources/iconos/gestionUsuario.png");
		iconoGestorUsuario = new ImageIcon(iconoGestorUsuario.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonGestionUsuario = new JButton(iconoGestorUsuario);
		botonGestionUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] gestion = {"Administrar Usuarios", "Administrar Empleados"};
				String resp = (String) JOptionPane.showInputDialog( null, "Selecciona que quiere administar", "Modo de trabajo", JOptionPane.QUESTION_MESSAGE, null, gestion, "Administrar Usuarios");
				if(resp != null){
					if (resp.equals(gestion[0])){
						ventanaAdministracionUsuarios.setVisible(true);
						
					} else {
						ventanaAdministracionEmpleados.setVisible(true);
					}
				}
			}
		});
		
		
		botonStock = new JButton("Gestion de Stock");
		botonStock.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaStock stock = new VentanaStock();
				stock.setVisible(true);
			}
		});
		
		barraBusqueda = new JTextField(20);
		
		iconoGAO = new ImageIcon("resources/imagenes/GAOmarket.png");
		iconoGAO = new ImageIcon(iconoGAO.getImage().getScaledInstance(404, 114, Image.SCALE_SMOOTH));
		
		JLabel imagenGAO = new JLabel();
		imagenGAO.setIcon(iconoGAO);
			
		JPanel panelArriba = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 10));		
		JPanel panelIconos = new JPanel(new BorderLayout());
		JPanel panelSubIconos = new JPanel(new FlowLayout());

		panelArriba.add(imagenGAO);
		panelArriba.add(barraBusqueda);
		panelArriba.add(panelIconos);
		panelSubIconos.add(botonGestionUsuario);
		panelSubIconos.add(botonUsuario);
		panelSubIconos.add(botonStock);
		panelIconos.add(panelSubIconos, "North");
		panelIconos.add(botonCesta, "Center");
		
		cp.add(panelArriba, BorderLayout.NORTH);
		
		//productos = createProductos();
		productos = gestor.getGestorBD().listarProductos();
		productosEnCesta = new ArrayList<>();
		
		JPanel backgroundPanel = new JPanel(new GridLayout(8, 4, 10, 10));
		backgroundPanel.setBackground(Color.GREEN);
		JScrollPane scrollPane = new JScrollPane(backgroundPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        
        cp.add(scrollPane);
		
        createRowPanels(backgroundPanel);
		 
		ImageIcon iconoPrincipal = new ImageIcon("resources/iconos/iconoGAO.png");
		iconoPrincipal = new ImageIcon(iconoPrincipal.getImage().getScaledInstance(207, 207, Image.SCALE_SMOOTH));
			
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
               logger.info("Programa finalizado");
            }
        });
		
		this.setIconImage(iconoPrincipal.getImage());
		
		this.setTitle("GAO Market");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiar a DISPOSE_ON_CLOSE por si el usuario se equivoca, que no cierre todo el programa.
		this.setBounds(150, 40, 1200, 730);
		this.setVisible(false);
		logger.info("Progrma iniciado");
	}
	
	// 	#IAG gorkaBidaurratzagaPérez_2023-11-05_18-30.txt  El uso de la IAG se ha utilizado para la creación de los metodos createRowPanels y createRowPanel
	// 		y se han realizado cambios a ambos metodos para garantizar su funcionalidad, mejor aspecto y buen rendimiento del programa.
	private void createRowPanels(JPanel backgroundPanel) {
        int colCount = 4;
        int colWidth = 180;
        int rowHeight = 120;

        int rowCount = (int) Math.ceil((double) productos.size() / colCount);

        for (int i = 0; i < rowCount; i++) {
            JPanel rowPanel = new JPanel(new GridLayout(1, colCount, 10, 10));

            // Configurar el panel para que tenga fondo transparente y sin márgenes
            rowPanel.setOpaque(false);
            rowPanel.setBorder(BorderFactory.createEmptyBorder());

            int productsInThisRow = Math.min(colCount, productos.size() - i * colCount);

            for (int j = 0; j < colCount; j++) {
                if (j < productsInThisRow) {
                    int index = i * colCount + j;
                    Producto producto = productos.get(index);
                    JPanel productPanel = createRowPanel(producto, rowHeight, colWidth);
                    rowPanel.add(productPanel);
                } else {
                    // Si no hay más productos, agregar un componente vacío para ocupar el espacio restante
                    rowPanel.add(Box.createRigidArea(new Dimension(colWidth, rowHeight)));
                }
            }

            backgroundPanel.add(rowPanel);
        }
    }
	

	// 	#IAG gorkaBidaurratzagaPérez_2023-11-05_18-30.txt  El uso de la IAG se ha utilizado para la creación de los metodos createRowPanels y createRowPanel
	// 		y se han realizado cambios a ambos metodos para garantizar su funcionalidad, mejor aspecto y buen rendimiento del programa.
	
	private JPanel createRowPanel(Producto producto, int rowHeight, int colWidth) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());            
		
		ImageIcon productoIcono = new ImageIcon("resources/productos/" + producto.getImagen());
		productoIcono = new ImageIcon(productoIcono.getImage().getScaledInstance(colWidth, rowHeight, Image.SCALE_SMOOTH));
		
		JLabel imageLabel = new JLabel(productoIcono);
		panel.add(imageLabel, BorderLayout.NORTH);
		
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.setBorder(new LineBorder(Color.BLACK));
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1)); // Configurar el JSpinner con valores entre 1 y 10
		
		JLabel textLabel = new JLabel(producto.getNombre());
		JLabel priceLabel = new JLabel("Precio: " + producto.getPrecio() + "€");
		
		JPanel priceTextPanel = new JPanel(new BorderLayout());
		priceTextPanel.add(textLabel, BorderLayout.WEST);
		priceTextPanel.add(priceLabel, BorderLayout.EAST);
		priceTextPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		
		textPanel.add(priceTextPanel, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Añadir a la cesta");
		addButton.addActionListener(e -> {
			int cantidad = (int) spinner.getValue();
			productosEnCesta.add(new Producto(producto.getId(), producto.getImagen(), producto.getNombre(), producto.getPrecio(), cantidad, producto.getTipoProducto(), producto.getCategoria(), producto.getEstado(), producto.getDescuento()));
			spinner.setValue(1);
			Usuario u = new Usuario("Nombre1", "Apellido1", "usu1", 666666666, "correo@gmail.com", "contrasenya");
			gestor.getGestorXML().anadirProducto(producto, cantidad, u.getNomUsuario());
			JOptionPane.showMessageDialog(null, "Producto añadido a la cesta");
		});

		JPanel spinnerAndButtonPanel = new JPanel(new BorderLayout());
		spinnerAndButtonPanel.add(spinner, BorderLayout.CENTER);
		spinnerAndButtonPanel.add(addButton, BorderLayout.SOUTH);

		textPanel.add(spinnerAndButtonPanel, BorderLayout.SOUTH);

		panel.add(textPanel, BorderLayout.CENTER);

		return panel;
	}
    
	private List<Producto> createProductos() {
		List<Producto> productos = new ArrayList<>();

		// Agregar productos a la lista
		productos.add(new Producto(1, "Producto 1", "imagen1.png", 19.99, 1, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.POCAS_UNIDADES, 0));
		productos.add(new Producto(2, "Producto 2", "imagen2.png", 24.99, 48, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.NORMAL, 10));
		productos.add(new Producto(3, "Producto 3", "imagen3.png", 21.52, 21, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.NORMAL, 30));
		productos.add(new Producto(4, "Producto 4", "imagen4.png", 9.24, 23, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.NORMAL, 15));
		productos.add(new Producto(5, "Producto 5", "imagen5.png", 4.20, 6, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.POCAS_UNIDADES, 5));
		productos.add(new Producto(6, "Producto 6", "imagen6.png", 7.72, 0, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.AGOTADO, 0));
		productos.add(new Producto(7, "Producto 7", "imagen7.png", 8.35, 1, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.POCAS_UNIDADES, 5));
		productos.add(new Producto(8, "Producto 8", "imagen8.png", 1.39, 64, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.NORMAL, 10));
		productos.add(new Producto(9, "Producto 9", "imagen9.png", 6.01, 0, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.AGOTADO, 30));
		productos.add(new Producto(10, "Producto 10", "imagen10.png", 14.34, 72, TipoProducto.ALIMENTO, TipoAlimento.CARNICOS, Estado.NORMAL, 10));
		
		return productos;
	}
}
