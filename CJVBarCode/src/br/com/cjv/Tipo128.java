package br.com.cjv;

/**
 * Enum para escolher o tipo de c�digo de barras 128, A,B ou C
 * @author Cirilo Jos� Veloso
 *
 */
public enum Tipo128 {
	Tipo128A('A'),Tipo128B('B'),Tipo128C('C');
	private char codigo;

	Tipo128(char codigo) {
		this.codigo = codigo;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}
	
}
