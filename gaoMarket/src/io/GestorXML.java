package io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import domain.Alimento;
import domain.GestorMarket;
import domain.HigieneYBelleza;
import domain.Limpieza;
import domain.Producto;
import domain.TipoAlimento;

public class GestorXML {
	
	public static final String NOM_FICH_XML = "src/io/carrito.xml";
	private Document doc;
	private GestorMarket gestor;
	
	//Constructor
	public GestorXML(GestorMarket gestor) {
		this.gestor = gestor;
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(NOM_FICH_XML);
		} catch (JDOMException e) {
			gestor.getLogger().log(Level.SEVERE, "Documento XML corrupto");
		} catch (IOException e) {
			gestor.getLogger().log(Level.SEVERE, "Documento XML no exist");
		}
	}
		
	//Metodo grabar
	private void grabar() {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			outputter.output(doc, new FileWriter(NOM_FICH_XML));
		} catch (IOException e) {
			gestor.getLogger().log(Level.SEVERE, "Fichero " + NOM_FICH_XML + " no exist");;
		}
	}
	
	//Metodo dameProductos
	public Map[] dameProductos(String usuario) {
		Map[] listaProductos = new HashMap[2];
		Map<Alimento, Integer> mapaAlimento = new  HashMap<>();
		Map<Limpieza, Integer> mapaLimpieza = new  HashMap<>();
		Map<HigieneYBelleza, Integer> mapaHigieneYBelleza = new  HashMap<>();
		
		Element eCarritos = doc.getRootElement();
		for(Element eCarrito: eCarritos.getChildren()) {
			if(eCarrito.getAttributeValue("usuario").equals(usuario)) {
				List<Element> lstElemntosProductos = eCarrito.getChildren();
				for (int i = 0; i<lstElemntosProductos.size(); i++) {
					Element eProducto = lstElemntosProductos.get(i);
					String nombre = eProducto.getAttributeValue("nombre");
					String tipo = eProducto.getChildText("tipo");
					int cantidad = Integer.parseInt(eProducto.getChildText("cantidad"));
					switch (tipo) { 
	                case "ALIMENTO":
	                	Alimento a = gestor.getGestorBD().buscarAlimento(nombre);
	                	mapaAlimento.put(a, cantidad);
	                	break;
	                case "LIMPIEZA":
	                	Limpieza l = gestor.getGestorBD().buscarLimpieza(nombre);
	                	mapaLimpieza.put(l, cantidad);
	                	break;
	                case "HIGIENE_Y_BELLEZA":
	                	HigieneYBelleza hb = gestor.getGestorBD().buscarHigieneYBelleza(nombre);
	                	mapaHigieneYBelleza.put(hb, cantidad);
	                	break;
	            	}
				}
			}
			
		}
		
		listaProductos[0] = mapaAlimento;
		listaProductos[1] = mapaLimpieza;
		listaProductos[2] = mapaHigieneYBelleza;
		return listaProductos;
	}

	//Metodo anadirProducto
	public void anadirProducto(Producto p, int cantidad, String usuario) {
		Element eCarrito = buscarCarritoUsuario(usuario);
		Element eProducto = buscarProductoCarritoUsuario(usuario, p.getNombre());
		if(eProducto != null) {
			actualizarCantidadProducto(eProducto, cantidad);
			grabar();
			return;
		}
		eProducto = new Element("producto");
		eProducto.setAttribute("nombre", p.getNombre());
		Element eTipo = new Element("tipo");
		eTipo.addContent(p.getTipoProducto().toString());
		Element eCantidad = new Element("cantidad");
		eCantidad.addContent("" + cantidad);
		eProducto.addContent(eTipo);
		eProducto.addContent(eCantidad);
		
		if(eCarrito == null) {
			eCarrito = new Element("carrito");
			eCarrito.setAttribute("usuario", usuario);
			Element eCarritos = doc.getRootElement();
			eCarritos.addContent(eCarrito);
		}
		eCarrito.addContent(eProducto);
		
		grabar();
	}
	
	//Metodo eliminarProducto
	public void eliminarProducto(String usuario, String nomProducto) {
		Element eCarrito = buscarCarritoUsuario(usuario);
		if(eCarrito == null)
			return;
		
		List<Element> lstElemntosProductos = eCarrito.getChildren();
		for (int i = 0; i<lstElemntosProductos.size(); i++) {
			Element eProducto = lstElemntosProductos.get(i);
			if(eProducto.getAttributeValue("nombre").equals(nomProducto)) {
				eCarrito.removeContent(eProducto);
				grabar();
			}
		}
	}
	
	//Metodo buscarCarritoUsuario
	private Element buscarCarritoUsuario(String usuario) {
		Element eCarritos = doc.getRootElement();
		for(Element eCarrito: eCarritos.getChildren()) {
			if(eCarrito.getAttributeValue("usuario").equals(usuario))
				return eCarrito;
		}
		return null;
	}
	
	//Metodo buscarProductoCarritoUsuario
	private Element buscarProductoCarritoUsuario(String usuario, String producto) {
		Element eCarritos = doc.getRootElement();
		for(Element eCarrito: eCarritos.getChildren()) {
			if(eCarrito.getAttributeValue("usuario").equals(usuario)) {
				Element eProducto;
				List<Element> lstElemntosProductos = eCarrito.getChildren();
				for (int i = 0; i<lstElemntosProductos.size(); i++) {
					eProducto = lstElemntosProductos.get(i);
					if(eProducto.getAttributeValue("nombre").equals(producto))
						return eProducto;
				}
			}
		}
		return null;
	}
	
	//Metodo actualizarCantidadProducto
	private Element actualizarCantidadProducto(Element e, int cantidad) {
		Element eCantidad = e.getChild("cantidad");
		int cant = cantidad + Integer.parseInt(eCantidad.getText());
		eCantidad.setText(cant+"");
		return e;
	}
	
	//Metodo vaciarCarrito
	public void vaciarCarrito(String usuario) {
		Element eCarrito = buscarCarritoUsuario(usuario);
		if(eCarrito == null)
			return;
		Element eCarritos = eCarrito.getParentElement();
		eCarritos.removeContent(eCarrito);
		grabar();
	}
}
