package bd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.Empleado;
import domain.Producto;
import domain.TipoAlimento;
import domain.TipoHigieneYBelleza;
import domain.TipoLimpieza;
import domain.TipoProducto;
import domain.Usuario;
import domain.Producto.Estado;

public class GestorBD {
	private Connection conn;
	public static final String DB_PATH = "resources/db/GAOmarket.db";
	public static final String DB_PROPERTIES_PATH = "resources/db/configuracion.properties";
	
	private static Logger logger = Logger.getLogger(GestorBD.class.getName());
	
	public GestorBD() {
		configuracionBD();
		connect();
	}
	
	private void configuracionBD() {
		Properties properties = new Properties();
		try  {
			FileInputStream input = new FileInputStream(DB_PROPERTIES_PATH);
			properties.load(input);

			String nombreBD = properties.getProperty("nombreBD");
			String nombre = properties.getProperty("nombre");
			String fecha = properties.getProperty("fecha");
		
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "No se ha podido encontrar el fichero de Base de Datos");
		} catch (IOException e) {
			logger.log(Level.WARNING, "Error cargando las propiedades de la base de Datos");
		}
		
	}
	
	public void connect(){
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			
			Statement statement = conn.createStatement();
			String sent = "CREATE TABLE IF NOT EXISTS Producto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(100), imagen varchar(100), precio double, cantidad int, tipoProducto varchar(100), categoria varchar(100), estado varchar(100), descuento int);";
			statement.executeUpdate( sent );
			sent = "CREATE TABLE IF NOT EXISTS Usuario (nombre varchar(100), apellidos varchar(100), nomUsuario varchar(100), numTelefono int, correoElectronico varchar(100), contrasenya varchar(100));";
			statement.executeUpdate( sent );
			sent = "CREATE TABLE IF NOT EXISTS Empleado (nombre varchar(100), apellidos varchar(100), nomUsuario varchar(100), numTelefono int, correoElectronico varchar(100), contrasenya varchar(100), dni varchar(9));";
			statement.executeUpdate( sent );
			
		} catch (ClassNotFoundException e) {
			logger.log(Level.WARNING, "No se ha podido cargar el driver de la base de datos");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error conectando a la BD", e);
		}
	}
	
	public void disconnect(){
		try {
			conn.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error cerrando la conexión con la BD", e);
		}
	}
	
	public ResultSet realizarQuery(String sql, Statement st) {
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			logger.log(Level.WARNING, "No se ha podido realizar la consulta " + sql);
		}
		return rs;
	}
	
	private static final String capitalize(String str) {
		if (str == null || str.length() == 0) return str;

	    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}
	
	private static final String lower(String str) {
	    return str.toLowerCase();
	}
	
	//// Consultas de Usuarios
	
	// Metodo que busca un usuario en la BD por su nomUsuario y contraseña
	public Usuario verificarCredenciales(String nomUsuario, String password) { 
		Usuario u = null;
    	String sql = "SELECT * FROM usuario WHERE nomUsuario = ? AND contrasenya = ?";

		 try (PreparedStatement ps = conn.prepareStatement(sql)){
	        ps.setString(1, nomUsuario);
	        ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) { 
            	u = new Usuario(rs.getString("nombre"), rs.getString("apellido"), rs.getString("nomUsuario"), rs.getInt("numTelefono"), rs.getString("correoElectronico"), rs.getString("contrasenya"));
            }
            rs.close();
            ps.close();
         }
         catch (Exception e)  {
        	 logger.log(Level.WARNING, "Error en verificarCredenciales(Usuario, password): " + e);
         } 
		 return u;
	}
	
	// Metodo que busca un usuario en la BD por su nomUsuario
	public Usuario buscarUsuario(String usuario) {
		Usuario u = new Usuario();
        String sql = "SELECT * FROM usuario WHERE nomUsuario = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)){
	        ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();        
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
            	String apellidos = capitalize(rs.getString("apellidos"));
            	String nomUsuario = rs.getString("nomUsuario");
            	int numTelefono = rs.getInt("numTelefono");
            	String correoElectronico = lower(rs.getString("correoElectronico"));
            	String contrasenya = rs.getString("contrasenya");
		        u = new Usuario(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasenya);
            }
            rs.close();
            ps.close();
         }
	     catch (Exception e)  {
	    	 logger.log(Level.WARNING, "Error en buscarUsuario(usuario): " + e);
	     } 
		 return u;
	}
	
	// Metodo que verifica si existe un usuario en la BD por su nomUsuario
	public boolean existeUsuario(String nomUsuario) {
		boolean existe = false;
        String sql = "SELECT * FROM usuario WHERE nomUsuario = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)){
	        ps.setString(1, nomUsuario);
            ResultSet rs = ps.executeQuery();        
            if (rs.next()) existe = true;
            rs.close();
            ps.close();
         }
	     catch (Exception e)  {
	    	 logger.log(Level.WARNING, "Error en existeUsuario(Usuario): " + e);
	     } 
		 return existe;
	}
		
	//Metodo para introducir un nuevo usuario en la base de datos
	public boolean guardarUsuario(Usuario u) {
		if(existeUsuario(u.getNomUsuario()))
			return false;
	    boolean guardado = false;
	    String sql =
	      "INSERT INTO usuario(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasenya) "
	      + "VALUES(?, ?, ?, ?, ?, ?)";
	    
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, capitalize(u.getNombre()));
			ps.setString(2, capitalize(u.getApellidos()));
			ps.setString(3, u.getNomUsuario());
			ps.setInt(4, u.getNumTelefono());
			ps.setString(5, lower(u.getCorreoElectronico()));
			ps.setString(6, u.getContrasenya());
			ps.executeUpdate();
			guardado = true;
			
			ps.close();
		} catch (SQLException ex) {
			logger.log(Level.WARNING, "Error en metodo guardarCliente: " + ex);
		}
	
	    return guardado;
	}
	
	//Metodo que elimina un usuario pasado su nomUsuario
	public boolean borrarUsuario(String nomUsuario){
		String sql = "DELETE FROM usuario WHERE nomUsuario = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)){
	       	ps.setString(1, nomUsuario); 
	       	ps.executeUpdate();
	       	ps.close();
	       	return true;
	   	} catch (SQLException ex) {
	   		logger.log(Level.WARNING, "Error en metodo borrarUsuario: " + ex);
	       	return false;
	   	}
	}
	
	// Metodo que devuelve todos los usuarios
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "select nombre, apellidos, nomUsuario, numTelefono, correoElectronico, "
				+ "contrasenya from usuario;";
	    try (Statement st = conn.createStatement()){
            ResultSet rs = realizarQuery(sql, st);
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
            	String apellidos = capitalize(rs.getString("apellidos"));
            	String nomUsuario = rs.getString("nomUsuario");
            	int numTelefono = rs.getInt("numTelefono");
            	String correoElectronico = lower(rs.getString("correoElectronico"));
            	String contrasenya = rs.getString("contrasenya");
		        usuarios.add(new Usuario(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasenya));
	      }
	      rs.close();
	      st.close();
	    } catch (SQLException e) {
	    	logger.log(Level.WARNING, "Error en metodo listarUsuarios: " + e);
	    }
	    return usuarios;
	}
	
	////Consultas de Empleados
	
	// Metodo que busca un empleado en la BD por su nomUsuario
	public boolean buscaEmpleado(String nomUsuario) {
		boolean existe = false;
       String sql = "SELECT * FROM empleado WHERE nomUsuario = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)){
	        ps.setString(1, nomUsuario);
           ResultSet rs = ps.executeQuery();        
           if (rs.next()) existe = true;
           rs.close();
           ps.close();
        }
	     catch (Exception e)  {
	    	 logger.log(Level.WARNING, "Error en buscaEmpleado(Empleado): " + e);
	     } 
		 return existe;
	}
	
	//Metodo para introducir un nuevo empleado en la base de datos
	public boolean guardarEmpleado(Empleado e) {
		if(buscaEmpleado(e.getNomUsuario()))
			return false;
	    boolean guardado = false;
	    String sql =
	      "INSERT INTO empleado(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasenya, dni) "
	      + "VALUES(?, ?, ?, ?, ?, ?, ?)";
	    
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, capitalize(e.getNombre()));
			ps.setString(2, capitalize(e.getApellidos()));
			ps.setString(3, e.getNomUsuario());
			ps.setInt(4, e.getNumTelefono());
			ps.setString(5, lower(e.getCorreoElectronico()));
			ps.setString(6, e.getContrasenya());
			ps.setString(7, e.getDni());
			ps.executeUpdate();
			guardado = true;
			
			ps.close();
		} catch (SQLException ex) {
			logger.log(Level.WARNING, "Error en metodo guardarEmpleado: " + ex);
		}
	
	    return guardado;
	}
	
	//Metodo que elimina un empleado pasado su nomUsuario
	public boolean borrarEmpleado(String nomUsuario){
		String sql = "DELETE FROM empleado WHERE nomUsuario = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)){
	       	ps.setString(1, nomUsuario); 
	       	ps.executeUpdate();
	       	ps.close();
	       	return true;
	   	} catch (SQLException ex) {
	   		logger.log(Level.WARNING, "Error en metodo borrarEmpleado: " + ex);
	       	return false;
	   	}
	}
	
	// Metodo que devuelve todos los empleados
	public List<Empleado> listarEmpleados() {
		List<Empleado> empleado = new ArrayList<>();
		String sql = "select nombre, apellidos, nomUsuario, numTelefono, correoElectronico, "
				+ "contrasenya, dni from empleado;";
	    try (Statement st = conn.createStatement()){
           ResultSet rs = realizarQuery(sql, st);
           while (rs.next()) {
        	   String nombre = capitalize(rs.getString("nombre"));
        	   String apellidos = capitalize(rs.getString("apellidos"));
        	   String nomUsuario = rs.getString("nomUsuario");
        	   int numTelefono = rs.getInt("numTelefono");
        	   String correoElectronico = lower(rs.getString("correoElectronico"));
        	   String contrasenya = rs.getString("contrasenya");
        	   String dni = rs.getString("dni");
        	   empleado.add(new Empleado(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasenya, dni));
	      }
	      rs.close();
	      st.close();
	    } catch (SQLException e) {
	    	logger.log(Level.WARNING, "Error en metodo listarEmpleados: " + e);
	    }
	    return empleado;
	}
	
	////Consultas de Productos
	
	public boolean anyadirProducto(Producto p) {
		boolean anyadir = true;
		try {
			Statement st = conn.createStatement();
			String sql = String.format("SELECT * FROM Producto WHERE codigo = '%s'" , p.getId());
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				sql = String.format("INSERT INTO Producto (id, nombre, imagen, precio, cantidad, tipoProducto, categoria, estado, descuento) VALUES('%d', '%s', '%s', '%.2f', '%d', '%s', '%s', '%s', '%d')", p.getId(), p.getNombre(), p.getImagen(), p.getPrecio(), p.getCantidad(), p.getTipoProducto(), p.getCategoria(), p.getEstado(), p.getDescuento());
				st.executeUpdate(sql);
				
			}else {
				anyadir = false;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error en metodo anyadirProducto: " + e);
			anyadir = false;
		}
		return anyadir;
	}
	
	//Metodo que devuelve todos los productos
	public List<Producto> listarProductos() {
		List<Producto> productos = new ArrayList<>();
		String sql = "SELECT nombre, imagen, precio, cantidad, tipoProducto, categoria, estado, descuento "
				+ "FROM Producto";
		try (Statement st = conn.createStatement()){
			ResultSet rs = realizarQuery(sql, st);
			while (rs.next()) {
				String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("tipoProducto");
				String categoria = rs.getString("categoria");
				String estadoStr = rs.getString("estado");
				Estado estado = Estado.valueOf(estadoStr);
				int descuento = rs.getInt("descuento");
				
				Producto p = new Producto();
				p.setNombre(nombre);
				p.setImagen(imagen);
				p.setPrecio(precio);
				p.setCantidad(cantidad);

				TipoProducto enumTipoProducto = TipoProducto.valueOf(tipoProducto);
				p.setTipoProducto(enumTipoProducto);

				if (enumTipoProducto == TipoProducto.ALIMENTO) {
					TipoAlimento enumTipoA = TipoAlimento.valueOf(categoria);
					p.setCategoria(enumTipoA);
				}
	            
				if (enumTipoProducto == TipoProducto.HIGIENE_Y_BELLEZA) {
					TipoHigieneYBelleza enumTipoHYB = TipoHigieneYBelleza.valueOf(categoria);
					p.setCategoria(enumTipoHYB);
				}
	            
				if (enumTipoProducto == TipoProducto.LIMPIEZA) {
					TipoLimpieza enumTipoL = TipoLimpieza.valueOf(categoria);
					p.setCategoria(enumTipoL);
				}

				p.setEstado(estado);
				p.setDescuento(descuento);

				productos.add(p);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error en metodo listarProductos: " + e);
		}
		return productos;
	}
	
	// Metodo que devuelve todos los productos de una categoria
	public List<Producto> listarProductosPorCategoria(String categoria) {
		List<Producto> productos = new ArrayList<>();
		String sql = "SELECT nombre, imagen, precio, cantidad, tipoProducto, categoria, estado, descuento "
				+ "FROM Producto WHERE categoria = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, capitalize(categoria));
            ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("ALIMENTO");
				String cat = rs.getString("categoria");
				String estadoStr = rs.getString("estado");
				Estado estado = Estado.valueOf(estadoStr);
				int descuento = rs.getInt("descuento");
				
				Producto p = new Producto();
				if(p.getTipoProducto() == TipoProducto.ALIMENTO) {
					p.setNombre(nombre);
					p.setImagen(imagen);
					p.setPrecio(precio);
					p.setCantidad(cantidad);

					TipoProducto enumTipoProducto = TipoProducto.valueOf(tipoProducto);
					p.setTipoProducto(enumTipoProducto);

					if (enumTipoProducto == TipoProducto.ALIMENTO) {
						TipoAlimento enumTipoA = TipoAlimento.valueOf(cat);
						p.setCategoria(enumTipoA);
					}
		            
					if (enumTipoProducto == TipoProducto.HIGIENE_Y_BELLEZA) {
						TipoHigieneYBelleza enumTipoHYB = TipoHigieneYBelleza.valueOf(cat);
						p.setCategoria(enumTipoHYB);
					}
		            
					if (enumTipoProducto == TipoProducto.LIMPIEZA) {
						TipoLimpieza enumTipoL = TipoLimpieza.valueOf(cat);
						p.setCategoria(enumTipoL);
					}

					p.setEstado(estado);
					p.setDescuento(descuento);

					productos.add(p);
				}
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error en metodo listarProductosPorCategoria: " + e);
		}
		return productos;
	}
	
	// Metodo que busca un alimento en la BD
	public Producto buscarProducto(String nomb) {
		Producto p = new Producto();
        String sql = "SELECT * FROM producto WHERE nombre = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)){
	        ps.setString(1, nomb);
            ResultSet rs = ps.executeQuery();        
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("tipoProducto");
				String categoria = rs.getString("categoria");
				String estadoStr = rs.getString("estado");
				Estado estado = Estado.valueOf(estadoStr);
				int descuento = rs.getInt("descuento");
				if(p.getTipoProducto() == TipoProducto.ALIMENTO) {
					p.setNombre(nombre);
					p.setImagen(imagen);
					p.setPrecio(precio);
					p.setCantidad(cantidad);

					TipoProducto enumTipoProducto = TipoProducto.valueOf(tipoProducto);
					p.setTipoProducto(enumTipoProducto);

					if (enumTipoProducto == TipoProducto.ALIMENTO) {
						TipoAlimento enumTipoA = TipoAlimento.valueOf(categoria);
						p.setCategoria(enumTipoA);
					}
		            
					if (enumTipoProducto == TipoProducto.HIGIENE_Y_BELLEZA) {
						TipoHigieneYBelleza enumTipoHYB = TipoHigieneYBelleza.valueOf(categoria);
						p.setCategoria(enumTipoHYB);
					}
		            
					if (enumTipoProducto == TipoProducto.LIMPIEZA) {
						TipoLimpieza enumTipoL = TipoLimpieza.valueOf(categoria);
						p.setCategoria(enumTipoL);
					}

					p.setEstado(estado);
					p.setDescuento(descuento);
				}
            }
            rs.close();
            ps.close();
         }
	     catch (Exception e)  {
	    	 logger.log(Level.WARNING, "Error en buscarAlimento(nombre): " + e);
	     } 
		 return p;
	}
	
	//Metodo que al realizar una compra, reste la cantidad al stock de un producto
	public void realizarCompra(String nombre, int cantidad) {
		String sql = "UPDATE Producto SET cantidad = ? WHERE nombre = ?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)){
	      ps.setInt(1, cantidad);
	      ps.setString(2, nombre);
	      ps.executeUpdate();
	      ps.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	}
}
