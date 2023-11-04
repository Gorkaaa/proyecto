package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domain.GestorMarket;

public class VentanaPrincipal extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	protected VentanaInicioSesion ventanaInicioSesion;
	protected VentanaCarroCompra ventanaCarroCompra;
	protected VentanaAdministracionUsuarios ventanaAdministracionUsuarios;
	protected VentanaAdministracionEmpleados ventanaAdministracionEmpleados;
	protected ImageIcon iconoUsuario;
	protected ImageIcon iconoCesta;
	protected ImageIcon iconoGestorUsuario;
	protected ImageIcon iconoGAO;
	protected JButton botonCesta;
	protected JButton botonUsuario;
	protected JButton botonGestionUsuario;
	protected JTextField barraBusqueda;
	
	public VentanaPrincipal(GestorMarket gestor) {
		this.gestor = gestor;
		Container cp = this.getContentPane();
		
		ventanaCarroCompra = new VentanaCarroCompra(gestor);
		ventanaInicioSesion = new VentanaInicioSesion(gestor);
		ventanaAdministracionUsuarios = new VentanaAdministracionUsuarios(gestor);
		ventanaAdministracionEmpleados = new VentanaAdministracionEmpleados(gestor);
		
		iconoCesta = new ImageIcon("resources/iconos/carritoCompra.png");
		iconoCesta = new ImageIcon(iconoCesta.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonCesta = new JButton(iconoCesta);
		botonCesta.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaCarroCompra.setVisible(true);
			}
			
		});
		
		iconoUsuario = new ImageIcon("resources/iconos/usuario.png");
		iconoUsuario = new ImageIcon(iconoUsuario.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonUsuario = new JButton(iconoUsuario);
		botonUsuario.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaInicioSesion.setVisible(true);
				
			}
		});
		
		iconoGestorUsuario = new ImageIcon("resources/iconos/gestionUsuario.png");
		iconoGestorUsuario = new ImageIcon(iconoGestorUsuario.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		botonGestionUsuario = new JButton(iconoGestorUsuario);
		botonGestionUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] gestion = {"Administrar Usuarios", "Administrar Empleados"};
				String resp = (String) JOptionPane.showInputDialog( null, "Selecciona que quiere administar", "Modo de trabajo", JOptionPane.QUESTION_MESSAGE, null, gestion, "Administrar Usuarios");
				if (resp.equals(gestion[0])){
					ventanaAdministracionUsuarios.setVisible(true);
				}else {
					ventanaAdministracionEmpleados.setVisible(true);
				}
			}
			
		});
		
		barraBusqueda = new JTextField(20);
		
		//Prueba de como añadir un panel "carta" de cada producto
		/*
		Producto p = new Producto("Producto1", "descipcion 1........................",
				"Acer_Wallpaper_01_3840x2400.jpg", 15.7, 2, null);
		CartaProducto cartaP1 = new CartaProducto();
		cartaP1.Dibujar(p);
		
		CartaProducto cp1 = new CartaProducto();
		JPanel cartaProducto = cp1.Dibujar(p);
		this.add(cartaProducto);
		*/
		
		iconoGAO = new ImageIcon("resources/imagenes/GAOmarket.png");
		iconoGAO = new ImageIcon(iconoGAO.getImage().getScaledInstance(404, 114, Image.SCALE_SMOOTH));
		
		JLabel imagenGAO = new JLabel();
		imagenGAO.setIcon(iconoGAO);
			
		JLayeredPane panelArriba = new JLayeredPane();
		panelArriba.setPreferredSize(new Dimension(1200, 130));

		panelArriba.add(barraBusqueda);
		panelArriba.add(botonGestionUsuario);
		panelArriba.add(botonUsuario);
		panelArriba.add(botonCesta);
		panelArriba.add(imagenGAO);

		barraBusqueda.setBounds(550, 75, 280, 35);
		botonGestionUsuario.setBounds(1060, 6, 45, 45);
		botonUsuario.setBounds(1120, 6, 45, 45);
		botonCesta.setBounds(1050, 60, 125, 60);
		imagenGAO.setBounds(20, 10, 404, 114);
		
		cp.add(panelArriba, BorderLayout.NORTH);
		
		JLayeredPane panelAbajo = new JLayeredPane();
        
		panelAbajo.setBackground(Color.BLACK);
		panelAbajo.setPreferredSize(new Dimension(1200, 600));
		panelAbajo.setOpaque(true);
		
		 cp.add(panelAbajo);
		
		this.setTitle("GAO Market");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cambiar a DISPOSE_ON_CLOSE por si el usuario se equivoca, que no cierre todo el programa.
		this.setBounds(150, 40, 1200, 730);
		this.setVisible(false);
	}		
}
