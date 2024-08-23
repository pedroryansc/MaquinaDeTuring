package com.iplinski.automato;

/**
 * Classe que representa a saída de uma transição da Máquina de Turing (MT).
 * 
 * @version 1.0
 * @author Pedro Ryan Coelho Iplinski
 */

public class Transicao {

	private String estado;
	private char simbolo;
	private boolean direcao;
	
	/**
	 * Cria a saída de uma transição da Máquina de Turing.
	 * 
	 * @param estado Estado que a MT assume
	 * @param simbolo Símbolo que é gravado no lugar do símbolo lido na fita
	 * @param direcao Valor booleano que representa a direção em que o cabeçote da MT se move,
	 * 				sendo true para a direita e false para a esquerda
	 */
	
	public Transicao(String estado, char simbolo, boolean direcao) {
		this.estado = estado;
		this.simbolo = simbolo;
		this.direcao = direcao;
	}
	
	/**
	 * Retorna o estado que a Máquina de Turing assume quando a transição é executada.
	 * 
	 * @return o estado que a MT assume
	 */
	
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Retorna o símbolo que é gravado no lugar do símbolo lido na fita da Máquina de Turing.
	 * 
	 * @return o símbolo que é gravado no lugar do símbolo lido na fita
	 */
	
	public char getSimbolo() {
		return simbolo;
	}
	
	/**
	 * Retorna um valor booleano que representa a direção em que o cabeçote da Máquina de Turing se move.
	 * 
	 * @return true se for para a direita ou false se for para a esquerda
	 */
	
	public boolean getDirecao() {
		return direcao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transicao [estado=");
		builder.append(estado);
		builder.append(", simbolo=");
		builder.append(simbolo);
		builder.append(", direcao=");
		builder.append(direcao);
		builder.append("]");
		return builder.toString();
	}
	
}