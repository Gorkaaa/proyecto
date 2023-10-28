package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import domain.Usuario;

public class VentanaAdministracionUsuarios extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VentanaAdministracionUsuarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setTitle("Lista de usuarios");
		
		//setLayout();
		
		DefaultListModel<Usuario> modeloUsuarios = new DefaultListModel<>();
		//Prueba
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario());
		usuarios.add(new Usuario());
		usuarios.add(new Usuario());
		usuarios.add(new Usuario());
		modeloUsuarios.addAll(usuarios);
		
		JList<Usuario> lstUsuarios = new JList<>(modeloUsuarios);
		
		//la lista solamente admite selección sencilla
		lstUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Se establece el renderer para los elementos de la lista
		class MyCellRenderer extends JLabel implements ListCellRenderer<Usuario>{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<? extends Usuario> list, Usuario value, int index,
					boolean isSelected, boolean cellHasFocus) {
				//Se establece el valor del texto mostrado en el JLabel de la celda
	            //setText(value.toString());
	            setText("-----");

	            
				// Cuando se seleccione un usuario cambia el color de la celda
	            if (isSelected) {
	            	setOpaque(true);
	                setBackground(Color.cyan);
	            }
	            else
	            	setOpaque(false);

	            // se devuelve el componente visual. siempre se devuelve this
	            // porque se evita crear uno nuevo para cada elemento de la lista
	            return this;
			}
			
		}
		lstUsuarios.setCellRenderer(new MyCellRenderer());
		
		
				
		JScrollPane scrollPane = new JScrollPane(lstUsuarios);
		add(scrollPane);
		JButton botonBorrar = new JButton("Eliminar Usuario");
		botonBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lstUsuarios.getSelectedIndex() != -1) {
					System.out.println("Nada");
				}
			}
		});
		
		//add(botonBorrar);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				new VentanaAdministracionUsuarios();
			}
		});
	}
}
