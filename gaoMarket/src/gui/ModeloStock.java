package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import domain.*;
import domain.Producto.Estado;

public class ModeloStock extends DefaultTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	protected List<Producto> productos;
	protected final List<String> cabeceras = Arrays.asList("ID", "Nombre", "Imagen", "Precio", "Cantidad", "Tipo Producto", "Categoría", "Estado", "Descuento", "Precio Con Descuento");
			
	
	public ModeloStock(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	@Override
	public String getColumnName(int column) {
		return cabeceras.get(column);
	}
	
	@Override
	public int getRowCount() {
		if (productos != null) {
			return productos.size();
		} else {
			return 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		return cabeceras.size();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return true;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		
		Producto p = productos.get(row);
		switch (column) {
			case 0: return p.getId();
			case 1: return p.getNombre();
			case 2: return p.getImagen();
			case 3: return p.getPrecio();
			case 4: return Integer.valueOf(p.getCantidad());
			case 5: return p.getTipoProducto();
			case 6: return p.getCategoria();
			case 7: return p.getEstado();
			case 8: return p.getDescuento();
			case 9:  double resultado = ((Double) p.getPrecio()) * (1 - ((double) p.getDescuento()) / 100);
				return Math.round(resultado * 100.0) / 100.0;
			default: return null;
		}
	}
	
}
