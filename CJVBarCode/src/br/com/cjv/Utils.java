package br.com.cjv;
/**
 * Métodos utilitários
 * @author Cirilo José Veloso
 * 
 */
public class Utils {
	
	
	public char intCh(short inteiro){
		char c = ' ';
		if (inteiro >= 0 && inteiro <= 9) { 
			c = Character.forDigit(inteiro + (char)0, 10);
		}
		return c;
	}
	
 	public static String lpad(String valueToPad, String filler, int size) {   
        while (valueToPad.length() < size) {   
            valueToPad = filler + valueToPad;   
        }   
        return valueToPad;   
    }   
       
    public static String rpad(String valueToPad, String filler, int size) {   
        while (valueToPad.length() < size) {   
            valueToPad = valueToPad+filler;   
        }   
        return valueToPad;   
    }   

    public static String somenteNumeros(String str) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i<=str.length()-1; i++) {
            if (Character.isDigit(str.charAt(i))) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * Objetivo: Cálculo do módulo 10 com peso 1 e 2
     * @param numeros
     * @return String
     */
    public static String modulo1012(String numeros) {
        int peso = 2;
        int total = 0;
        int mult = 0;
        int dv = 0;
        for (int i = numeros.length() - 1; i >= 0; i--) {
            if (Character.isDigit(numeros.charAt(i))) {
                int num = Character.getNumericValue(numeros.codePointAt(i));
                mult = num * peso;
                if(mult > 9){
                    mult = mult - 9;
                }
                total = total + mult;
                peso--;
                if (peso==0){peso=2;}
            }
        }
        dv = 10 - (total % 10);
        if (dv >= 10) {
            dv = 0;
        }
        return new Integer(dv).toString();
    }    
    
    /*
     * Módulo 11 peso de 2 a 9
     *
    */
    public static String modulo1129(String numeros) {
        int peso = 2;
        int total = 0;
        int dv = 0;
        for (int i = numeros.length() - 1; i >= 0; i--) {
            if (Character.isDigit(numeros.charAt(i))) {
                int num = Character.getNumericValue(numeros.codePointAt(i));
                total = total + (num * peso);
                peso++;
                if (peso==10){peso=2;}
            }
        }
        dv = 11 - (total % 11);
        if (dv >= 10) {
            dv = 1;
        }
        return new Integer(dv).toString();
    }    
    
    /*
     * Módulo 11 peso de 2 a 9 
     *
    */
    public static String modulo11Arrecada(String numeros) {
        int peso = 2;
        int total = 0;
        int dv = 0;
        for (int i = numeros.length() - 1; i >= 0; i--) {
            if (Character.isDigit(numeros.charAt(i))) {
                int num = Character.getNumericValue(numeros.codePointAt(i));
                total = total + (num * peso);
                peso++;
                if (peso==10){peso=2;}
            }
        }
        dv = 11 - (total % 11);
        if (dv >= 10) {
            dv = 0;
        }
        return new Integer(dv).toString();
    }    
    
    /**
     * Objetivo: Cálculo do módulo 11 com peso de 2 a 9
     * @param numeros
     * @return String
     */
	public static String modulo11CPF(String numeros){
		int peso = 2;
		int total = 0;
		int resto = 0;
		int dv = 0;
		for(int i=numeros.length()-1;i>=0; i--){
			if (Character.isDigit(numeros.charAt(i))){
				int num =Character.getNumericValue(numeros.codePointAt(i));
				total   = total + ( num * peso);
				peso++;
			}
		}
		resto = (total % 11);
		if(resto < 2){
			dv = 0;
		}else{
	  		dv = 11 - resto;			
		}
		return new Integer(dv).toString();
	}
	/**
	 * Objetivo: Cálculo do módulo 11 com peso de 2 a N
	 * @param numeros
	 * @return String
	 */
	public static String modulo11Cnpj(String numeros){
		int peso = 2;
		int total = 0;
		int resto = 0;
		int dv = 0;
		for(int i=numeros.length()-1;i>=0; i--){
			if (Character.isDigit(numeros.charAt(i))){
				int num =Character.getNumericValue(numeros.codePointAt(i));
				total   = total + ( num * peso);
				peso++;
				if(peso>9){peso=2;}
			}
		}
		resto = (total % 11);
		if(resto < 2){
			dv = 0;
		}else{
	  		dv = 11 - resto;			
		}
		return new Integer(dv).toString();
	}
	
}
