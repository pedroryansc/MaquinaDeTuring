package automato;

import java.util.ArrayList;
import java.util.List;

public class MaquinaDeTuring {

	private String linguagem;
	private List<Estado> estados = new ArrayList<Estado>();
	// private List<Character> simbolosEntrada = new ArrayList<Character>();
	// private List<Character> simbolosFita = new ArrayList<Character>();
	// private List<String> funcaoTransicao = new ArrayList<String>();
	private char simboloBranco;
	private Estado estadoInicial;
	// private List<String> estadosFinais = new ArrayList<String>();

	public MaquinaDeTuring(char simboloBranco) {
		setSimboloBranco(simboloBranco);
	}
	
	public MaquinaDeTuring(String linguagem, char simboloBranco) {
		setLinguagem(linguagem);
		setSimboloBranco(simboloBranco);
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}

	public List<Estado> getEstados() {
		return estados;
	}
	
	public Estado addEstado(String nome) {
		Estado estado = new Estado(nome);
		estados.add(estado);
		return estado;
	}

	public char getSimboloBranco() {
		return simboloBranco;
	}

	public void setSimboloBranco(char simboloBranco) {
		this.simboloBranco = simboloBranco;
	}

	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	
	public void verificarCadeia(String cadeia) {
		
	}
	
}