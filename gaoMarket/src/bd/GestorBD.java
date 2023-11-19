package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import domain.Alimento;
import domain.GestorMarket;
import domain.HigieneYBelleza;
import domain.Limpieza;
import domain.TipoAlimento;
import domain.TipoHigieneYBelleza;
import domain.TipoLimpieza;
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
	public Usuario verificarCredenciales(String nomUsuario, String password) { 
		Usuario u = null;
		 try{                  		         
				conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	        	String sql = "SELECT * FROM usuario WHERE nomUsuario = '" + nomUsuario + "' AND contrasenya = '"+ password +"'";
	        	st = conn.createStatement();
	            ResultSet rs = realizarQuery(sql);           
	            if(rs.next()) { 
	            	u = new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("nomUsuario"), rs.getInt("numTelefono"), rs.getString("correoElectronico"), rs.getString("contrasenya"));
	            }
	            rs.close();
	            st.close();
	            conn.close();
	         }
	         catch (Exception e)  {
	 			gestor.getLogger().log(Level.SEVERE, "Error en verificarCredenciales(Usuario, password): " + e);
	         } 
		 return u;
	}
	
	// Metodo que busca un usuario en la BD por su email
	public boolean buscaUsuario(String nomUsuario) {
		boolean existe = false;
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	        String sql = "SELECT * FROM usuario WHERE  = '" + nomUsuario +"'";
        	st = conn.createStatement();
            ResultSet rs = realizarQuery(sql);           
            if (rs.next()) existe = true;
            rs.close();
            st.close();
            conn.close();
         }
	     catch (Exception e)  {
	    	 gestor.getLogger().log(Level.SEVERE, "Error en buscaUsuario(Usuario): " + e);
	     } 
		 return existe;
	}
	
	//Metodo para introducir un nuevo usuario en la base de datos
	public boolean guardarUsuario(Usuario u) {
		if(buscaUsuario(u.getCorreoElectronico()))
			return false;
	    boolean guardado = false;
	    String sql =
	      "INSERT INTO usuario(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasenya) "
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
	public boolean borrarUsuario(String nomUsuario){
		String sql = "DELETE FROM usuario WHERE nomUsuario=?";
		try{
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
		  	PreparedStatement ps = conn.prepareStatement(sql);
	       	ps.setString(1, nomUsuario); 
	       	ps.executeUpdate();
	       	ps.close();
	       	conn.close();
	       	return true;
	   	} catch (SQLException ex) {
	   		gestor.getLogger().log(Level.SEVERE, "Error en metodo borrarUsuario: " + ex);
	       	return false;
	   	}
	}
	
	// Metodo que devuelve todos los usuarios
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "select nombre, apellidos, nomUsuario, numTelefono, correoElectronico, "
				+ "contrasenya from usuario;";
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
            	String contrasenya = capitalize(rs.getString("contrasenya"));
            	Usuario u = new Usuario();
		        u.setNombre(nombre);
		        u.setApellidos(apellidos);
		        u.setNomUsuario(nomUsuario);
		        u.setNumTelefono(numTelefono);
		        u.setCorreoElectronico(correoElectronico);
		        u.setContrasenya(contrasenya);
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
	////Consultas de Productos
	
	//Metodo que devuelve todos los alimentos
	public List<Alimento> listarAlimentos() {
		List<Alimento> alimentos = new ArrayList<>();
		String sql = "SELECT nombre, descripcion, imagen, precio, cantidad, tipoAlimento "
				+ "FROM alimentos";
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	    	st = conn.createStatement();
            ResultSet rs = realizarQuery(sql);
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
            	String descripcion = capitalize(rs.getString("descripcion"));
            	String imagen = rs.getString("imagen");
            	Double precio = rs.getDouble("precio");
            	int cantidad = rs.getInt("cantidad");
            	String tipoAlimento = rs.getString("tipoAlimento");
            	Alimento a = new Alimento();
            	a.setNombre(nombre);
            	//a.setDescripcion(descripcion);
            	a.setImagen(imagen);
            	a.setPrecio(precio);
            	a.setCantidad(cantidad);
            	switch (tipoAlimento) { 
                case "CARNICOS":
                	a.setTipoAlimento(TipoAlimento.CARNICOS);
                	break;
                case "VEGETALES":
                	a.setTipoAlimento(TipoAlimento.VEGETALES);
                	break;
                case "BEBIDAS":
                	a.setTipoAlimento(TipoAlimento.BEBIDAS);
                	break;
                case "CONGELADOS":
                	a.setTipoAlimento(TipoAlimento.CONGELADOS);
                	break;
                case "DULCES":
                	a.setTipoAlimento(TipoAlimento.DULCES);
                	break;
              }
            	alimentos.add(a);
            }
            
          rs.close();
  	      st.close();
  	      conn.close();
  	    } catch (SQLException e) {
  	    	gestor.getLogger().log(Level.SEVERE, "Error en metodo listarAlimentos: " + e);
  	    }
		return alimentos;
	}
	
	//Metodo que devuelve todos los productos de limpieza
	public List<Limpieza> listarLimpieza() {
		List<Limpieza> lstLimpieza = new ArrayList<>();
		String sql = "SELECT nombre, descripcion, imagen, precio, cantidad, tipoLimpieza "
				+ "FROM limpieza";
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	    	st = conn.createStatement();
            ResultSet rs = realizarQuery(sql);
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
            	String descripcion = capitalize(rs.getString("descripcion"));
            	String imagen = rs.getString("imagen");
            	Double precio = rs.getDouble("precio");
            	int cantidad = rs.getInt("cantidad");
            	String tipoLimpieza = rs.getString("tipoLimpieza");
            	Limpieza l = new Limpieza();
            	l.setNombre(nombre);
            	//l.setDescripcion(descripcion);
            	l.setImagen(imagen);
            	l.setPrecio(precio);
            	l.setCantidad(cantidad);
            	switch (tipoLimpieza) { 
                case "UTENSILIOS":
                	l.setTipoLimpieza(TipoLimpieza.UTENSILIOS);
                	break;
                case "PRODUCTOS_LIMPIEZA":
                	l.setTipoLimpieza(TipoLimpieza.PRODUCTOS_LIMPIEZA);
                	break;
              }
            	lstLimpieza.add(l);
            }
            
          rs.close();
  	      st.close();
  	      conn.close();
  	    } catch (SQLException e) {
  	    	gestor.getLogger().log(Level.SEVERE, "Error en metodo listarLimpieza: " + e);
  	    }
		return lstLimpieza;
	}
	
	//Metodo que devuelve todos los productos Higiene y belleza
	public List<HigieneYBelleza> listarHigieneYBelleza() {
		List<HigieneYBelleza> lstHigieneYBelleza = new ArrayList<>();
		String sql = "SELECT nombre, descripcion, imagen, precio, cantidad, tipoHigieneYBelleza "
				+ "FROM HigieneYBelleza";
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:resources/db/GAOmarket.db");
	    	st = conn.createStatement();
            ResultSet rs = realizarQuery(sql);
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
            	String descripcion = capitalize(rs.getString("descripcion"));
            	String imagen = rs.getString("imagen");
            	Double precio = rs.getDouble("precio");
            	int cantidad = rs.getInt("cantidad");
            	String tipoAlimento = rs.getString("tipoAlimento");
            	HigieneYBelleza hb = new HigieneYBelleza();
            	hb.setNombre(nombre);
            	//hb.setDescripcion(descripcion);
            	hb.setImagen(imagen);
            	hb.setPrecio(precio);
            	hb.setCantidad(cantidad);
            	switch (tipoAlimento) { 
                case "AFEITADO_DEPILACION":
                	hb.setTipoHigieneYBelleza(TipoHigieneYBelleza.AFEITADO_DEPILACION);
                	break;
                case "HIGIENE_BUCAL":
                	hb.setTipoHigieneYBelleza(TipoHigieneYBelleza.HIGIENE_BUCAL);
                	break;
                case "HIGIENE_INTIMA":
                	hb.setTipoHigieneYBelleza(TipoHigieneYBelleza.HIGIENE_INTIMA);
                	break;
                case "CUIDADO_CORPORAL":
                	hb.setTipoHigieneYBelleza(TipoHigieneYBelleza.CUIDADO_CORPORAL);
                	break;
                case "PARAFARMACIA_SOLARES":
                	hb.setTipoHigieneYBelleza(TipoHigieneYBelleza.PARAFARMACIA_SOLARES);
                	break;
              }
            	lstHigieneYBelleza.add(hb);
            }
            
          rs.close();
  	      st.close();
  	      conn.close();
  	    } catch (SQLException e) {
  	    	gestor.getLogger().log(Level.SEVERE, "Error en metodo listarHigieneYBelleza: " + e);
  	    }
		return lstHigieneYBelleza;
	}
}
