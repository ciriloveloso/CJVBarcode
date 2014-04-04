package br.com.cjv;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Gerador de código de barras padrão EAN8
 * @author Cirilo José Veloso
 *
 */
public class CJVBarcodeEAN8 extends AbstractCJVBarcode{
	private String dv;
	

	public CJVBarcodeEAN8(String valor, boolean printLegenda,
			boolean centralizarLegenda) {
		super(valor, printLegenda, centralizarLegenda);
	}

	public CJVBarcodeEAN8(String valor, int altura, int espessura,
			int[] margens, boolean printLegenda, int alturaLegenda,
			boolean centralizarLegenda, int[] margensLegenda,
			Font fonteLegenda, Color colorLegenda) {
		super(valor, altura, espessura, margens, printLegenda, alturaLegenda,
				centralizarLegenda, margensLegenda, fonteLegenda, colorLegenda);
	}

	public CJVBarcodeEAN8(String valor) {
		super(valor);
	}

	private final  String[][] padrao = new String[][]{
			{"0","0001101","0100111","1110010"},
			{"1","0011001","0110011","1100110"},
			{"2","0010011","0011011","1101100"},
			{"3","0111101","0100001","1000010"},
			{"4","0100011","0011101","1011100"},
			{"5","0110001","0111001","1001110"},
			{"6","0101111","0000101","1010000"},
			{"7","0111011","0010001","1000100"},
			{"8","0110111","0001001","1001000"},
			{"9","0001011","0010111","1110100"}			
	};

	
	private String digitoEan13(String conteudo){
		int soma = 0;
		int fator = 3;
		for(int i=conteudo.length()-1; i >= 0;i--){
			int sub = Integer.parseInt(conteudo.substring(i, i+1)) * fator;
			soma = soma + sub;
			fator = fator - 2;
			if(fator < 0){
				fator = 3;
			}			
		}
		int resto = soma % 10;
		int dv = 10 - resto;
		if(dv >= 10){
			return "0";
		}else{
			return Integer.toString(dv);
		}
	}

	private String criarPretoBranco(){
		StringBuffer retorno = new StringBuffer();	
		String left  = "101";
		String right = "101";
		String center= "01010";
		String num = Utils.somenteNumeros(getValor());
		if(num.length() < 7){
			num = Utils.lpad(num, "0", 7);
		}
		if(num.length() > 7){
			num = num.substring(0, 7);
		}
		String dv = digitoEan13(num);
		setValor(num);
		setDv(dv);
		num = num + dv;
		//lado esquerdo do código de barras
		retorno.append(left);
		int i = 0;
		int iNum;
		while ( i < 4 ){
			iNum = Integer.parseInt(num.substring(i,i+1));
			retorno.append(padrao[iNum][1]);
			i++;
		}
		retorno.append(center);
		//lado direito do código
		while( i < 8 ){
			iNum = Integer.parseInt(num.substring(i,i+1));
			retorno.append(padrao[iNum][3]);
			i++;			
		}
		retorno.append(right);
		return retorno.toString();
	}
	
    public BufferedImage getImagem(){
		return desenhar(criarPretoBranco());
    }

	@Override
	public BufferedImage desenhar(String pb) {
		int tit1 = 5;
	    int tit2 = 36;
		int w = getMargensImagem()[0] + (pb.length() * getEspessura()) + getMargensImagem()[1] + 2;
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
			if( caracter.equals("1")) {
				g.setColor(getColorBarCode());
			}
			//System.out.println("i="+i);
			switch (i) {
			case 0: case 1: case 2:{
				g.drawLine(tam, getMargensImagem()[2], tam, getAltura()+12);
				tit1=tam+3;
				break;
			}
			case 32: case 33: case 34: case 35:{
				g.drawLine(tam, getMargensImagem()[2], tam, getAltura()+12);
				tit2=tam+2;
				break;				
			}
			case 64: case 65: case 66:{
				g.drawLine(tam, getMargensImagem()[2], tam, getAltura()+12);
				break;
			}
			default:
				g.drawLine(tam, getMargensImagem()[2], tam, getAltura());
				break;
			}		    
			g.setColor(Color.WHITE);
			tam = tam + (int)getEspessura();
		}
		//desenha a legenda
		if(isPrintLegenda()){
			if(getLegenda()==null){
				setLegenda(getValor()+getDv());
			}
			g.setFont(getFonteLegenda());
			g.setColor(getColorLegenda());
			g.drawString(getLegenda().substring(0,4),tit1, getAltura()+((getAlturaLegenda()+getMargensImagem()[2])/2));			
			g.drawString(getLegenda().substring(4,8),tit2, getAltura()+((getAlturaLegenda()+getMargensImagem()[2])/2));
		}
		image.flush();
		return image;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

}
