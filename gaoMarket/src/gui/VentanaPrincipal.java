package gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class VentanaPrincipal extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 480);
		setTitle("GAOmarket");
		
		
		
		
		setVisible(true);
	}
	
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new VentanaPrincipal();
			}
			
		});

	}

}
