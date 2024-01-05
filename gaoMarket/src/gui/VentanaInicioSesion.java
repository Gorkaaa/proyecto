package gui;


import domain.GestorMarket;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaInicioSesion extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected GestorMarket gestor;
	protected JTextField cajaUsuario;
	protected JPasswordField cajaContrasena;
	protected VentanaRegistro ventanaRegistro;
	protected ImageIcon iconoUsuario;
	protected ImageIcon iconoContrasena;
	
    public VentanaInicioSesion(GestorMarket gestor) {
    	this.gestor = gestor;
		Container cp = this.getContentPane();
		
		ventanaRegistro = new VentanaRegistro(gestor);
		
        // Crear componentes
		iconoUsuario = new ImageIcon("resources/iconos/usuario.png");
		iconoUsuario = new ImageIcon(iconoUsuario.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
    	JLabel correoLabel = new JLabel(iconoUsuario);
    	cajaUsuario = new JTextField(40);
    	iconoContrasena = new ImageIcon("resources/iconos/llave.png");
    	iconoContrasena = new ImageIcon(iconoContrasena.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
    	JLabel contrasenaLabel = new JLabel(iconoContrasena);
    	cajaContrasena = new JPasswordField(40);
    	JButton botonIniciarSesion = new JButton("Iniciar Sesión");
    	JButton botonRegistrar = new JButton("Registrarse");
    	
    	// Agregar ActionListener al botón de inicio de sesión
    	botonIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = cajaUsuario.getText();
                char[] contrasena = cajaContrasena.getPassword();
                
                // Verifica las credenciales
                if (verificarCredenciales(usuario, new String(contrasena))) {
                	gestor.setUsuario(gestor.getGestorBD().buscarUsuario(usuario));
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión fallido");
                }
                
                // Limpia los campos después del intento de inicio de sesión
                cajaContrasena.setText("");
            }
        });
        
        // Agregar ActionListener al botón de registrarse
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaRegistro.setVisible(true);
                dispose();
            }
        });

        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelUsuario = new JPanel();
        JPanel panelContrasena = new JPanel();
        JPanel panelCajas = new JPanel();
        JPanel panelBotones = new JPanel();

        
        panelUsuario.add(correoLabel);
        panelUsuario.add(cajaUsuario);
        panelContrasena.add(contrasenaLabel);
        panelContrasena.add(cajaContrasena);
        panelCajas.add(panelUsuario);
        panelCajas.add(panelContrasena);
        panelCajas.setBorder(new EmptyBorder(10,50,10,50));
        panelBotones.add(botonIniciarSesion);
        panelBotones.add(botonRegistrar);
        
        panel.add(panelCajas, "Center");
        panel.add(panelBotones, "South");
        cp.add(panel);
        
        ImageIcon iconoPrincipal = new ImageIcon("resources/iconos/iconoGAO.png");
		iconoPrincipal = new ImageIcon(iconoPrincipal.getImage().getScaledInstance(207, 207, Image.SCALE_SMOOTH));
			
		this.setIconImage(iconoPrincipal.getImage());
        
        this.setTitle("Inicio de Sesión");
    	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	this.setBounds(700, 70, 480, 180);
    	this.setVisible(false);
     }

    // Método para verificar las credenciales
    private boolean verificarCredenciales(String usuario, String contrasena) {
    	
    	return usuario.equals("usuario") && contrasena.equals("contrasena");
    }

}
