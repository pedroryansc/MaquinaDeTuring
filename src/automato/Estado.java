package automato;

import java.util.HashMap;

public class Estado {

	private String nome;
	private boolean estadoFinal;
	private HashMap<Character, Transicao> transicoes = new HashMap<Character, Transicao>();
	
	public Estado(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public boolean isEstadoFinal() {
		return estadoFinal;
	}

	public Estado estadoFinal() {
		estadoFinal = true;
		return this;
	}
	
	public HashMap<Character, Transicao> getTransicoes(){
		return transicoes;
	}
	
	public Estado addTransicao(char simboloLido, String estado, char simboloGravado, char direcao) {
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