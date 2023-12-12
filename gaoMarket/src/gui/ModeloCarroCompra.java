package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.GestorMarket;
import domain.Producto;

public class ModeloCarroCompra extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected GestorMarket gestor;
	protected List<Producto> productos;
	protected final List<String> cabeceras = Arrays.asList("Producto", "Nombre", "Precio", "Cantidad", "Total");
			
	
	public ModeloCarroCompra(List<Producto> productos) {
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
		if (row == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	
	@Override
	public Object getValueAt(int row, int column) {
		
		Producto p = productos.get(row);
		switch (column) {
			case 0: return p.getImagen();
			case 1: return p.getNombre();
			case 2: return Double.valueOf(p.getPrecio());
			case 3: return Integer.valueOf(p.getCantidad());
			case 4: return Double.valueOf((Double) (p.getPrecio()*p.getCantidad()));
			default: return null;
		}
	}
}
