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
import java.util.logging.Logger;

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
	private static Logger logger = Logger.getLogger(GestorBD.class.getName());
	
	public GestorBD() {
		connect();
	}
	
	public void connect(){
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			
			Statement statement = conn.createStatement();
			String sent = "CREATE TABLE IF NOT EXISTS Producto (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre varchar(100), imagen varchar(100), precio double, cantidad int, tipoProducto varchar(100), tipoProductoTipo varchar(100), estado varchar(100), descuento int);";
			statement.executeUpdate( sent );
			sent = "CREATE TABLE IF NOT EXISTS Usuario (nombre varchar(100), apellidos varchar(100), nomUsuario varchar(100), numTelefono int, correoElectronico varchar(100), contrasenya varchar(100));";
			statement.executeUpdate( sent );
			sent = "CREATE TABLE IF NOT EXISTS Empleado (nombre varchar(100), apellidos varchar(100), nomUsuario varchar(100), numTelefono int, correoElectronico varchar(100), contrasenya varchar(100), dni varchar(9));";
			statement.executeUpdate( sent );
			
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, "No se ha podido cargar el driver de la base de datos");
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error conectando a la BD", e);
		}
	}
	
	public void disconnect(){
		try {
			conn.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error cerrando la conexión con la BD", e);
		}
	}
	
	public ResultSet realizarQuery(String sql, Statement st) {
		ResultSet rs = null;
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "No se ha podido realizar la consulta " + sql);
		}
		return rs;
	}
	
	private static final String capitalize(String str) {
		if (str == null || str.length() == 0) return str;

	    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
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
        	 logger.log(Level.SEVERE, "Error en verificarCredenciales(Usuario, password): " + e);
         } 
		 return u;
	}
	
	// Metodo que busca un usuario en la BD por su nomUsuario
	public boolean buscaUsuario(String nomUsuario) {
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
	    	 logger.log(Level.SEVERE, "Error en buscaUsuario(Usuario): " + e);
	     } 
		 return existe;
	}
	
	//Metodo para introducir un nuevo usuario en la base de datos
	public boolean guardarUsuario(Usuario u) {
		if(buscaUsuario(u.getNomUsuario()))
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
			ps.setString(5, u.getCorreoElectronico());
			ps.setString(6, u.getContrasenya());
			ps.executeUpdate();
			guardado = true;
			
			ps.close();
		} catch (SQLException ex) {
			logger.log(Level.SEVERE, "Error en metodo guardarCliente: " + ex);
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
	   		logger.log(Level.SEVERE, "Error en metodo borrarUsuario: " + ex);
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
            	String correoElectronico = rs.getString("correoElectronico");
            	String contrasenya = rs.getString("contrasenya");
		        usuarios.add(new Usuario(nombre, apellidos, nomUsuario, numTelefono, correoElectronico, contrasenya));
	      }
	      rs.close();
	      st.close();
	    } catch (SQLException e) {
	    	logger.log(Level.SEVERE, "Error en metodo listarUsuarios: " + e);
	    }
	    return usuarios;
	}
	
	////Consultas de Productos
	
	public boolean anyadirProducto(Producto p) {
		boolean anyadir = true;
		try {
			Statement st = conn.createStatement();
			String sql = String.format("SELECT * FROM Producto WHERE codigo = '%s'" , p.getId());
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				sql = String.format("INSERT INTO Producto (id, nombre, imagen, precio, cantidad, tipoProducto, tipoProductoTipo, estado, descuento) VALUES('%d', '%s', '%s', '%.2f', '%d', '%s', '%s', '%s', '%d')", p.getId(), p.getNombre(), p.getImagen(), p.getPrecio(), p.getCantidad(), p.getTipoProducto(), p.getTipoProductoTipo(), p.getEstado(), p.getDescuento());
				st.executeUpdate(sql);
				
			}else {
				anyadir = false;
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en metodo anyadirProducto: " + e);
			anyadir = false;
		}
		return anyadir;
	}
	
	//Metodo que devuelve todos los productos
	public List<Producto> listarProductos() {
		List<Producto> productos = new ArrayList<>();
		String sql = "SELECT nombre, imagen, precio, cantidad, tipoProducto, tipoProductoTipo, estado, descuento "
				+ "FROM Productos";
		try (Statement st = conn.createStatement()){
			ResultSet rs = realizarQuery(sql, st);
			while (rs.next()) {
				String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("tipoProducto");
				String tipoProductoTipo = rs.getString("tipoProductoTipo");
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
					TipoAlimento enumTipoA = TipoAlimento.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoA);
				}
	            
				if (enumTipoProducto == TipoProducto.HIGIENE_Y_BELLEZA) {
					TipoHigieneYBelleza enumTipoHYB = TipoHigieneYBelleza.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoHYB);
				}
	            
				if (enumTipoProducto == TipoProducto.LIMPIEZA) {
					TipoLimpieza enumTipoL = TipoLimpieza.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoL);
				}

				p.setEstado(estado);
				p.setDescuento(descuento);

				productos.add(p);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en metodo listarProductos: " + e);
		}
		return productos;
	}
	
	// Metodo que devuelve todos los productos de Alimento
	public List<Producto> listarAlimentos() {
		List<Producto> productos = new ArrayList<>();
		String sql = "SELECT nombre, imagen, precio, cantidad, tipoProducto, tipoProductoTipo, estado, descuento "
				+ "FROM Productos";
		try (Statement st = conn.createStatement()){
			ResultSet rs = realizarQuery(sql, st);
			while (rs.next()) {
				String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("ALIMENTO");
				String tipoProductoTipo = rs.getString("tipoProductoTipo");
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

					TipoAlimento enumTipoA = TipoAlimento.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoA);

					p.setEstado(estado);
					p.setDescuento(descuento);

					productos.add(p);
				}
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en metodo listarAlimentos: " + e);
		}
		return productos;
	}
	
//	//Metodo que devuelve todos los productos de Higiene y Belleza
	public List<Producto> listarHigieneYBelleza() {
		List<Producto> productos = new ArrayList<>();
		String sql = "SELECT nombre, imagen, precio, cantidad, tipoProducto, tipoProductoTipo, estado, descuento "
				+ "FROM Productos";
		try (Statement st = conn.createStatement()){
			ResultSet rs = realizarQuery(sql, st);
			while (rs.next()) {
				String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("tipoProducto");
				String tipoProductoTipo = rs.getString("tipoProductoTipo");
				String estadoStr = rs.getString("estado");
				Estado estado = Estado.valueOf(estadoStr);
				int descuento = rs.getInt("descuento");
				
				Producto p = new Producto();
				if(p.getTipoProducto() == TipoProducto.HIGIENE_Y_BELLEZA) {
					p.setNombre(nombre);
					p.setImagen(imagen);
					p.setPrecio(precio);
					p.setCantidad(cantidad);

					TipoProducto enumTipoProducto = TipoProducto.valueOf(tipoProducto);
					p.setTipoProducto(enumTipoProducto);

					TipoHigieneYBelleza enumTipoH = TipoHigieneYBelleza.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoH);

					p.setEstado(estado);
					p.setDescuento(descuento);

					productos.add(p);
				}
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en metodo listarHigieneYBelleza: " + e);
		}
		return productos;
	}
	
//	
//	//Metodo que devuelve todos los productos de Limpieza
	public List<Producto> listarLimpieza() {
		List<Producto> productos = new ArrayList<>();
		String sql = "SELECT nombre, imagen, precio, cantidad, tipoProducto, tipoProductoTipo, estado, descuento "
				+ "FROM Productos";
		try (Statement st = conn.createStatement()){
			ResultSet rs = realizarQuery(sql, st);
			while (rs.next()) {
				String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("tipoProducto");
				String tipoProductoTipo = rs.getString("tipoProductoTipo");
				String estadoStr = rs.getString("estado");
				Estado estado = Estado.valueOf(estadoStr);
				int descuento = rs.getInt("descuento");
				
				Producto p = new Producto();
				if(p.getTipoProducto() == TipoProducto.LIMPIEZA) {
					p.setNombre(nombre);
					p.setImagen(imagen);
					p.setPrecio(precio);
					p.setCantidad(cantidad);

					TipoProducto enumTipoProducto = TipoProducto.valueOf(tipoProducto);
					p.setTipoProducto(enumTipoProducto);

					TipoLimpieza enumTipoL = TipoLimpieza.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoL);

					p.setEstado(estado);
					p.setDescuento(descuento);

					productos.add(p);
				}
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "Error en metodo listarLimpieza: " + e);
		}
		return productos;
	}
	
	// Metodo que busca un alimento en la BD
	public Producto buscarAlimento(String nomb) {
		Producto p = new Producto();
        String sql = "SELECT * FROM productos WHERE nombre = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)){
	        ps.setString(1, nomb);
            ResultSet rs = ps.executeQuery();        
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("tipoProducto");
				String tipoProductoTipo = rs.getString("tipoProductoTipo");
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

					TipoAlimento enumTipoA = TipoAlimento.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoA);

					p.setEstado(estado);
					p.setDescuento(descuento);
				}
            }
            rs.close();
            ps.close();
         }
	     catch (Exception e)  {
	    	 logger.log(Level.SEVERE, "Error en buscarAlimento(nombre): " + e);
	     } 
		 return p;
	}
	
//	// Metodo que busca un producto de Higiene y Belleza en la BD
	public Producto buscarHigieneYBelleza(String nomb) {
		Producto p = new Producto();
        String sql = "SELECT * FROM productos WHERE nombre = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)){
	        ps.setString(1, nomb);
            ResultSet rs = ps.executeQuery();        
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("tipoProducto");
				String tipoProductoTipo = rs.getString("tipoProductoTipo");
				String estadoStr = rs.getString("estado");
				Estado estado = Estado.valueOf(estadoStr);
				int descuento = rs.getInt("descuento");
				if(p.getTipoProducto() == TipoProducto.HIGIENE_Y_BELLEZA) {
					p.setNombre(nombre);
					p.setImagen(imagen);
					p.setPrecio(precio);
					p.setCantidad(cantidad);

					TipoProducto enumTipoProducto = TipoProducto.valueOf(tipoProducto);
					p.setTipoProducto(enumTipoProducto);

					TipoHigieneYBelleza enumTipoH = TipoHigieneYBelleza.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoH);

					p.setEstado(estado);
					p.setDescuento(descuento);
				}
            }
            rs.close();
            ps.close();
         }
	     catch (Exception e)  {
	    	 logger.log(Level.SEVERE, "Error en buscarHigieneYBelleza(nombre): " + e);
	     } 
		 return p;
	}
	
//	// Metodo que busca un producto de Limpieza en la BD
	public Producto buscarLimpieza(String nomb) {
		Producto p = new Producto();
        String sql = "SELECT * FROM productos WHERE nombre = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)){
	        ps.setString(1, nomb);
            ResultSet rs = ps.executeQuery();        
            while (rs.next()) {
            	String nombre = capitalize(rs.getString("nombre"));
				String imagen = rs.getString("imagen");
				Double precio = rs.getDouble("precio");
				int cantidad = rs.getInt("cantidad");
				String tipoProducto = rs.getString("tipoProducto");
				String tipoProductoTipo = rs.getString("tipoProductoTipo");
				String estadoStr = rs.getString("estado");
				Estado estado = Estado.valueOf(estadoStr);
				int descuento = rs.getInt("descuento");
				if(p.getTipoProducto() == TipoProducto.LIMPIEZA) {
					p.setNombre(nombre);
					p.setImagen(imagen);
					p.setPrecio(precio);
					p.setCantidad(cantidad);

					TipoProducto enumTipoProducto = TipoProducto.valueOf(tipoProducto);
					p.setTipoProducto(enumTipoProducto);

					TipoLimpieza enumTipoL = TipoLimpieza.valueOf(tipoProductoTipo);
					p.setTipoProductoTipo(enumTipoL);

					p.setEstado(estado);
					p.setDescuento(descuento);
				}
            }
            rs.close();
            ps.close();
         }
	     catch (Exception e)  {
	    	 logger.log(Level.SEVERE, "Error en buscarLimpieza(nombre): " + e);
	     } 
		 return p;
	}
	
	//Metodo que al realizar una compra, reste la cantidad al stock de un producto
	public void realizarCompra(String nombre, String producto, int cantidad) {
		String sql = "UPDATE ? SET cantidad = ? WHERE nombre = ?";
	    try (PreparedStatement ps = conn.prepareStatement(sql)){
	      ps.setString(1, nombre);
	      ps.setString(2, producto);
	      ps.setInt(3, cantidad);
	      ps.executeUpdate();
	      ps.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	}
}
