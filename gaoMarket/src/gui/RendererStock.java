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
		
//		if (column == 0) {
//            table.getColumnModel().getColumn(0).setPreferredWidth(3);
//        } else if (column == 3) {
//            table.getColumnModel().getColumn(3).setPreferredWidth(5);
//        } else if (column == 4) {
//            table.getColumnModel().getColumn(4).setPreferredWidth(4);
//        } else if (column == 8) {
//            table.getColumnModel().getColumn(8).setPreferredWidth(3);
//        } else if (column == 9) {
//            table.getColumnModel().getColumn(9).setPreferredWidth(5);
//        }
		
		if (column == 8) {
			setText(String.format("%d%%", Integer.parseInt(value.toString())));
		}
		
		
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
