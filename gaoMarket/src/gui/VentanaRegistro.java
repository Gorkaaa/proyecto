package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField cajaUsuario;
	private JTextField cajaCorreo;
    private JPasswordField cajaContrasena;
    
    public VentanaRegistro() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 180);
        
        // Crear componentes
        JLabel usuarioLabel = new JLabel("Usuario:");
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
                char[] contrasena = cajaContrasena.getPassword();
                JOptionPane.showMessageDialog(null, "Registrado con éxito: " + usuario);
                
                // Limpia los campos después del registro
                cajaUsuario.setText("");
                cajaCorreo.setText("");
                cajaContrasena.setText("");
            }
        });
        
        // Crear un panel para organizar los componentes
        JPanel panel = new JPanel();
        panel.add(usuarioLabel);
        panel.add(cajaUsuario);
        panel.add(correoLabel);
        panel.add(cajaCorreo);
        panel.add(contrasenaLabel);
        panel.add(cajaContrasena);
        panel.add(botonRegistrarse);
        
        this.add(panel);
    }
}
