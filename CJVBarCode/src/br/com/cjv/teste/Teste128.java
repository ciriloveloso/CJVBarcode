package br.com.cjv.teste;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import br.com.cjv.CJVBarcode128;
import br.com.cjv.ImagePanel;
import br.com.cjv.Tipo128;

public class Teste128 {
	public static void main(String[] args) {
        Font fonte = new Font("Arial",Font.PLAIN,10);
        int[] margens = {10,10,10,10};
        int[] legendaMargens = {10,10};
		//CJVBarcode128 code128 = new CJVBarcode128("35130560432713000156550010000211551342412294", Tipo128.Tipo128C);
		//CJVBarcode128 code128 = new CJVBarcode128("7896548198957", Tipo128.Tipo128C);
		CJVBarcode128 code128 = new CJVBarcode128("789654819895",80,1,margens,true,10,true,legendaMargens,fonte,Color.RED, Tipo128.Tipo128C);
		//CJVBarcode128 code128 = new CJVBarcode128("", Tipo128.Tipo128B);
		//code128.setEspessura(1);
		//code128.setLegenda("3513 0560 4327 1300 0156 5500 1000 0211 5513 4241 2294");
		//code128.setFonteLegenda(new Font("TimesRoman",Font.PLAIN,11));
		//code128.setPrintLegenda(true);
		//code128.setCentralizarLegenda(true);
		//code128.setColorLegenda(Color.red);
		final BufferedImage image = code128.getImagem();
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
