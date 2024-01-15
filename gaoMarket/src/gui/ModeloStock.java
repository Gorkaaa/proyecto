package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.*;

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
	
	public Producto getProductoEnFila(int fila) {
		return productos.get(fila);
	}

	public void eliminarProducto(Producto producto) {
		productos.remove(producto);
	}

	
//	@Override
//	public void setValueAt(Object aValue, int row, int column) {
//		
//        super.setValueAt(aValue, row, column);
//	    try {
//	        switch (column) {
//	            case 0: // ID
//	                productos.get(row).setId((Integer) aValue);
//	                break;
//	            case 1: // Nombre
//	                productos.get(row).setNombre((String) aValue);
//	                break;
//	            case 2: // Imagen
//	                productos.get(row).setImagen((String) aValue);
//	                break;
//	            case 3: // Precio
//	                productos.get(row).setPrecio((double) aValue);
//	                break;
//	            case 4: // Cantidad
//	                productos.get(row).setCantidad((int) aValue);
//	                break;
//	            case 5: // Tipo de producto
//	            	TipoProducto tipoProducto = (TipoProducto) aValue;
//	            	boolean isValidTipoProducto = false;
//
//	            	for (TipoProducto enumValue : TipoProducto.values()) {
//	            	    if (enumValue.equals(tipoProducto)) {
//	            	        isValidTipoProducto = true;
//	            	        break;
//	            	    }
//	            	}
//	            	
//	            	if (isValidTipoProducto) {
//	            	    productos.get(row).setTipoProducto(tipoProducto);
//	            	} else {
//	            	    JOptionPane.showMessageDialog(null, "El tipo de producto debe ser uno de los siguientes: " + Arrays.toString(TipoProducto.values()), "Error", JOptionPane.ERROR_MESSAGE);
//	            	}
//	                break;
//	            case 6: // Categoría
//	                productos.get(row).setCategoria((Enum<?>) aValue);
//	                break;
//	            case 7: // Estado
//	                productos.get(row).setEstado((Estado) aValue);
//	                break;
//	            case 8: // Descuento
//	                productos.get(row).setDescuento((int) aValue);
//	                break;
//	            default:
//	                break;
//	        }
//
//
//	    } catch (Exception e) {
//	        JOptionPane.showMessageDialog(null, "Error: Los datos no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
//	    }
//	}

	
	
}
