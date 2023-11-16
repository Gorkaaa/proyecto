package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	
	private static final String capitalize(String str) {
		if (str == null || str.length() == 0) return str;

	    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
	//// Consultas de Usuarios
	
	// Metodo que busca un usuario en la BD por su email y contraseña
	public Usuario verificarCredenciales(String email, String password) { 
		Usuario u = null;
		 try{                  		         
				conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	        	String sql = "SELECT * FROM usuario WHERE email = '" + email + "' AND contrasena = '"+ password +"'";
	        	st = conn.createStatement();
	            ResultSet rs = realizarQuery(sql);           
	            if(rs.next()) { 
	            	u = new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("nomUsuario"), rs.getInt("numTelefono"), rs.getString("correoElectronico"), rs.getString("contrasena"));
	            }
	            rs.close();
	            st.close();
	            conn.close();
	         }
	         catch (Exception e)  {
	 			gestor.getLogger().log(Level.SEVERE, "Error en verificarCredenciales(Email, password): " + e);
	         } 
		 return u;
	}
	
	// Metodo que busca un usuario en la BD por su email
	public boolean buscaUsuario(String email) {
		boolean existe = false;
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	        String sql = "SELECT * FROM usuario WHERE email = '" + email +"'";
        	st = conn.createStatement();
            ResultSet rs = realizarQuery(sql);           
            if (rs.next()) existe = true;
            rs.close();
            st.close();
            conn.close();
         }
	     catch (Exception e)  {
	    	 gestor.getLogger().log(Level.SEVERE, "Error en buscaUsuario(Email): " + e);
	     } 
		 return existe;
	}
	
	//Metodo para introducir un nuevo usuario en la base de datos
	public boolean guardarUsuario(Usuario u) {
		if(buscaUsuario(u.getCorreoElectronico()))
			return false;
	    boolean guardado = false;
	    String sql =
	      "INSERT INTO usuario(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasena) "
	      + "VALUES(?, ?, ?, ?, ?)";
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
		  	PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, capitalize(u.getNombre()));
			ps.setString(2, u.getApellidos());
			ps.setString(3, u.getNomUsuario());
			ps.setInt(4, u.getNumTelefono());
			ps.setString(5, u.getCorreoElectronico());
			ps.setString(6, u.getContrasenya());
			ps.executeUpdate();
			guardado = true;
			
			ps.close();
			conn.close();
		} catch (SQLException ex) {
			gestor.getLogger().log(Level.SEVERE, "Error en metodo guardarCliente: " + ex);
		}
	
	    return guardado;
	}
	
	//Metodo que elimina un usuario pasado su email
	public boolean borrarUsuario(String email){
		String sql = "DELETE FROM usuario WHERE correoElectronico=?";
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
		  	PreparedStatement ps = conn.prepareStatement(sql);
	       	ps.setString(1, email); 
	       	ps.executeUpdate();
	       	ps.close();
	       	conn.close();
	       	return true;
	   	} catch (SQLException ex) {
	   		gestor.getLogger().log(Level.SEVERE, "Error en metodo borrarProducto: " + ex);
	       	return false;
	   	}
	}
	
	// Metodo que devuelve todos los usuarios
	public ArrayList<Usuario> listarUsuarios() {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select nombre, apellidos, nomUsuario, numTelefono, correoElectronico, "
				+ "contrasena from usuario;";
	    try {
	    	conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	    	st = conn.createStatement();
            ResultSet rs = realizarQuery(sql);
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
            	String apellidos = capitalize(rs.getString("apellidos"));
            	String nomUsuario = capitalize(rs.getString("nomUsuario"));
            	int numTelefono = rs.getInt("numTelefono");
            	String correoElectronico = rs.getString("correoElectronico");
            	String contrasena = capitalize(rs.getString("contrasena"));
            	Usuario u = new Usuario();
		        u.setNombre(nombre);
		        u.setApellidos(apellidos);
		        u.setNomUsuario(nomUsuario);
		        u.setNumTelefono(numTelefono);
		        u.setCorreoElectronico(correoElectronico);
		        u.setContrasenya(contrasena);
		        usuarios.add(u);
	      }

	      rs.close();
	      st.close();
	      conn.close();
	    } catch (SQLException e) {
	    	gestor.getLogger().log(Level.SEVERE, "Error en metodo listarUsuarios: " + e);
	    }
	    return usuarios;
	   }
}
