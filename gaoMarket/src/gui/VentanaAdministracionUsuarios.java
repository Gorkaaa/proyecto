package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import domain.Empleado;
import domain.GestorMarket;
import domain.Usuario;

public class VentanaAdministracionUsuarios extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	
	public VentanaAdministracionUsuarios(GestorMarket gestor) {
		
		
		this.setLayout(new BorderLayout());
		
		DefaultListModel<Usuario> modeloUsuarios = new DefaultListModel<>();
		DefaultListModel<Empleado> modeloEmpleados = new DefaultListModel<>();
		//Prueba
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Nombre1", "Apellido1", "usu1", 666666666, "correo@gmail.com", "contrasenya"));
		usuarios.add(new Usuario("Nombre2", "Apellido2", "usu2", 666666666, "correo@gmail.com", "contrasenya"));
		usuarios.add(new Usuario("Nombre3", "Apellido3", "usu3", 666666666, "correo@gmail.com", "contrasenya"));
		usuarios.add(new Usuario("Nombre4", "Apellido4", "usu4", 666666666, "correo@gmail.com", "contrasenya"));
		modeloUsuarios.addAll(usuarios);
		
		/*
		List<Empleado> empleados = new ArrayList<>();
		empleados.add(new Empleado("Nombre1", "Apellido1", "usu1", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre2", "Apellido2", "usu2", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre3", "Apellido3", "usu3", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre4", "Apellido4", "usu4", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		modeloEmpleados.addAll(empleados);*/

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
	            setText(value.toString());

	            
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
		add(scrollPane, "Center");
		
		/*JRadioButton rb_Usuarios = new JRadioButton("Usuarios");
		JRadioButton rb_Empleados = new JRadioButton("Empleados");

        ButtonGroup grupoBotones = new ButtonGroup();
        grupoBotones.add(rb_Usuarios);
        grupoBotones.add(rb_Empleados);

        JPanel panelIzq = new JPanel();
        panelIzq.add(rb_Usuarios);
        panelIzq.add(rb_Empleados);
        add(panelIzq, "West");*/
		
		JButton botonBorrar = new JButton("Eliminar Usuario");
		botonBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!lstUsuarios.isSelectionEmpty()) {
					int usuarioSeleccionado = lstUsuarios.getSelectedIndex();
					int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el usuario?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
					if (confirmacion == JOptionPane.YES_OPTION) {
						modeloUsuarios.remove(usuarioSeleccionado);
					}
				}
			}
		});
		
		JButton botonAñadirUsuario = new JButton("Añadir Usuario");
		botonAñadirUsuario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro ventanaRegistro = new VentanaRegistro(gestor);
				ventanaRegistro.setVisible(true);				
			}
		});
		
		JPanel panelBotones = new JPanel();
		panelBotones.add(botonAñadirUsuario);
		panelBotones.add(botonBorrar);

		add(panelBotones, "South");
		
		
		this.setTitle("Lista de usuarios/empleados");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 480);
		this.setVisible(false);
		
	}

}