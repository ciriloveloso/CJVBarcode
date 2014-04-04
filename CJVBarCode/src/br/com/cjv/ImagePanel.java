package br.com.cjv;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Classe extendida de JPanel para inclusão de imagens
 * @see javax.swing.JPanel
 * @author Cirilo José Veloso
 * 
 */
public class ImagePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;

	  public ImagePanel(BufferedImage img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }	

}
