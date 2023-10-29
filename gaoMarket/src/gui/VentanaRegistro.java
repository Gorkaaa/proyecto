package gui;

import javax.swing.*;

import domain.GestorMarket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JTextField cajaNombre;
	protected JTextField cajaApellidos;
	protected JTextField cajaUsuario;
	protected JTextField cajaCorreo;
    protected JTextField cajaTelefono;
    protected JPasswordField cajaContrasena;
    protected GestorMarket gestor;
    
    public VentanaRegistro(GestorMarket gestor) {
    	this.gestor = gestor;
		Container cp = this.getContentPane();
		
       
        
        // Crear componentes
        JLabel nombreLabel = new JLabel("Nombre:");
        cajaNombre = new JTextField(15);
        JLabel apellidosLabel = new JLabel("Apellidos:");
        cajaApellidos = new JTextField(30);  
        JLabel telefonoLabel = new JLabel("Número de teléfono:");
        cajaTelefono = new JTextField(30);
        
        JLabel usuarioLabel = new JLabel("Nombre Usuario:");
        cajaUsuario = new JTextField(15);
        JLabel correoLabel = new JLabel("Correo electrónico:");
        cajaCorreo = new JTextField(40);
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        cajaContrasena = new JPasswordField(15);
        JButton botonRegistrarse = new JButton("Registrarse");
        
        // Agregar ActionListener al botón de registro
        botonRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String usuario = cajaUsuario.getText();
                char[] contrasenya = cajaContrasena.getPassword();
                JOptionPane.showMessageDialog(null, "Registrado con éxito: " + usuario);
                
                // Limpia los campos después del registro
                cajaUsuario.setText("");
                cajaContrasena.setText("");
            }
        });
        
        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));
        panel.add(nombreLabel);
        panel.add(cajaNombre);
        panel.add(apellidosLabel);
        panel.add(cajaApellidos);
        panel.add(telefonoLabel);
        panel.add(cajaTelefono);
        panel.add(usuarioLabel);
        panel.add(cajaUsuario);
        panel.add(correoLabel);
        panel.add(cajaCorreo);
        panel.add(contrasenaLabel);
        panel.add(cajaContrasena);
        panel.add(botonRegistrarse);
        
        cp.add(panel);
        
        this.setTitle("Registro de usuario");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 200);
        this.setVisible(false);
        
    }
}
