package gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import domain.Producto;
import domain.Producto.Estado;

public class RendererStock implements TableCellRenderer {


	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Producto p = new Producto();
		JLabel label = new JLabel();
		
		label.setBackground(table.getBackground());
		
		label.setHorizontalAlignment(JLabel.CENTER);
		switch (column) {
			case 0:
				label.setText(value.toString());
			case 1:
				label.setText(value.toString());
				break;
			case 2:
				label.setText(String.format("%.2f €", Double.parseDouble(value.toString())));
				break;
			case 3:
				label.setText(value.toString());
				break;
			case 4:
				label.setText(String.format("%.2f €", Double.parseDouble(value.toString())));
				break;
            default:
                break;
        }
		
		if (p.getEstado() == Estado.POCAS_UNIDADES) {
			label.setBackground(Color.YELLOW);
		} else if (p.getEstado() == Estado.AGOTADO) {
			label.setBackground(Color.RED);
		} else {
			label.setBackground(Color.GREEN);
		}
		
		label.setOpaque(true);
		return label;
	}

}
