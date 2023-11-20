package io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import domain.GestorMarket;
import domain.Producto;

public class GestorXML {
	
	private String nomFichXML;
	private Document doc;
	private GestorMarket gestor;
	
	//Constructor
	public GestorXML(String nomFich, GestorMarket gestor) {
		this.nomFichXML = nomFich;
			
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(nomFich);
		} catch (JDOMException e) {
			gestor.getLogger().log(Level.SEVERE, "Documento XML no exist");
		} catch (IOException e) {
			gestor.getLogger().log(Level.SEVERE, "Documento XML no exist");
		}
	}
		
	//Metodo grabar
	private void grabar() {
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try {
			outputter.output(doc, new FileWriter(nomFichXML));
		} catch (IOException e) {
			gestor.getLogger().log(Level.SEVERE, "Fichero " + nomFichXML + " no exist");;
		}
	}
	
	//Metodo dameProductos
	public List<Producto> dameProductos(String usuario) {
		List<Producto> lstProductos = new  ArrayList<>();
		Element eCarritos = doc.getRootElement();
		for(Element eCarrito: eCarritos.getChildren()) {
			if(eCarrito.getAttributeValue("usuario").equals(usuario)) {
				List<Element> lstElemntosProductos = eCarrito.getChildren();
				for (int i = 0; i<lstElemntosProductos.size(); i++) {
					Element eProducto = lstElemntosProductos.get(i);
					Producto p;
					int id = Integer.parseInt(eProducto.getChildText("id"));
					String nombre = eProducto.getAttributeValue("nombre");
					String imagen = eProducto.getChildText("imagen");
					double precio = Double.parseDouble(eProducto.getChildText("precio"));
					int cantidad = Integer.parseInt(eProducto.getChildText("cantidad"));
					
					p = new Producto(id, nombre, imagen, precio, cantidad);
					
					lstProductos.add(p);
				}
			}
			
		}
		return lstProductos;
	}

	//Metodo anadirProducto
	public void anadirProducto(Producto p, int cantidad, String usuario) {
		Element eCarrito = buscarCarritoUsuario(usuario);
		if(eCarrito == null) {
			eCarrito = new Element("carrito");
			eCarrito.setAttribute("usuario", usuario);
		}
		Element eProducto = new Element("producto");
		eProducto.setAttribute("nombre", p.getNombre());
		Element eImagen = new Element("imagen");
		Element ePrecio = new Element("precio");
		Element eCantidad = new Element("cantidad");
		eProducto.addContent(eImagen);
		eProducto.addContent(ePrecio);
		eProducto.addContent(eCantidad);
		
		eCarrito.addContent(eProducto);
		
		Element eCarritos = doc.getRootElement();
		eCarritos.addContent(eCarrito);
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
				//Se debe de eliminar el elemento
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
	
	//Metodo vaciarCarrito
	public void vaciarCarrito(String usuario) {
		Element eCarrito = buscarCarritoUsuario(usuario);
		if(eCarrito == null)
			return;
		//Se debe de eliminar la rama entera
		
	}
}
