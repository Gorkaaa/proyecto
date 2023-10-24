package ventanas;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


import dinosaurio.*;

public class VentanaTablaInformaciones extends JFrame {
	protected GestorDinos gestor;
	protected JList<String> listaDinos;
	protected JTable tablaInformaciones;
	protected DefaultTableModel modeloTablaInformaciones;


	public VentanaTablaInformaciones (GestorDinos gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();		
		
		modeloTablaInformaciones = new DefaultTableModel();
		tablaInformaciones = new JTable(modeloTablaInformaciones);
		
		
		modeloTablaInformaciones.addColumn("Nombre");
		modeloTablaInformaciones.addColumn("ID");
		modeloTablaInformaciones.addColumn("Comida");
		modeloTablaInformaciones.addColumn("Vida");
		modeloTablaInformaciones.addColumn("Extinto");
		modeloTablaInformaciones.addColumn("Periodo");
		
	
		for (Informacion informacion : gestor.getInformaciones()) {
			modeloTablaInformaciones.addRow(new Object[] {informacion.getNombre(), informacion.getId(), informacion.getComida(), 
											informacion.getVida(), informacion.getExtinto() + " M de años", informacion.getPeriodo()});
		}
		
		JScrollPane scrollPane = new JScrollPane(tablaInformaciones);
		cp.add(scrollPane);
		
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
					guardarInformacionesTXT("informaciones.txt");
			}	
		});
		
		
		this.setTitle("Tabla Informacioes");
		this.setBounds(300, 300, 750, 400);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	
	
	
	public void actualizarTabla() {
		modeloTablaInformaciones.setRowCount(0);

		for (Informacion informacion : gestor.getInformaciones()) {
	    	modeloTablaInformaciones.addRow(new Object[] {informacion.getNombre(), informacion.getId(), informacion.getComida(), 
					informacion.getVida(), informacion.getExtinto() + " M de años", informacion.getPeriodo()});
	    }
	}
	
	
	public void guardarInformacionesTXT(String textoInformaciones) {
		try {
			PrintWriter pw = new PrintWriter(textoInformaciones);
		
			pw.println(textoInformaciones);
			pw.println("");
		
			for (Informacion informacion : gestor.getInformaciones()) {
				pw.println("El " + informacion.getNombre() +", con ID:"+ informacion.getId() +", era un animal "+ informacion.getComida() +" además de "+ 
							informacion.getVida() +". Según los estudios se extinguió hace "+ informacion.getExtinto() + 
							"M de años, lo que viene a ser el "+ informacion.getPeriodo()+".");
				pw.println("");
			}
			
			
		
			pw.close();
		
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar datos de texto.");
		}
	
	}

}

