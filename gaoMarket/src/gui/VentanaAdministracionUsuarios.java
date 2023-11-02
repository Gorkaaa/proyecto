package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import domain.GestorMarket;
import domain.Usuario;

public class VentanaAdministracionUsuarios extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	protected List<Usuario> usuarios;
	
	public VentanaAdministracionUsuarios(GestorMarket gestor) {
		this.setLayout(new BorderLayout());
				
		//Prueba
		usuarios = new ArrayList<>();
		usuarios.add(new Usuario("Nombre1", "Apellido1", "usu1", 666666666, "correo@gmail.com", "contrasenya"));
		usuarios.add(new Usuario("Nombre2", "Apellido2", "usu2", 666666666, "correo@gmail.com", "contrasenya"));
		usuarios.add(new Usuario("Nombre3", "Apellido3", "usu3", 666666666, "correo@gmail.com", "contrasenya"));
		usuarios.add(new Usuario("Nombre4", "Apellido4", "usu4", 666666666, "correo@gmail.com", "contrasenya"));
		
		/*
		DefaultListModel<Empleado> modeloEmpleados = new DefaultListModel<>();
		List<Empleado> empleados = new ArrayList<>();
		empleados.add(new Empleado("Nombre1", "Apellido1", "usu1", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre2", "Apellido2", "usu2", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre3", "Apellido3", "usu3", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre4", "Apellido4", "usu4", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		modeloEmpleados.addAll(empleados);
		
		JRadioButton rb_Usuarios = new JRadioButton("Usuarios");
		JRadioButton rb_Empleados = new JRadioButton("Empleados");

        ButtonGroup grupoBotones = new ButtonGroup();
        grupoBotones.add(rb_Usuarios);
        grupoBotones.add(rb_Empleados);

        JPanel panelIzq = new JPanel();
        panelIzq.add(rb_Usuarios);
        panelIzq.add(rb_Empleados);
        add(panelIzq, "West");*/
		
		class ModeloTabla extends AbstractTableModel {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			String[] cabeceras = { "Nombre", "Apellidos", "Nombre Usuario", "Teléfono", "Correo", "Contraseña"};
				
			public ModeloTabla(List<Usuario> usuarios) {
				VentanaAdministracionUsuarios.this.usuarios = usuarios;
			}

			@Override
			public Object getValueAt(int row, int column) {
				Usuario u = usuarios.get(row);
				switch (column) {
					case 0: return u.getNombre();
					case 1: return u.getApellidos();
					case 2: return u.getNomUsuario();
					case 3: return u.getNumTelefono();
					case 4: return u.getCorreoElectronico();
					case 5: return u.getContrasenya();
				}
				return null;
			}

			@Override
			public int getRowCount() {
				return usuarios.size();
			}

			@Override
			public int getColumnCount() {
				return cabeceras.length;
			}

			@Override
			public String getColumnName(int column) {
				return cabeceras[column];
			}
			
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	        
	        @Override
	        public void setValueAt(Object value, int row, int column) {
	            Usuario u = usuarios.get(row);
	            switch (column) {
	                case 0:
	                    u.setNombre((String) value);
	                    break;
	                case 1:
	                    u.setApellidos((String) value);
	                    break;
	                case 2:
	                    u.setNomUsuario((String) value);
	                    break;
	                case 3:
	                    u.setNumTelefono((int) value);
	                    break;
	                case 4:
	                    u.setCorreoElectronico((String) value);
	                    break;
	                case 5:
	                    u.setContrasenya((String) value);
	                    break;
	            }
	            
	            fireTableCellUpdated(row, column);
	        }
		}
		
		JTextField buscador = new JTextField(20);
		
		class RendererTabla extends JLabel implements TableCellRenderer{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				String cellText = value.toString();
				
				//Pone en negrita el string que aparece en las celdas
				if(!buscador.getText().isBlank() && cellText.startsWith(buscador.getText())) {
					setText(String.format("<html><b>%s</b>%s</htnml>",
							buscador.getText(),
							cellText.substring(buscador.getText().length())
							));
				}else {
					setText(cellText);
				}
				
				return this;
			}
			
		}
		
		ModeloTabla modeloTabla = new ModeloTabla(usuarios);
		JTable tablaUsuarios = new JTable(modeloTabla);
		tablaUsuarios.setDefaultRenderer(Object.class, new RendererTabla());
		JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
		add(scrollPane, "Center");
		
		JButton botonBorrar = new JButton("Eliminar Usuario");
		/*botonBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!tablaUsuarios.isSelectionEmpty()) {
					int usuarioSeleccionado = tablaUsuarios.getSelectedIndex();
					int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el usuario?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
					if (confirmacion == JOptionPane.YES_OPTION) {
						modeloUsuarios.remove(usuarioSeleccionado);
					}
				}
			}
		});*/
		
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
		
		this.setTitle("Lista de usuarios");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 480);
		this.setVisible(false);
		
	}

}