package ventanas;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dinosaurio.*;

public class VentanaTablaCaracteristicas extends JFrame {
	protected GestorDinos gestor;
	protected JList<String> listaDinos;
	protected JTable tablaCaracteristicas;
	protected DefaultTableModel modeloTablaCaracteristicas;
	
	
	public VentanaTablaCaracteristicas (GestorDinos gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();		
		
		modeloTablaCaracteristicas = new DefaultTableModel();
		tablaCaracteristicas = new JTable(modeloTablaCaracteristicas);

		
		modeloTablaCaracteristicas.addColumn("Nombre");
		modeloTablaCaracteristicas.addColumn("ID");
		modeloTablaCaracteristicas.addColumn("Altura");
		modeloTablaCaracteristicas.addColumn("Peso");
		modeloTablaCaracteristicas.addColumn("Conducta");
		
	
		for (Caracteristica caracteristica : gestor.getCaracteristicas()) {
			modeloTablaCaracteristicas.addRow(new Object[] {caracteristica.getNombre(), caracteristica.getId(), caracteristica.getAltura() + " m", 
											caracteristica.getPeso()+" Kg", caracteristica.getConducta()});
		}

		JScrollPane scrollPane = new JScrollPane(tablaCaracteristicas);
		cp.add(scrollPane);
    
		this.setTitle("Tabla Caracteristicas");
		this.setBounds(300, 300, 750, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	
	public void actualizarTabla() {
		modeloTablaCaracteristicas.setRowCount(0);
	    for (Caracteristica caracteristica : gestor.getCaracteristicas()) {
	    	modeloTablaCaracteristicas.addRow(new Object[] { caracteristica.getNombre(), caracteristica.getId(),
            								caracteristica.getAltura() + " m", caracteristica.getPeso() + " Kg", caracteristica.getConducta() });
	    }
	}
	
	public void guardarCaracteristicasTXT(String textoCaracteristicas) {
		try {
			PrintWriter pw = new PrintWriter(textoCaracteristicas);
		
			pw.println(textoCaracteristicas);
			pw.println("");
		
			for (Caracteristica caracteristica : gestor.getCaracteristicas()) {
				pw.println("El " + caracteristica.getNombre() +", con ID:"+ caracteristica.getId() +", medía "+ caracteristica.getAltura() +"m de altura y pesaba "+ 
						caracteristica.getPeso() +"Kg. Se cree que era un animal "+ caracteristica.getConducta() + " por su apariencia.");
				pw.println("");
			}
				
			pw.close();
		
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar datos de texto.");
		}
	
	}

}
