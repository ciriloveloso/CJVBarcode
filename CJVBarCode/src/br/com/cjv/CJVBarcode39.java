package br.com.cjv;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * Gerador de código de barras padrão 3e9
 * @author Cirilo Cirilo José Veloso
 *
 */
public class CJVBarcode39 extends AbstractCJVBarcode{

		
	public CJVBarcode39(String valor, boolean printLegenda,
			boolean centralizarLegenda) {
		super(valor, printLegenda, centralizarLegenda);
	}

	public CJVBarcode39(String valor, int altura, int espessura, int[] margens,
			boolean printLegenda, int alturaLegenda,
			boolean centralizarLegenda, int[] margensLegenda,
			Font fonteLegenda, Color colorLegenda) {
		super(valor, altura, espessura, margens, printLegenda, alturaLegenda,
				centralizarLegenda, margensLegenda, fonteLegenda, colorLegenda);
	}

	public CJVBarcode39(String valor) {
		super(valor);
	}

	private final  String[][] padrao = new String[][]{	
			{"1","BPPPBPBBBPBPBPPPB"},
			{"2","BPBPPPBBBPBPBPPPB"},
			{"3","BPPPBPPPBBBPBPBPB"},
			{"4","BPBPBBBPPPBPBPPPB"},
			{"5","BPPPBPBBBPPPBPBPB"},
			{"6","BPBPPPBBBPPPBPBPB"},
			{"7","BPBPBBBPBPPPBPPPB"},
			{"8","BPPPBPBBBPBPPPBPB"},
			{"9","BPBPPPBBBPBPPPBPB"},
			{"0","BPBPBBBPPPBPPPBPB"},
			{"A","BPPPBPBPBBBPBPPPB"},
			{"B","BPBPPPBPBBBPBPPPB"},
			{"C","BPPPBPPPBPBBBPBPB"},
			{"D","BPBPBPPPBBBPBPPPB"},
			{"E","BPPPBPBPPPBBBPBPB"},
			{"F","BPBPPPBPPPBBBPBPB"},
			{"G","BPBPBPBBBPPPBPPPB"},
			{"H","BPPPBPBPBBBPPPBPB"},
			{"I","BPBPPPBPBBBPPPBPB"},
			{"J","BPBPBPPPBBBPPPBPB"},
			{"K","BPPPBPBPBPBBBPPPB"},
			{"L","BPBPPPBPBPBBBPPPB"},
			{"M","BPPPBPPPBPBPBBBPB"},
			{"N","BPBPBPPPBPBBBPPPB"},
			{"O","BPPPBPBPPPBPBBBPB"},
			{"P","BPBPPPBPPPBPBBBPB"},
			{"Q","BPBPBPBPPPBBBPPPB"},
			{"R","BPPPBPBPBPPPBBBPB"},
			{"S","BPBPPPBPBPPPBBBPB"},
			{"T","BPBPBPPPBPPPBBBPB"},
			{"U","BPPPBBBPBPBPBPPPB"},
			{"V","BPBBBPPPBPBPBPPPB"},
			{"W","BPPPBBBPPPBPBPBPB"},
			{"X","BPBBBPBPPPBPBPPPB"},
			{"Y","BPPPBBBPBPPPBPBPB"},
			{"Z","BPBBBPPPBPPPBPBPB"},
			{"-","BPBBBPBPBPPPBPPPB"},
			{".","BPPPBBBPBPBPPPBPB"},
			{" ","BPBBBPPPBPBPPPBPB"},
			{"*","BPBBBPBPPPBPPPBPB"},
			{"$","BPBBBPBBBPBBBPBPB"},
			{"/","BPBBBPBBBPBPBBBPB"},
			{"+","BPBBBPBPBBBPBBBPB"},
			{"%","BPBPBBBPBBBPBBBPB"}		
	};
	
	private String criarPretoBranco() {
    	StringBuffer buffer = new StringBuffer(); 
    	//start
    	String start = pretoBranco39("*");
    	buffer.append(start);    	
    	String pb;
    	for(int i=0;i < getValor().length();i++){
   			pb = pretoBranco39(getValor().substring(i, i + 1));
   			buffer.append(pb);
    	}
    	//stop é igual ao start
    	buffer.append(start);    	
        return buffer.toString();  
    }	
    
    public String posicao39(String caracter){
    	String retorno = ""; 
    	for (int i = 0; i < padrao.length; i++ ){
    		if(padrao[i][0].equals(caracter) ){
    			retorno = String.format("%02d", i);
    			break;
    		}
    	}
        return retorno;
    }
    
    public String pretoBranco39(String caracter){
    	String retorno = ""; 
    	for (int i = 0; i < padrao.length; i++ ){
    		if(padrao[i][0].equals(caracter) ){
    			retorno = padrao[i][1];
    			break;
    		}
    	}
        return retorno;
    }

    public BufferedImage getImagem(){
		return desenhar(criarPretoBranco());
    }
    
}
