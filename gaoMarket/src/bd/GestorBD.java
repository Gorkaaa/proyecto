package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import domain.GestorMarket;
import domain.Usuario;

public class GestorBD {
	private GestorMarket gestor;
	private Connection conn;
	private Statement st;
	
	public GestorBD(GestorMarket gestor){
		
	}
	public GestorBD() {
		// Carga del Driver de SQLite
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			gestor.getLogger().log(Level.SEVERE, "No se ha podido cargar el driver de la base de datos");
		}
	}
	
	/*public void conexionBD() {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
			st = conn.createStatement();
			
			// Consultas, busquedas, etc
			
			
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	public ResultSet realizarQuery(String sql) {
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			gestor.getLogger().log(Level.SEVERE, "No se ha podido realizar la consulta " + sql);
		}
		return rs;
	}
	
	// Metodo que busca un usuario en la BD por su email y contraseña
	public Usuario buscaUsuario(String email, String password) { 
		Usuario u = null;
		 try{                  		         
				conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	        	String sql = "SELECT * FROM usuario WHERE email = '" + email + "' AND contrasena = '"+ password +"'";
	        	st = conn.createStatement();
	            ResultSet rs = realizarQuery(sql);           
	            if(rs.next()) { 
	            	u = new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("nomUsuario"), rs.getInt("telefono"), rs.getString("email"), rs.getString("contrasena"));
	            }
	            rs.close();
	            st.close();
	            conn.close();
	         }
	         catch (Exception e)  {
	 			gestor.getLogger().log(Level.SEVERE, "Error en buscaUsuario(Email, password): " + e);
	         } 
		 return u;
	}
}
