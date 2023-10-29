package main;

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
				 ventana.setVisible(true);
			}
		});
	}

}
