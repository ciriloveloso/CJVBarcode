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

import br.com.cjv.CJVBarcodeEAN8;
import br.com.cjv.ImagePanel;

public class TesteEan8 {
	public static void main(String[] args) {
		CJVBarcodeEAN8 code8 = new CJVBarcodeEAN8("789500036111");
		code8.setEspessura(2);
		// codeEan13.setLegenda("3513 0560 4327 1300 0156 5500 1000 0211 5513 4241 2294");
		code8.setFonteLegenda(new Font("TimesRoman", Font.PLAIN, 10));
		code8.setPrintLegenda(true);
		code8.setCentralizarLegenda(true);
		code8.setColorLegenda(Color.red);
		final BufferedImage image = code8.getImagem();
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
