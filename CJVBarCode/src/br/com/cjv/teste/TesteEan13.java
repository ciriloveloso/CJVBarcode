package br.com.cjv.teste;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import br.com.cjv.CJVBarcodeEAN13;
import br.com.cjv.ImagePanel;

public class TesteEan13 {
	public static void main(String[] args) {
		//CJVBarcodeEAN13 code13 = new CJVBarcodeEAN13("789500036111");
	
		CJVBarcodeEAN13 code13 = new br.com.cjv.CJVBarcodeEAN13("789500036111",80,2,new int[]{10,10,10,10},true,10,false,new int[]{10,10},new java.awt.Font("Arial", Font.PLAIN, 11), java.awt.Color.BLUE );
		//code13.setEspessura(1);
		//codeEan13.setLegenda("3513 0560 4327 1300 0156 5500 1000 0211 5513 4241 2294");
		//code13.setFonteLegenda(new Font("TimesRoman", Font.PLAIN, 10));
		//code13.setPrintLegenda(true);
		//code13.setCentralizarLegenda(true);
		//code13.setColorLegenda(Color.red);
		final BufferedImage image = code13.getImagem();
		ImagePanel panel = new ImagePanel(image);
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					String path = new File(".").getCanonicalPath();
					ImageIO.write(image, "png", new File(path
							+ "\\CJVBarcode.png"));
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
