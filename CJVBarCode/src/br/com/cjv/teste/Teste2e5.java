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

import br.com.cjv.CJVBarcode25i;
import br.com.cjv.ImagePanel;

public class Teste2e5 {
	public static void main(String[] args) {
		
		//CJVBarcode2e5i code25 = new CJVBarcode2e5i("35130560432713000156550010000211551342412294");
		CJVBarcode25i code25 = new CJVBarcode25i("23791554100000402743389259700256338300608570",true,true);
		code25.setEspessura(1);
		code25.setLegenda("23791554100000402743389259700256338300608570");
		//code25.setPrintLegenda(true);
		code25.setCentralizarLegenda(true);
		code25.setColorLegenda(Color.red);
		//
		int screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
		int fontSize = (int)Math.round(10.0 * screenRes / 72.0);
		Font font = new Font("Arial", Font.PLAIN, fontSize);
		code25.setFonteLegenda(font);
		//
		final BufferedImage image = code25.getImagem();
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
