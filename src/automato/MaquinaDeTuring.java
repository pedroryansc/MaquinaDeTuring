package automato;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilizada para estruturar uma Máquina de Turing (MT) e verificar se uma cadeia pertence ou não à linguagem reconhecida pela MT.
 * 
 * @author Pedro Ryan Coelho Iplinski
 */

public class MaquinaDeTuring {

	private String linguagem;
	private List<Estado> estados = new ArrayList<Estado>();
	private List<Character> simbolosEntrada = new ArrayList<Character>();
	private List<Character> simbolosFita = new ArrayList<Character>();
	private List<String> funcaoTransicao = new ArrayList<String>();
	private Estado estadoInicial;
	private char simboloBranco;
	private List<String> estadosFinais = new ArrayList<String>();

	/**
	 * Cria uma Máquina de Turing definindo seu símbolo branco.
	 * 
	 * @param simboloBranco Símbolo que compõe as extremidades da fita da MT
	 */
	
	public MaquinaDeTuring(char simboloBranco) {
		setSimboloBranco(simboloBranco);
	}
	
	/**
	 * Cria uma Máquina de Turing definindo a expressão da linguagem reconhecida e seu símbolo branco.
	 * 
	 * @param linguagem Expressão da linguagem reconhecida pela MT
	 * @param simboloBranco Símbolo que compõe as extremidades da fita da MT
	 */
	
	public MaquinaDeTuring(String linguagem, char simboloBranco) {
		setLinguagem(linguagem);
		setSimboloBranco(simboloBranco);
	}

	/**
	 * Retorna a expressão que representa a linguagem formal reconhecida pela Máquina de Turing.
	 * 
	 * @return a expressão da linguagem reconhecida pela MT (ou null caso não tenha sido definida)
	 */
	
	public String getLinguagem() {
		return linguagem;
	}
	
	/**
	 * Define a expressão que representa a linguagem formal reconhecida pela Máquina de Turing.
	 * 
	 * @param linguagem Expressão da linguagem reconhecida pela MT
	 * @return o objeto da MT
	 */
	
	public MaquinaDeTuring setLinguagem(String linguagem) {
		this.linguagem = linguagem;
		return this;
	}

	/**
	 * Retorna uma lista com os nomes dos estados da Máquina de Turing.
	 * 
	 * @return uma lista com os nomes dos estados da MT
	 */
	
	public List<String> getEstados() {
		List<String> est = new ArrayList<String>();
		for(Estado estado : estados)
			est.add(estado.getNome());
		return est;
	}
	
	/**
	 * Cria um novo estado e o adiciona ao conjunto de estados da Máquina de Turing.
	 * 
	 * @param nome Nome do estado
	 * @return o objeto do estado criado
	 */
	
	public Estado addEstado(String nome) {
		Estado estado = new Estado(nome);
		estados.add(estado);
		return estado;
	}
	
	/**
	 * A partir das transições criadas, retorna a lista dos símbolos de entrada da Máquina de Turing.
	 * 
	 * @return a lista que contém os símbolos de entrada da MT
	 */
	
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
	
	/**
	 * A partir das transições criadas, retorna a lista dos símbolos da fita da Máquina de Turing.
	 * 
	 * @return a lista que contém os símbolos da fita da MT, na qual também estão contidos os símbolos de entrada
	 */
	
	public List<Character> getSimbolosFita(){
		for(Estado estado : estados) {
			for(char simbolo : estado.getTransicoes().keySet()) {
				if(!(simbolosFita.contains(simbolo)))
					simbolosFita.add(simbolo);
			}
		}
		return simbolosFita;
	}
	
	/**
	 * Retorna a lista das definições formais das transições da Máquina de Turing.
	 * 
	 * @return a lista que contém as definições formais das transições da MT
	 */
	
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
	
	/**
	 * Retorna o nome do estado inicial da Máquina de Turing.
	 * 
	 * @return o nome do estado inicial da MT
	 */
	
	public String getEstadoInicial() {
		return estadoInicial.getNome();
	}
	
	/**
	 * Define o estado inicial da Máquina de Turing.
	 * 
	 * @param estadoInicial Objeto do estado inicial da MT
	 * @return o objeto da MT
	 */

	public MaquinaDeTuring setEstadoInicial(Estado estadoInicial) {
		this.estadoInicial = estadoInicial;
		return this;
	}
	
	/**
	 * Retorna o símbolo branco da Máquina de Turing.
	 * 
	 * @return o símbolo que compõe as extremidades da fita da MT
	 */
	
	public char getSimboloBranco() {
		return simboloBranco;
	}

	/**
	 * Define o símbolo branco da Máquina de Turing.
	 * 
	 * @param simboloBranco Símbolo que compõe as extremidades da fita da MT
	 * @return o objeto da MT
	 */
	
	public MaquinaDeTuring setSimboloBranco(char simboloBranco) {
		this.simboloBranco = simboloBranco;
		return this;
	}
	
	/**
	 * A partir dos estados criados, retorna a lista com os nomes dos estados finais da Máquina de Turing.
	 * 
	 * @return a lista que contém os nomes dos estados finais da MT
	 */
	
	public List<String> getEstadosFinais(){
		for(Estado estado : estados) {
			if(estado.isEstadoFinal()) {
				if(!(estadosFinais.contains(estado.getNome())))
					estadosFinais.add(estado.getNome());
			}
		}
		return estadosFinais;
	}
	
	/**
	 * A partir da cadeia passada como parâmetro, monta a fita utilizada para verificar se a cadeia
	 * pertence ou não à linguagem da Máquina de Turing.
	 * 
	 * @param cadeia Cadeia a ser analisada
	 * @return o vetor de caracteres da fita
	 */
	
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
	
	/**
	 * Executa a leitura da cadeia para verificar se ela pertence ou não à linguagem da Máquina de Turing.
	 * 
	 * @param cadeia Cadeia a ser analisada
	 * @param gerarID Valor booleano que indica se deve ser gerada a Descrição Instantânea (ID) que representa a fita ao longo da leitura
	 * @return a ID da verificação caso o valor de gerarID for verdadeiro. Caso contrário, retorna "true" se a cadeia for aceita ou "false" se não for
	 */
	
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
	
	/**
	 * Verifica se a cadeia pertence ou não à linguagem da Máquina de Turing.
	 * 
	 * @param cadeia Cadeia a ser analisada
	 * @return true se a cadeia for aceita ou false se não for
	 */
	
	public boolean verificarCadeia(String cadeia) {
		String resultado = leituraCadeia(cadeia, false);
		
		if(resultado.equals("true"))
			return true;
		else
			return false;
	}
	
	/**
	 * Gera a Descrição Instantânea (ID) que representa a fita ao longo da análise da cadeia,
	 * análise que verifica se a cadeia pertence ou não à linguagem da Máquina de Turing.
	 * 
	 * @param cadeia Cadeia a ser analisada
	 * @return a ID da verificação
	 */
	
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