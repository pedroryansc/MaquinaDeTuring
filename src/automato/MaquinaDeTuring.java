package automato;

import java.util.ArrayList;
import java.util.List;

public class MaquinaDeTuring {

	private String linguagem;
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Character> simbolosEntrada = new ArrayList<Character>();
	private List<Character> simbolosFita = new ArrayList<Character>();
	private List<String> funcaoTransicao = new ArrayList<String>();
	private Estado estadoInicial;
	private char simboloBranco;
	private List<String> estadosFinais = new ArrayList<String>();

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

	public MaquinaDeTuring setLinguagem(String linguagem) {
		this.linguagem = linguagem;
		return this;
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
	
	public List<Character> getSimbolosEntrada(){
		for(Estado estado : estados) {
			for(char simboloLido : estado.getTransicoes().keySet()) {
				Transicao transicao = estado.getTransicoes().get(simboloLido);
				if(!(simboloLido == transicao.getSimbolo()) && !(simbolosEntrada.contains(simboloLido)))
					simbolosEntrada.add(simboloLido);
			}
		}
		return simbolosEntrada;
	}
	
	public List<Character> getSimbolosFita(){
		for(Estado estado : estados) {
			for(char simbolo : estado.getTransicoes().keySet()) {
				if(!(simbolosFita.contains(simbolo)))
					simbolosFita.add(simbolo);
			}
		}
		return simbolosFita;
	}
	
	public List<String> getFuncaoTransicao(){
		for(Estado estado : estados) {
			for(char simboloLido : estado.getTransicoes().keySet()) {
				Transicao transicao = estado.getTransicoes().get(simboloLido);
				String t = "δ(" + estado.getNome() + ", " + simboloLido + ") = (" + transicao.getEstado() + ", " + transicao.getSimbolo() + ", ";
				if(transicao.getDirecao())
					t += "D)";
				else
					t += "E)";
				if(!(funcaoTransicao.contains(t)))
					funcaoTransicao.add(t);
			}
		}
		return funcaoTransicao;
	}
	
	public String getEstadoInicial() {
		return estadoInicial.getNome();
	}

	public MaquinaDeTuring setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
		return this;
	}
	
	public char getSimboloBranco() {
		return simboloBranco;
	}

	public MaquinaDeTuring setSimboloBranco(char simboloBranco) {
		this.simboloBranco = simboloBranco;
		return this;
	}
	
	public List<String> getEstadosFinais(){
		for(Estado estado : estados) {
			if(estado.isEstadoFinal()) {
				if(!(estadosFinais.contains(estado.getNome())))
					estadosFinais.add(estado.getNome());
			}
		}
		return estadosFinais;
	}
	
	private char[] montarFita(String cadeia) {
		char[] vetorCadeia = cadeia.toCharArray();
		
		char[] fita = new char[vetorCadeia.length + 2];
		
		for(int i = 0; i < fita.length; i++) {
			if(i > 0 && i < fita.length - 1)
				fita[i] = vetorCadeia[i - 1];
			else
				fita[i] = simboloBranco;
		}
		
		return fita;
	}
	
	private String leituraCadeia(String cadeia, boolean gerarID) {
		
		// Montagem da fita
		
		char[] fita = montarFita(cadeia);
		
		// Inicialização do autômato
		
		Estado estadoAtual = estadoInicial;
		int posicao = 1;
		
		// Início da Descrição Instantânea
		
		String id = "";
		
		if(gerarID) {
			id = "(";
			
			for(int i = 0; i < fita.length; i++) {
				if(i == 1)
					id += "\033[1m" + estadoAtual.getNome() + "\033[0m";
				id += fita[i];
			}
			
			id += ")";
		}
		
		// Leitura da cadeia
		
		while(posicao < fita.length) {
			Transicao transicao = estadoAtual.getTransicoes().get(fita[posicao]);
			
			if(transicao == null)
				break;
			
			for(Estado estado : estados) {
				if(estado.getNome().equals(transicao.getEstado()))
					estadoAtual = estado;
			}
			
			fita[posicao] = transicao.getSimbolo();
			
			if(transicao.getDirecao())
				posicao++;
			else
				posicao--;
			
			if(gerarID) {
				id += " -> (";
				
				for(int i = 0; i < fita.length; i++) {
					if(i == posicao)
						id += "\033[1m" + estadoAtual.getNome() + "\033[0m";
					id += fita[i];
				}
				
				if(posicao == fita.length)
					id += "\033[1m" + estadoAtual.getNome() + "\033[0m";
				
				id += ")";
			}
		}
		
		if(estadoAtual.isEstadoFinal()) {
			if(gerarID) {
				id += " -> Aceita";
				return id;
			} else
				return "true";
		} else {
			if(gerarID) {
				id += " -> Não aceita";
				return id;
			} else
				return "false";
		}
	}
	
	public boolean verificarCadeia(String cadeia) {
		String resultado = leituraCadeia(cadeia, false);
		
		if(resultado.equals("true"))
			return true;
		else
			return false;
	}
	
	public String gerarID(String cadeia) {
		return leituraCadeia(cadeia, true);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MaquinaDeTuring [linguagem=");
		builder.append(linguagem);
		builder.append(", estados=");
		builder.append(estados);
		builder.append(", simbolosEntrada=");
		builder.append(simbolosEntrada);
		builder.append(", simbolosFita=");
		builder.append(simbolosFita);
		builder.append(", funcaoTransicao=");
		builder.append(funcaoTransicao);
		builder.append(", estadoInicial=");
		builder.append(estadoInicial);
		builder.append(", simboloBranco=");
		builder.append(simboloBranco);
		builder.append(", estadosFinais=");
		builder.append(estadosFinais);
		builder.append("]");
		return builder.toString();
	}
	
}