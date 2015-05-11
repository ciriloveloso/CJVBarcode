package br.com.cjv;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

/**
 * Gerador de código de barras padrão 128
 * @see br.com.cjv.Tipo128
 * @author Cirilo José Veloso
 *
 */
public class CJVBarcode128 extends AbstractCJVBarcode{

	private final String STOP = "PPBBBPPPBPBPP";
	private final String ASPAS_DUPLAS = (char)34+"";
	private final String BARRA_INVERTIDA = (char)92+"";
	private Tipo128 tipo = Tipo128.Tipo128A;
	
	private final  String[][] padrao = new String[][]{		
	{"PPBPPBBPPBB"," "},
	{"PPBBPPBPPBB","!"},
	{"PPBBPPBBPPB",ASPAS_DUPLAS},
	{"PBBPBBPPBBB","#"},
	{"PBBPBBBPPBB","$"},
	{"PBBBPBBPPBB","%"},
	{"PBBPPBBPBBB","&"},
	{"PBBPPBBBPBB","'"},
	{"PBBBPPBBPBB","("},
	{"PPBBPBBPBBB",")"},
	{"PPBBPBBBPBB","*"},
	{"PPBBBPBBPBB","+"},
	{"PBPPBBPPPBB",","},
	{"PBBPPBPPPBB","-"},
	{"PBBPPBBPPPB","."},
	{"PBPPPBBPPBB","/"},
	{"PBBPPPBPPBB","0"},
	{"PBBPPPBBPPB","1"},
	{"PPBBPPPBBPB","2"},
	{"PPBBPBPPPBB","3"},
	{"PPBBPBBPPPB","4"},
	{"PPBPPPBBPBB","5"},
	{"PPBBPPPBPBB","6"},
	{"PPPBPPBPPPB","7"},
	{"PPPBPBBPPBB","8"},
	{"PPPBBPBPPBB","9"},
	{"PPPBBPBBPPB",":"},
	{"PPPBPPBBPBB",";"},
	{"PPPBBPPBPBB","<"},
	{"PPPBBPPBBPB","="},
	{"PPBPPBPPBBB",">"},
	{"PPBPPBBBPPB","?"},
	{"PPBBBPPBPPB","@"},
	{"PBPBBBPPBBB","A"},
	{"PBBBPBPPBBB","B"},
	{"PBBBPBBBPPB","C"},
	{"PBPPBBBPBBB","D"},
	{"PBBBPPBPBBB","E"},
	{"PBBBPPBBBPB","F"},
	{"PPBPBBBPBBB","G"},
	{"PPBBBPBPBBB","H"},
	{"PPBBBPBBBPB","I"},
	{"PBPPBPPPBBB","J"},
	{"PBPPBBBPPPB","K"},
	{"PBBBPPBPPPB","L"},
	{"PBPPPBPPBBB","M"},
	{"PBPPPBBBPPB","N"},
	{"PBBBPPPBPPB","O"},
	{"PPPBPPPBPPB","P"},
	{"PPBPBBBPPPB","Q"},
	{"PPBBBPBPPPB","R"},
	{"PPBPPPBPBBB","S"},
	{"PPBPPPBBBPB","T"},
	{"PPBPPPBPPPB","U"},
	{"PPPBPBPPBBB","V"},
	{"PPPBPBBBPPB","W"},
	{"PPPBBBPBPPB","X"},
	{"PPPBPPBPBBB","Y"},
	{"PPPBPPBBBPB","Z"},
	{"PPPBBBPPBPB","["},
	{"PPPBPPPPBPB",BARRA_INVERTIDA}, 
	{"PPBBPBBBBPB","]"},
	{"PPPPBBBPBPB","^"},
	{"PBPBBPPBBBB","_"},
	{"PBPBBBBPPBB","`"},
	{"PBBPBPPBBBB","a"},
	{"PBBPBBBBPPB","b"},
	{"PBBBBPBPPBB","c"},
	{"PBBBBPBBPPB","d"},
	{"PBPPBBPBBBB","e"},
	{"PBPPBBBBPBB","f"},
	{"PBBPPBPBBBB","g"},
	{"PBBPPBBBBPB","h"},
	{"PBBBBPPBPBB","i"},
	{"PBBBBPPBBPB","j"},
	{"PPBBBBPBBPB","k"},
	{"PPBBPBPBBBB","l"},
	{"PPPPBPPPBPB","m"},
	{"PPBBBBPBPBB","n"},
	{"PBBBPPPPBPB","o"},
	{"PBPBBPPPPBB","p"},
	{"PBBPBPPPPBB","q"},
	{"PBBPBBPPPPB","r"},
	{"PBPPPPBBPBB","s"},
	{"PBBPPPPBPBB","t"},
	{"PBBPPPPBBPB","u"},
	{"PPPPBPBBPBB","v"},
	{"PPPPBBPBPBB","w"},
	{"PPPPBBPBBPB","x"},
	{"PPBPPBPPPPB","y"},
	{"PPBPPPPBPPB","z"},
	{"PPPPBPPBPPB","{"},
	{"PBPBPPPPBBB","|"},
	{"PBPBBBPPPPB","}"},
	{"PBBBPBPPPPB","~"},
	{"PBPPPPBPBBB","DEL"},
	{"PBPPPPBBBPB","FNC3"},
	{"PPPPBPBPBBB","FNC2"},
	{"PPPPBPBBBPB","SHIFT"},
	{"PBPPPBPPPPB","CODE C"},
	{"PBPPPPBPPPB","FNC4"},
	{"PPPBPBPPPPB","CODE A"},
	{"PPPPBPBPPPB","FNC1"},
	{"PPBPBBBBPBB","START CODE A"},
	{"PPBPBBPBBBB","START CODE B"},
	{"PPBPBBPPPBB","START CODE C"},			
	};


	public CJVBarcode128(String valor, boolean printLegenda, boolean centralizarLegenda, Tipo128 tipo) {
		super(valor, printLegenda, centralizarLegenda);
		this.tipo = tipo;
	}

	public CJVBarcode128(String valor, boolean printLegenda,
			boolean centralizarLegenda, char tipo) {
		super(valor, printLegenda, centralizarLegenda);
		switch (tipo) {
		case 'A': {
			this.tipo = Tipo128.Tipo128A;
			break;
		}
		case 'B': {
			this.tipo = Tipo128.Tipo128B;
			break;
		}
		case 'C': {
			this.tipo = Tipo128.Tipo128C;
			break;
		}
		default: {
			this.tipo = Tipo128.Tipo128C;
			break;
		}
		}
	}

	public CJVBarcode128(String valor, int altura, int espessura,
			int[] margens, boolean printLegenda, int alturaLegenda,
			boolean centralizarLegenda, int[] margensLegenda,
			Font fonteLegenda, Color colorLegenda,Tipo128 tipo) {
		super(valor, altura, espessura, margens, printLegenda, alturaLegenda,
				centralizarLegenda, margensLegenda, fonteLegenda, colorLegenda);
		this.tipo = tipo;
	}


	public CJVBarcode128(String valor, Tipo128 tipo) {
		super(valor);
		this.tipo = tipo;
	}

	public CJVBarcode128(String valor, char tipo) {
		super(valor);
		switch (tipo) {
		case 'A': {
			this.tipo = Tipo128.Tipo128A;
			break;
		}
		case 'B': {
			this.tipo = Tipo128.Tipo128B;
			break;
		}
		case 'C': {
			this.tipo = Tipo128.Tipo128C;
			break;
		}
		default: {
			this.tipo = Tipo128.Tipo128C;
			break;
		}
		}		
	}


	private String criarPretoBranco() {
    	StringBuffer buffer = new StringBuffer(); 
    	int step = 1;
    	String dv;    
    	switch (tipo.getCodigo()) {
    	   case 'A':{
    		   buffer.append("PPBPBBBBPBB");
    		   step = 1;
    		   break;
    	   }    	   
    	   case 'B':{
    		   buffer.append("PPBPBBPBBBB");
    		   step = 1;
    		   break;
    	   }
    	   case 'C':{    		   
    		   buffer.append("PPBPBBPPPBB");
    		   step = 2;
    		   setValor(Utils.somenteNumeros(getValor()));
    		   int par = getValor().length() % 2;
    		   if(par!=0){
    			   setValor("0"+getValor());
    		   }
    		   break;
    	   }
    	}
    	dv = digito128(tipo.getCodigo());
    	int i = 0;
    	int pos = 0;
    	String pb;
    	while ( i < getValor().length()){
    		if(step==2){
    			pos = new Integer(getValor().substring(i, i + step));
    			buffer.append(padrao[pos][0]);
    		}else{ 
    			pb = pretoBranco128(getValor().substring(i, i + step));
    			buffer.append(pb);
    		}
    		i = i + step;    		
    	}
    	buffer.append(pretoBranco128(dv));
    	buffer.append(STOP);
        return buffer.toString();  
    }	
    
    public String posicao128(String caracter){
    	String retorno = ""; 
    	for (int i = 0; i < padrao.length; i++ ){
    		if(padrao[i][1].equals(caracter) ){
    			retorno = String.format("%02d", i);
    			break;
    		}
    	}
        return retorno;
    }
    
    public String pretoBranco128(String caracter){
    	String retorno = ""; 
    	for (int i = 0; i < padrao.length; i++ ){
    		if(padrao[i][1].equals(caracter) ){
    			retorno = padrao[i][0];
    			break;
    		}
    	}
        return retorno;
    }
  
    
    private String digito128(char tipo){
    	int dv;
    	int soma = 104;
    	int step =  1; 
    	int c = 0;
    	String posNumero = "";
    	switch (tipo) {
    	case 'A':
    		soma = 103;
    		step = 1;
    	    break;
    	case 'B':
    		soma = 104;
    		step = 1;
    		break;
    	case 'C':
    		soma = 105;
    		step = 2;
    		break;
    	}
    	String caracter = "";
    	while (c < getValor().length()){
    		caracter = getValor().substring(c, c + step);
    		if(step==2){
    			posNumero = posNumero + caracter;
    		}else{
    			posNumero = posNumero + posicao128(caracter);
    		}
    		c = c + step;
    	}
    	c = 0;
    	String pos;
    	int subTotal;
    	int x = 1;
    	while (c < posNumero.length()){
    		pos = posNumero.substring(c, c + 2);
    		subTotal = new Integer(pos) * x;
    		soma = soma + subTotal;
    		c = c + 2;
    		x++;
    	}
    	dv = (soma % 103);
    	return padrao[dv][1];
    }
	
	public Tipo128 getTipo() {
		return tipo;
	}

	public void setTipo(Tipo128 tipo) {
		this.tipo = tipo;
	}

		
    public BufferedImage getImagem(){
		return desenhar(criarPretoBranco());
    }
}
