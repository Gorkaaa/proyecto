package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
	protected JTextField barraBusqueda;
	
	protected List<Producto> productos;
	protected List<Producto> productosEnCesta;
	
	public VentanaPrincipal(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		try (FileInputStream fis = new FileInputStream("src/io/logger.properties")) {
	        LogManager.getLogManager().readConfiguration(fis);
	    } catch (IOException e) {
	        logger.log(Level.SEVERE, "No se pudo leer el fichero de configuración del logger");
	    }
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu jMenu = new JMenu("Menú");
		
		JMenu jProductos = new JMenu("Productos");

		JMenu jAlimentos = new JMenu("Alimentos");
		JMenu jHigieneYBelleza = new JMenu("Higiene y Belleza");
		JMenu jLimpieza = new JMenu("Limpieza");

		JPopupMenu popupMenu = new JPopupMenu();

		jAlimentos.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        popupMenu.removeAll();
		        for (TipoAlimento tipo : TipoAlimento.values()) {
		            JMenuItem menuItem = new JMenuItem(tipo.toString());
		            popupMenu.add(menuItem);
		        }
		        popupMenu.show(jAlimentos, jAlimentos.getWidth(), 0);
		    }
		});

		jHigieneYBelleza.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        popupMenu.removeAll();
		        for (TipoHigieneYBelleza tipo : TipoHigieneYBelleza.values()) {
		            JMenuItem menuItem = new JMenuItem(tipo.toString());
		            popupMenu.add(menuItem);
		        }
		        popupMenu.show(jHigieneYBelleza, jHigieneYBelleza.getWidth(), 0);
		    }
		});

		jLimpieza.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        popupMenu.removeAll();
		        for (TipoLimpieza tipo : TipoLimpieza.values()) {
		            JMenuItem menuItem = new JMenuItem(tipo.toString());
		            popupMenu.add(menuItem);
		        }
		        popupMenu.show(jLimpieza, jLimpieza.getWidth(), 0);
		    }
		});

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
				ventanaCarroCompra.setVisible(true);
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
		
		barraBusqueda = new JTextField(20);
		
		//Prueba de como añadir un panel "carta" de cada producto
		/*
		Producto p = new Producto("Producto1", "descipcion 1........................",
				"Acer_Wallpaper_01_3840x2400.jpg", 15.7, 2, null);
		CartaProducto cartaP1 = new CartaProducto();
		cartaP1.Dibujar(p);
		
		CartaProducto cp1 = new CartaProducto();
		JPanel cartaProducto = cp1.Dibujar(p);
		this.add(cartaProducto);
		*/
		
		iconoGAO = new ImageIcon("resources/imagenes/GAOmarket.png");
		iconoGAO = new ImageIcon(iconoGAO.getImage().getScaledInstance(404, 114, Image.SCALE_SMOOTH));
		
		JLabel imagenGAO = new JLabel();
		imagenGAO.setIcon(iconoGAO);
			
		JLayeredPane panelArriba = new JLayeredPane();
		panelArriba.setPreferredSize(new Dimension(1200, 130));

		panelArriba.add(barraBusqueda);
		panelArriba.add(botonGestionUsuario);
		panelArriba.add(botonUsuario);
		panelArriba.add(botonCesta);
		panelArriba.add(imagenGAO);

		barraBusqueda.setBounds(550, 75, 280, 35);
		botonGestionUsuario.setBounds(1060, 6, 45, 45);
		botonUsuario.setBounds(1120, 6, 45, 45);
		botonCesta.setBounds(1050, 60, 125, 60);
		imagenGAO.setBounds(20, 10, 404, 114);
		
		cp.add(panelArriba, BorderLayout.NORTH);
		
		productos = createProductos();
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
			
		this.setIconImage(iconoPrincipal.getImage());
		
		this.setTitle("GAO Market");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiar a DISPOSE_ON_CLOSE por si el usuario se equivoca, que no cierre todo el programa.
		this.setBounds(150, 40, 1200, 730);
		this.setVisible(false);
		logger.info("Programa finalizado");
	}
	
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

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 0, 10, 1)); // Configurar el JSpinner con valores entre 1 y 10

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
            productosEnCesta.add(new Producto(producto.getId(),producto.getImagen(), producto.getNombre(), producto.getPrecio(), cantidad));
            spinner.setValue(1);
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
        productos.add(new Producto(1, "Producto 1", "imagen1.png", 19.99, 1));
        productos.add(new Producto(2, "Producto 2", "imagen2.png", 24.99, 4));
        productos.add(new Producto(3, "Producto 3", "imagen3.png", 21.52, 2));
        productos.add(new Producto(4, "Producto 4", "imagen4.png", 9.24, 2));
        productos.add(new Producto(5, "Producto 5", "imagen5.png", 4.20, 6));
        productos.add(new Producto(6, "Producto 6", "imagen6.png", 7.72, 0));
        productos.add(new Producto(7, "Producto 7", "imagen7.png", 8.35, 1));
        productos.add(new Producto(8, "Producto 8", "imagen8.png", 1.39, 1));
        productos.add(new Producto(9, "Producto 9", "imagen9.png", 6.01, 0));
        productos.add(new Producto(10, "Producto 10", "imagen10.png", 14.34, 7));

        return productos;
    }
}
