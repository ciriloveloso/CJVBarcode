package br.com.cjv;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Classe abstrata para geração de código de Barras
 * @version 1.000
 * @author Cirilo José Veloso http://www.veloso.adm.br
 * @since Maio/2013
 */
public abstract class AbstractCJVBarcode {

	private String valor;
	private int altura = 80;
	private int espessura = 1;
	private int margensImagem[] = {1,1,1,1};//MARGEM_ESQUERDA,MARGEM_DIREITA,MARGEM_SUPERIOR,MARGEM_INFERIOR
	private Color colorBarCode = Color.BLACK;	
	//legenda
	private String legenda;
	private boolean printLegenda;
	private int alturaLegenda = 2;
	private boolean centralizarLegenda;
	private int margensLegenda[] = {10,10};//MARGEM_ESQUERDA,MARGEM_DIREITA
	private Font fonteLegenda = new Font("Arial", Font.PLAIN, 9);
	private Color colorLegenda = Color.BLACK;
		
	
	/**
	 * 
	 * @param valor conteúdo do código de barras
	 * @param altura altura do código de barras
	 * @param espessura espessura da barra
	 * @param margens margens do código de barras
	 * @param printLegenda define se imprime a legenda
	 * @param alturaLegenda altura da legenda
	 * @param centralizarLegenda define se centraliza a legenda
	 * @param margensLegenda margem esquerda e direita da legenda
	 * @param fonteLegenda fonte da legenda
	 * @param colorLegenda cor da legenda
	 */
	public AbstractCJVBarcode(String valor, int altura, int espessura, int[] margens,
			boolean printLegenda, int alturaLegenda, boolean centralizarLegenda, 
			int[] margensLegenda, Font fonteLegenda, Color colorLegenda){
		
		this.valor = valor;
		this.altura = altura;
		this.espessura = espessura;
		this.margensImagem = margens;
		this.printLegenda = printLegenda;
		this.alturaLegenda = alturaLegenda;
		this.centralizarLegenda = centralizarLegenda;
		this.margensLegenda = margensLegenda;
		this.fonteLegenda = fonteLegenda;
		this.colorLegenda = colorLegenda;
	}
	
	/**
	 * 
	 * @param valor conteúdo do código de barras
	 */
	public AbstractCJVBarcode(String valor){
		this.valor = valor;
	}
	
	/**
	 * 
	 * @param valor conteúdo do código de barras
	 * @param printLegenda define se imprime a legenda
	 * @param centralizarLegenda define se centraliza a legenda
	 */
	public AbstractCJVBarcode(String valor, boolean printLegenda, boolean centralizarLegenda){
		this.valor = valor;
		this.printLegenda = printLegenda;
		this.centralizarLegenda = centralizarLegenda;
		if(printLegenda){
			this.alturaLegenda = 20;
		}
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}


	public Color getColorBarCode() {
		return colorBarCode;
	}

	public void setColorBarCode(Color colorBarCode) {
		this.colorBarCode = colorBarCode;
	}

	public int getEspessura() {
		return espessura;
	}

	public void setEspessura(int espessura) {
		this.espessura = espessura;
	}

	public int[] getMargensImagem() {
		return margensImagem;
	}

	public void setMargensImagem(int[] margensImagem) {
		this.margensImagem = margensImagem;
	}

	public boolean isPrintLegenda() {
		return printLegenda;
	}

	public void setPrintLegenda(boolean printLegenda) {
		this.printLegenda = printLegenda;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public int getAlturaLegenda() {
		return alturaLegenda;
	}

	public void setAlturaLegenda(int alturaLegenda) {
		this.alturaLegenda = alturaLegenda;
	}

	public int[] getMargensLegenda() {
		return margensLegenda;
	}

	public void setMargensLegenda(int[] margensLegenda) {
		this.margensLegenda = margensLegenda;
	}

	public Font getFonteLegenda() {
		return fonteLegenda;
	}

	public void setFonteLegenda(Font fonteLegenda) {
		this.fonteLegenda = fonteLegenda;
	}

	public boolean isCentralizarLegenda() {
		return centralizarLegenda;
	}

	public void setCentralizarLegenda(boolean centralizarLegenda) {
		this.centralizarLegenda = centralizarLegenda;
	}

	public Color getColorLegenda() {
		return colorLegenda;
	}

	public void setColorLegenda(Color colorLegenda) {
		this.colorLegenda = colorLegenda;
	}

	
	/**
	 * @see java.awt.image.BufferedImage
	 * @param pb - string com letras P e B ( preto e branco )
	 * @return imagem do código de barras
	 */
	public BufferedImage desenhar(String pb){
		int w = getMargensImagem()[0] + (pb.length() * getEspessura()) + getMargensImagem()[1] + 2;
		/*
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		GraphicsConfiguration graphicsConfiguration = graphicsDevice.getDefaultConfiguration();
		BufferedImage image = graphicsConfiguration.createCompatibleImage(w,getAltura()+getAlturaLegenda()+getMargensImagem()[2],BufferedImage.TYPE_BYTE_INDEXED);
		*/
		BufferedImage image = new BufferedImage(w,getAltura()+getAlturaLegenda()+getMargensImagem()[2],BufferedImage.BITMASK);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setBackground(Color.WHITE);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.clearRect(0, 0, w, getAltura()+getAlturaLegenda()+getMargensImagem()[2]);		
		//espessura da linha
		BasicStroke stroke = new BasicStroke(getEspessura(),BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER);
		g.setStroke(stroke);
		int tam = getMargensImagem()[0]+1;
		//Conteudo do Codigo de Barras
		String caracter;
		//Line2D line;
		for(int i = 0; i <= pb.length()-1; i++) {
			caracter = pb.substring(i,i+1);
			if( caracter.equals("P")) {
				g.setColor(getColorBarCode());
			}
		    g.drawLine(tam, getMargensImagem()[2], tam, getAltura());
			//g.fillRect(tam, getMargensImagem()[2], getEspessura(), getAltura() - (getAlturaLegenda()/2) );
			g.setColor(Color.WHITE);
			tam = tam + (int)getEspessura();
		}
		//desenha a legenda
		if(isPrintLegenda()){
			if(getLegenda()==null){
				setLegenda(getValor());
			}
			g.setFont(getFonteLegenda());
			g.setColor(getColorLegenda());
			if(isCentralizarLegenda()){
				FontMetrics fm = g.getFontMetrics();
				int valorWidth = fm.stringWidth(getLegenda());
				int centro = (w - valorWidth) / 2;
				g.drawString(getLegenda(), centro , getAltura()+((getAlturaLegenda()+getMargensImagem()[2])/2));				
			}else{
				g.drawString(getLegenda(), getMargensLegenda()[0] , getAltura()+((getAlturaLegenda()+getMargensImagem()[2])/2));							
			}
		}
		image.flush();
		return image;
	}
	
	/*
	public static BufferedImage scale(BufferedImage src, int width, int height){
	    BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    Graphics2D g = dest.createGraphics();
	    AffineTransform at = AffineTransform.getScaleInstance(
	            (double)width/src.getWidth(),
	            (double)height/src.getHeight());
	    g.drawRenderedImage(src,at);        
	    return dest;
	}

	 public static BufferedImage crop(BufferedImage src, int width, int height){ 
	        int x = src.getWidth()/2 - width/2;
	        int y = src.getHeight()/2 - height/2;	        
//	        System.out.println("---" + src.getWidth() + " - " + src.getHeight() + " - " + x + " - " + y);	        
	        BufferedImage clipping = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//src.getType());  
	        Graphics2D area = (Graphics2D) clipping.getGraphics().create();  
	        area.drawImage(src, 0, 0, clipping.getWidth(), clipping.getHeight(), x, y, x + clipping.getWidth(),  
	            y + clipping.getHeight(), null);  
	        area.dispose(); 	        
	        return clipping;
	 }

	 public BufferedImage resize(BufferedImage source, int width, int height) {
	        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	        Graphics g = newImage.getGraphics();
	        g.drawImage(source.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
	        g.dispose();
	        return newImage;
	 }
	 */
}
