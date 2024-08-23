package com.iplinski.automato;

import java.util.HashMap;

/**
 * Classe que representa um estado da Máquina de Turing (MT) e que é utilizada para criar as transições da MT.
 * 
 * @version 1.0
 * @author Pedro Ryan Coelho Iplinski
 */

public class Estado {

	private String nome;
	private boolean estadoFinal = false;
	private HashMap<Character, Transicao> transicoes = new HashMap<Character, Transicao>();
	
	/**
	 * Cria um estado da Máquina de Turing.
	 * 
	 * @param nome Nome do estado
	 */
	
	public Estado(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna o nome do estado da Máquina de Turing.
	 * 
	 * @return o nome do estado da MT
	 */
	
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna um valor booleano que indica se o estado é um estado final ou não.
	 * 
	 * @return true se o estado for um estado final ou false se não for
	 */
	
	public boolean isEstadoFinal() {
		return estadoFinal;
	}

	/**
	 * Define o estado como estado final da Máquina de Turing.
	 * 
	 * @return o objeto da MT
	 */
	
	public Estado estadoFinal() {
		estadoFinal = true;
		return this;
	}
	
	/**
	 * Retorna o HashMap que contém as transições em que este estado é um argumento.
	 * 
	 * @return o HashMap que contém as transições do estado.
	 */
	
	public HashMap<Character, Transicao> getTransicoes(){
		return transicoes;
	}
	
	/**
	 * Adiciona uma nova transição ao conjunto de transições em que este estado é um argumento da transição.
	 * 
	 * @param simboloLido Símbolo que é lido na fita
	 * @param estado Estado que a MT assume
	 * @param simboloGravado Símbolo que é gravado no lugar do símbolo lido na fita
	 * @param direcao Valor booleano que representa a direção em que o cabeçote da MT se move, 
	 * 				sendo true para a direita e false para a esquerda
	 * @return o objeto do estado
	 */
	
	public Estado addTransicao(char simboloLido, String estado, char simboloGravado, boolean direcao) {
		transicoes.put(simboloLido, new Transicao(estado, simboloGravado, direcao));
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Estado [nome=");
		builder.append(nome);
		builder.append(", estadoFinal=");
		builder.append(estadoFinal);
		builder.append(", transicoes=");
		builder.append(transicoes);
		builder.append("]");
		return builder.toString();
	}
	
}