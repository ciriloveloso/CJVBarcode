package br.com.cjv.teste;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import br.com.cjv.CJVBarcode39;
import br.com.cjv.ImagePanel;

public class Teste39 {
	public static void main(String[] args) {		
		CJVBarcode39 code39 = new CJVBarcode39("WWW.CJV.COM.BR");
		code39.setEspessura(1);
		code39.setLegenda("WWW.CJV.COM.BR");
		code39.setPrintLegenda(true);
		code39.setCentralizarLegenda(true);
		code39.setColorLegenda(Color.red);
		//
		int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
		int fontSize = (int)Math.round(10.0 * screenRes / 72.0);
		Font font = new Font("Arial", Font.PLAIN, fontSize);
		code39.setFonteLegenda(font);
		//
		final BufferedImage image = code39.getImagem();
		ImagePanel panel = new ImagePanel(image);
			panel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					try {
						String path = new File(".").getCanonicalPath();					
						ImageIO.write(image, "png", new File(path+"\\CJVBarcode.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}			
			});
	    JFrame frame = new JFrame();
	    frame.getContentPane().add(panel);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setVisible(true);		
	}

}
