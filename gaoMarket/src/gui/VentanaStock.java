package gui;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import domain.Producto;
import domain.TipoAlimento;
import domain.TipoHigieneYBelleza;
import domain.TipoLimpieza;
import domain.TipoProducto;

public class VentanaStock extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel pOeste, pCentro, pNorte;
	protected DefaultTreeModel modeloArbol;
	protected JTree arbol;
	protected JScrollPane scrollArbol;
	protected ModeloStock modeloTabla;
	protected JTable tabla;
	protected JScrollPane scrollTabla;
	protected JSlider sUnidades;
	protected RendererStock renderer;
	protected ImageIcon iconoStock;
	
	protected String tipoProductoSeleccionado;
	protected String categoriaSeleccionada;
    
    public VentanaStock() {
		super();
		tipoProductoSeleccionado = "";
		categoriaSeleccionada = "";
		
		iconoStock = new ImageIcon("resources/iconos/iconoGAO.png");
		iconoStock = new ImageIcon(iconoStock.getImage().getScaledInstance(207, 207, Image.SCALE_SMOOTH));
		
		
		pOeste = new JPanel();
		pNorte = new JPanel();
		pCentro = new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		DefaultMutableTreeNode nraiz = new DefaultMutableTreeNode("TIPOS DE PRODUCTO");
		modeloArbol = new DefaultTreeModel(nraiz);
		arbol = new JTree(modeloArbol);
		scrollArbol = new JScrollPane(arbol);
		cargarArbol();
		getContentPane().add(scrollArbol, BorderLayout.WEST);

		renderer = new RendererStock();
		modeloTabla = new ModeloStock(null);
		tabla = new JTable(modeloTabla);
		tabla.setDefaultRenderer(Object.class, renderer);
 
		scrollTabla = new JScrollPane(tabla);
		getContentPane().add(scrollTabla, BorderLayout.CENTER);

        arbol.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getPath();
                tipoProductoSeleccionado = tp.getLastPathComponent().toString();
                categoriaSeleccionada = tp.getLastPathComponent().toString();
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
                Object selectedObject = selectedNode.getUserObject();
                
                if (selectedObject instanceof TipoProducto) {
                    tipoProductoSeleccionado = selectedObject.toString();

                    if (CadenaProductos.getMapaTipoProducto().containsKey(TipoProducto.valueOf(tipoProductoSeleccionado))) {
                    	//Esta lista que he añadido tengo que hacerlo en los JMenu para que cuando toque se filtren los productos de la VentanaPrincipal.
                        List<Producto> l = CadenaProductos.obtenerListaTipoProductos(TipoProducto.valueOf(tipoProductoSeleccionado));
                        tabla.setModel(new ModeloStock(l));
                    }
                } else if (selectedObject instanceof TipoAlimento) {
                	categoriaSeleccionada = ((TipoAlimento) selectedObject).toString();

                    if (CadenaProductos.getMapaCategoriaProducto().containsKey(TipoAlimento.valueOf(categoriaSeleccionada))) {
                        List<Producto> l = CadenaProductos.obtenerListaCategoriaProductos(TipoAlimento.valueOf(categoriaSeleccionada));
                        tabla.setModel(new ModeloStock(l));
                    }
                } else if (selectedObject instanceof TipoHigieneYBelleza) {
                	categoriaSeleccionada = ((TipoHigieneYBelleza) selectedObject).toString();

                    if (CadenaProductos.getMapaCategoriaProducto().containsKey(TipoHigieneYBelleza.valueOf(categoriaSeleccionada))) {
                        List<Producto> l = CadenaProductos.obtenerListaCategoriaProductos(TipoHigieneYBelleza.valueOf(categoriaSeleccionada));
                        tabla.setModel(new ModeloStock(l));
                    }
                } else {
                	categoriaSeleccionada = ((TipoLimpieza) selectedObject).toString();

                    if (CadenaProductos.getMapaCategoriaProducto().containsKey(TipoLimpieza.valueOf(categoriaSeleccionada))) {
                        List<Producto> l = CadenaProductos.obtenerListaCategoriaProductos(TipoLimpieza.valueOf(categoriaSeleccionada));
                        tabla.setModel(new ModeloStock(l));
                    }
                }
//                tabla.getColumnModel().getColumn(0).setPreferredWidth(3);
//                tabla.getColumnModel().getColumn(3).setPreferredWidth(1);
//                tabla.getColumnModel().getColumn(4).setPreferredWidth(4);
//                tabla.getColumnModel().getColumn(8).setPreferredWidth(3);
//                tabla.getColumnModel().getColumn(9).setPreferredWidth(5);
            }
            
           
        });
        
        setBounds(25, 100, 1490, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(false);
		setIconImage(iconoStock.getImage());
        
	}

	private void cargarArbol() {
		DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) modeloArbol.getRoot();

		for (TipoProducto tipoProducto : TipoProducto.values()) {
			DefaultMutableTreeNode tipoProductoNode = new DefaultMutableTreeNode(tipoProducto);
		    
			if (tipoProducto.equals(TipoProducto.ALIMENTO)) {
				for (TipoAlimento tipoAlimento : TipoAlimento.values()) {
					DefaultMutableTreeNode tipoAlimentoNode = new DefaultMutableTreeNode(tipoAlimento);
					tipoProductoNode.add(tipoAlimentoNode);
				}
			} else if (tipoProducto.equals(TipoProducto.HIGIENE_Y_BELLEZA)) {
				for (TipoHigieneYBelleza tipoHigiene : TipoHigieneYBelleza.values()) {
					DefaultMutableTreeNode tipoHigieneNode = new DefaultMutableTreeNode(tipoHigiene);
					tipoProductoNode.add(tipoHigieneNode);
				}
			} else if (tipoProducto.equals(TipoProducto.LIMPIEZA)) {
				for (TipoLimpieza tipoLimpieza : TipoLimpieza.values()) {
					DefaultMutableTreeNode tipoLimpiezaNode = new DefaultMutableTreeNode(tipoLimpieza);
					tipoProductoNode.add(tipoLimpiezaNode);
				}
			}
			raiz.add(tipoProductoNode);
		}
		modeloArbol.reload();
    }
}
