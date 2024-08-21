package automato;

import java.util.ArrayList;
import java.util.List;

public class MaquinaDeTuring {

	private String linguagem;
	private List<Estado> estados = new ArrayList<Estado>();
	// private List<Character> simbolosEntrada = new ArrayList<Character>();
	// private List<Character> simbolosFita = new ArrayList<Character>();
	private Estado estadoInicial;
	private char simboloBranco;
	private List<String> estadosFinais;

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

	public List<String> getEstados() {
		List<String> est = new ArrayList<String>();
		for(Estado estado : estados)
			est.add(estado.getNome());
		return est;
	}
	
	public Estado addEstado(String nome) {
		Estado estado = new Estado(nome);
		estados.add(estado);
		return estado;
	}
	
	public Estado getEstadoInicial() {
		return estadoInicial;
	}

	public void setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	
	public char getSimboloBranco() {
		return simboloBranco;
	}

	public void setSimboloBranco(char simboloBranco) {
		this.simboloBranco = simboloBranco;
	}
	
	public List<String> getEstadosFinais(){
		estadosFinais = new ArrayList<String>();
		for(Estado estado : estados) {
			if(estado.isEstadoFinal())
				estadosFinais.add(estado.getNome());
		}
		return estadosFinais;
	}
	
	public boolean verificarCadeia(String cadeia) {

		// Montagem da fita
		
		char[] vetorCadeia = cadeia.toCharArray();
		
		char[] fita = new char[vetorCadeia.length + 2];
		
		for(int i = 0; i < fita.length; i++) {
			if(i > 0 && i < fita.length - 1)
				fita[i] = vetorCadeia[i - 1];
			else
				fita[i] = simboloBranco;
		}
		
		// Início da leitura
		
		Estado estadoAtual = estadoInicial;
		int posicao = 1;
		
		while(posicao < fita.length) {
			Transicao transicao = estadoAtual.getTransicoes().get(fita[posicao]);
			
			if(transicao == null)
				break;
			
			for(Estado estado : estados) {
				if(estado.getNome().equals(transicao.getEstado()))
					estadoAtual = estado;
			}
			
			fita[posicao] = transicao.getSimbolo();
			
			if(transicao.getDirecao() == 'D')
				posicao++;
			else if(transicao.getDirecao() == 'E')
				posicao--;
		}
		
		if(estadoAtual.isEstadoFinal())
			return true;
		else
			return false;
	}
	
}