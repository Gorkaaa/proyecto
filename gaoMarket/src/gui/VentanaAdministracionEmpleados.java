package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Empleado;
import domain.GestorMarket;

public class VentanaAdministracionEmpleados extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	protected List<Empleado> empleados;
	protected DefaultTableModel mTablaEmpleado;
	
	public VentanaAdministracionEmpleados(GestorMarket gestor) {
		this.setLayout(new BorderLayout());
				
		//Prueba
		empleados = new ArrayList<>();
		empleados.add(new Empleado("Nombre1", "Apellido1", "usu1", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre2", "Apellido2", "usu2", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre3", "Apellido3", "usu3", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		empleados.add(new Empleado("Nombre4", "Apellido4", "usu4", 666666666, "correo@gmail.com", "contrasenya", "00000000A"));
		
		class ModeloTabla extends AbstractTableModel {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			String[] cabeceras = { "Nombre", "Apellidos", "Nombre Usuario", "Teléfono", "Correo", "Contraseña", "DNI"};
				
			public ModeloTabla(List<Empleado> empleados) {
				VentanaAdministracionEmpleados.this.empleados = empleados;
			}

			@Override
			public Object getValueAt(int row, int column) {
				Empleado e = empleados.get(row);
				switch (column) {
					case 0: return e.getNombre();
					case 1: return e.getApellidos();
					case 2: return e.getNomUsuario();
					case 3: return e.getNumTelefono();
					case 4: return e.getCorreoElectronico();
					case 5: return e.getContrasenya();
					case 6: return e.getDni();
				}
				return null;
			}

			@Override
			public int getRowCount() {
				return empleados.size();
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
	        	Empleado e = empleados.get(row);
	            switch (column) {
	                case 0:
	                    e.setNombre((String) value);
	                    break;
	                case 1:
	                    e.setApellidos((String) value);
	                    break;
	                case 2:
	                    e.setNomUsuario((String) value);
	                    break;
	                case 3:
	                    e.setNumTelefono((int) value);
	                    break;
	                case 4:
	                    e.setCorreoElectronico((String) value);
	                    break;
	                case 5:
	                    e.setContrasenya((String) value);
	                    break;
	                case 6:
	                    e.setDni((String) value);
	                    break;
	            }
	            
	            fireTableCellUpdated(row, column);
	        }
		}
		
		JTextField buscador = new JTextField(40);
		JPanel panelNorte = new JPanel();
		panelNorte.add(new JLabel("Filtro de busqueda: "));
		panelNorte.add(buscador);
		add(panelNorte, "North");
		
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
					setText(String.format("<html><b style='color:red'>%s</b>%s</htnml>",
							buscador.getText(),
							cellText.substring(buscador.getText().length())
							));
				}else {
					setText(cellText);
				}
				
				if(isSelected) {
					setOpaque(true);
					setBackground(Color.green);
				}
				else {
					setOpaque(false);
				}
				
				return this;
			}
			
		}
		
		ModeloTabla modeloTabla = new ModeloTabla(empleados);
		JTable tablaEmpleados = new JTable(modeloTabla);
		tablaEmpleados.setDefaultRenderer(Object.class, new RendererTabla());
		JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
		add(scrollPane, "Center");
		
		buscador.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				tablaEmpleados.repaint();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				tablaEmpleados.repaint();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				tablaEmpleados.repaint();
			}
		});
		
		JButton botonBorrar = new JButton("Eliminar Usuario");
		botonBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int empleadoSeleccionado = tablaEmpleados.getSelectedRow();
				if (empleadoSeleccionado != -1) {
					int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar el empleado?", "Confirmación de eliminación", JOptionPane.YES_NO_OPTION);
					if (confirmacion == JOptionPane.YES_OPTION) {
						empleados.remove(empleadoSeleccionado);
						tablaEmpleados.repaint();
					}
				}
			}
		});
		
		JButton botonAñadirEmpleado = new JButton("Añadir Empleado");
		botonAñadirEmpleado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro ventanaRegistro = new VentanaRegistro(gestor);
				ventanaRegistro.setVisible(true);				
			}
		});
		
		JPanel panelBotones = new JPanel();
		panelBotones.add(botonAñadirEmpleado);
		panelBotones.add(botonBorrar);

		add(panelBotones, "South");
		
		this.setTitle("Lista de empleados");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 480);
		this.setVisible(false);
		
	}

}