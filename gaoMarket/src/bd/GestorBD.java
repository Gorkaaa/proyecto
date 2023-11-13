package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

import domain.GestorMarket;

public class GestorBD {
	private GestorMarket gestor;
	private Connection conn;
	
	public GestorBD(GestorMarket gestor){
		
	}
	public void cargarDriverBD() {
		// Carga del Driver de SQLite
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			gestor.getLogger().log(Level.SEVERE, "No se ha podido cargar el driver de la base de datos");
		}
	}
	
	public void conexionBD() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
			
			// Consultas, busquedas, etc
			
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
