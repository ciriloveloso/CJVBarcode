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

import br.com.cjv.CJVBarcodeUPCA;
import br.com.cjv.ImagePanel;

public class TesteUPCA {
	public static void main(String[] args) {
		CJVBarcodeUPCA codeUPC = new CJVBarcodeUPCA("789500036111");
		codeUPC.setEspessura(2);
		// codeEan13.setLegenda("3513 0560 4327 1300 0156 5500 1000 0211 5513 4241 2294");
		codeUPC.setFonteLegenda(new Font("TimesRoman", Font.PLAIN, 20));
		codeUPC.setPrintLegenda(true);
		codeUPC.setCentralizarLegenda(true);
		codeUPC.setColorLegenda(Color.red);
		final BufferedImage image = codeUPC.getImagem();
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
