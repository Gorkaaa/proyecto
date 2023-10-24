package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import dinosaurio.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class VentanaImagen extends JFrame implements MouseListener, MouseMotionListener, MouseWheelListener {
	protected GestorDinos gestor;
	
    protected Point imagen1Pos;
    protected Point imagen2Pos;
    
    protected ImageIcon imagenIcon1;
    protected ImageIcon imagenIcon2;
    
    protected Image imagenes1;
    protected Image imagenes2;
    
    protected JLabel label1;
    protected JLabel label2;
    
    
    

    public VentanaImagen(GestorDinos gestor) {
    	this.gestor = gestor;
    	Container cp = this.getContentPane();
        
    	cp.setLayout(new BorderLayout());
        
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);

    	if (r == g && g == b) {
    		r = 110;
    		g = 110;
    		b = 110;
    	}
    	
		Color color = new Color(r, g, b);
        
		int width = 400;
		int height = 300;
		
		
		int aleatorio1 = (int) ((Math.random() * 18) + 1);
		String image1 = "cabezaDino/imagen" + aleatorio1 + ".png";
        
		int aleatorio2 = (int) ((Math.random() * 17) + 1);
		String image2 = "cuerpoDino/imagen" + aleatorio2 + ".png";

		
		imagenIcon1 = new ImageIcon(image1);
		imagenes1 = imagenIcon1.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
		ImageIcon imagen1 = new ImageIcon(imagenes1);
        
		imagenIcon2 = new ImageIcon(image2);
		imagenes2 = imagenIcon2.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
		ImageIcon imagen2 = new ImageIcon(imagenes2);
        
		label1 = new JLabel(imagen1);
		label2 = new JLabel(imagen2);

		imagen1Pos = new Point(500, 100);
		imagen2Pos = new Point(100, 100);

		label1.setBounds(imagen1Pos.x, imagen1Pos.y, imagen1.getIconWidth(), imagen1.getIconHeight());
		label2.setBounds(imagen2Pos.x, imagen2Pos.y, imagen2.getIconWidth(), imagen2.getIconHeight());

		label1.addMouseListener(this);
		label1.addMouseMotionListener(this);
		label1.addMouseWheelListener(this);

		label2.addMouseListener(this);
		label2.addMouseMotionListener(this);
		label2.addMouseWheelListener(this);
		
		
		this.setLayout(null);
		cp.add(label1);
		cp.add(label2);
    
		this.getContentPane().setBackground(color);
        
		this.setTitle("Creación Dino");
        this.setSize(1100, 600);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setVisible(false);

    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
    	JLabel source = (JLabel) e.getSource();

    	if (source == label1) {
    		imagen1Pos.x = e.getXOnScreen() - label1.getWidth() / 2;
    		imagen1Pos.y = e.getYOnScreen() - label1.getHeight() / 2;
    		label1.setLocation(imagen1Pos);
            
    	} else if (source == label2) {
    		imagen2Pos.x = e.getXOnScreen() - label2.getWidth() / 2;
    		imagen2Pos.y = e.getYOnScreen() - label2.getHeight() / 2;
    		label2.setLocation(imagen2Pos);
    	}
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    	JLabel source = (JLabel) e.getSource();
    	
    	if (source == label1) {
    	int scrollAmount = e.getWheelRotation();
    	int width = label1.getWidth();
    	int height = label1.getHeight();
    	

    	if (scrollAmount < 0) {
    		width += 10;
    		height += 10;
    	} else {
    		width -= 10;
    		height -= 10;
    	}

    	// Limitar tamanyo minimo de la cabeza
    	if (width < 50) width = 50;
    	if (height < 50) height = 50;

    	label1.setSize(width, height);
    	label1.setIcon(new ImageIcon(imagenIcon1.getImage().getScaledInstance(width, height, Image.SCALE_FAST)));
    	
    	} else {
    		int scrollAmount = e.getWheelRotation();
    		int width = label2.getWidth();
    		int height = label2.getHeight();

    		if (scrollAmount < 0) {
    			width += 10;
    			height += 10;
    		} else {
    			width -= 10;
    			height -= 10;
    		}
    		
    		// Limitar el tamanyo minimo del cuerpo
    		if (width < 50) width = 50;
    		if (height < 50) height = 50;

    		label2.setSize(width, height);
    		label2.setIcon(new ImageIcon(imagenIcon2.getImage().getScaledInstance(width, height, Image.SCALE_FAST)));
    	}	
    }
}