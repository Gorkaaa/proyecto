package gui;

import java.awt.BorderLayout;
import java.util.List;

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
	
	protected String tipoProductoSeleccionado;
	protected String categoriaSeleccionada;
    
    public VentanaStock() {
        super();
        setBounds(300, 200, 600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);

        tipoProductoSeleccionado = "";
        categoriaSeleccionada = "";
        
        
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

        modeloTabla = new ModeloStock(null);
        tabla = new JTable(modeloTabla);
 
        scrollTabla = new JScrollPane(tabla);
        getContentPane().add(scrollTabla, BorderLayout.CENTER);

        arbol.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getPath();
                tipoProductoSeleccionado = tp.getLastPathComponent().toString();
                
                if (CadenaProductos.getMapaTipoProducto().containsKey(TipoProducto.valueOf(tipoProductoSeleccionado))) {
                    List<Producto> l = CadenaProductos.obtenerListaTipoProductos(TipoProducto.valueOf(tipoProductoSeleccionado));
                    tabla.setModel(new ModeloStock(l));
                }
            }
           
        });
        
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
