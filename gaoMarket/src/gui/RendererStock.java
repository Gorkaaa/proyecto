package gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import domain.Producto.Estado;

public class RendererStock extends DefaultTableCellRenderer {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
	
		Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		setHorizontalAlignment(CENTER);
		
		ModeloStock modelo = (ModeloStock) table.getModel();
		Estado estado = (Estado) modelo.getValueAt(row, 7);

		switch (estado) {
			case POCAS_UNIDADES:
				setBackground(Color.YELLOW);
				break;
			case AGOTADO:
				setBackground(Color.RED);
				break;
			case NORMAL:
				setBackground(Color.GREEN);
				break;
			default:
				setBackground(table.getBackground());
		}
		
		return component;
	}

}
