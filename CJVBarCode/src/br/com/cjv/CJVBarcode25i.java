package br.com.cjv;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * Gerador de código de barras padrão 2e5 Intercalado
 * @author Cirilo José Veloso
 *
 */
public class CJVBarcode25i extends AbstractCJVBarcode{
	

	/*
	private final  String[] padrao = new String[]{		
			"00110",
			"10001",
			"01001",
			"11000",
			"00101",
			"10100",
			"01100",
			"00011",
			"10010",
			"01010"};
	*/
	
	public CJVBarcode25i(String valor) {
		super(valor);
	}



	public CJVBarcode25i(String valor, boolean printLegenda,
			boolean centralizarLegenda) {
		super(valor, printLegenda, centralizarLegenda);
	}


	public CJVBarcode25i(String valor, int altura, int espessura,
			int[] margens, boolean printLegenda, int alturaLegenda,
			boolean centralizarLegenda, int[] margensLegenda,
			Font fonteLegenda, Color colorLegenda) {
		super(valor, altura, espessura, margens, printLegenda, alturaLegenda,
				centralizarLegenda, margensLegenda, fonteLegenda, colorLegenda);
	}


	private String criarPretoBranco() {
		StringBuffer retorno = new StringBuffer();
		String num = Utils.somenteNumeros(getValor());
		String bin = "0011010001010011100000101101000110000011100100101000110";
		int resto = (num.length() % 2);
		if (resto != 0) {
			num = "0" + num;
		}
		String start = "PBPB"; // preto fino,brco fino,preto fino,brco fino
		String stop = "PPPBP"; // preto grosso,brco fin,preto fino.
		retorno.append(start);	
		int pri, sec, i = 0, iniInter1, iniInter2;
		String inter = "", inter1, inter2;
		while (i < num.length()) {
			pri = new Integer(num.substring(i, i + 1));
			sec = new Integer(num.substring(i + 1, i + 2));
			for (int j = 0; j <= 4; j++) {
				iniInter1 = pri * 5 + j;
				iniInter2 = sec * 5 + j;
				inter1 = bin.substring(iniInter1, iniInter1 + 1);
				inter2 = bin.substring(iniInter2, iniInter2 + 1);
				inter = inter + inter1 + inter2;
			}
			//System.out.println("inter="+inter);
			i = i + 2;
		}
		for (int c = 0; c < inter.length(); c++) {
			resto = (c+1) % 2;
			if (resto != 0) {
				if (inter.substring(c, c + 1).equals("1")) {
					retorno.append("PPP");
				} else {
					retorno.append("P");
				}
			} else {
				if (inter.substring(c, c + 1).equals("1")) {
					retorno.append("BBB");
				} else {
					retorno.append("B");
				}
			}
		}
		retorno.append(stop);
		return retorno.toString();
	}

	
    public BufferedImage getImagem(){
		return desenhar(criarPretoBranco());
    }

}
