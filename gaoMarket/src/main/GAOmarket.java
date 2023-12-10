package main;

import java.util.logging.Level;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import domain.GestorMarket;
import gui.*;

public class GAOmarket {
	protected static GestorMarket gestor;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				VentanaPrincipal ventana = new VentanaPrincipal(gestor);
				Thread hilo = new Thread(new Runnable() {
					@Override
					public void run() {
						
						 
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							gestor.getLogger().log(Level.INFO, "Error al iniciar Ventana: " + e);
						}
						 JOptionPane.getRootFrame().dispose();
						
					}
				});
				hilo.start();
				ventana.setVisible(true);
			}
		});
	}
}
