package main;

import com.iplinski.automato.*;

public class Main {
	public static void main(String[] args) {
		
		MaquinaDeTuring mt = new MaquinaDeTuring('B');
		
		Estado q0 = mt.addEstado("q0").addTransicao('1', "q2", 'Y', true).addTransicao('0', "q4", 'X', true)
				.addTransicao('B', "q6", 'B', true).addTransicao('X', "q0", 'X', true).addTransicao('Y', "q0", 'Y', true);
		mt.setEstadoInicial(q0);
		
		mt.addEstado("q2").addTransicao('0', "q3", 'X', false).addTransicao('1', "q2", '1', true).addTransicao('X', "q2", 'X', true)
			.addTransicao('Y', "q2", 'Y', true);
		
		mt.addEstado("q3").addTransicao('Y', "q0", 'Y', true).addTransicao('0', "q3", '0', false).addTransicao('1', "q3", '1', false)
			.addTransicao('X', "q3", 'X', false);
		
		mt.addEstado("q4").addTransicao('1', "q5", 'Y', false).addTransicao('0', "q4", '0', true).addTransicao('X', "q4", 'X', true)
			.addTransicao('Y', "q4", 'Y', true);
		
		mt.addEstado("q5").addTransicao('X', "q0", 'X', true).addTransicao('0', "q5", '0', false).addTransicao('1', "q5", '1', false)
			.addTransicao('Y', "q5", 'Y', false);
		
		mt.addEstado("q6").estadoFinal();
		
		String cadeia = "110100";
		
		System.out.println(mt.verificarCadeia(cadeia));
		System.out.println(mt.gerarID(cadeia));
		
		System.out.println();
		
		System.out.println("Definição formal:\n");
		System.out.println("M = (" + mt.getEstados() + ", " + mt.getSimbolosEntrada() + ", " + mt.getSimbolosFita()
							+ ", δ, " + mt.getEstadoInicial() + ", " + mt.getSimboloBranco() + ", " + mt.getEstadosFinais() + ")");
		
		System.out.println();
		
		System.out.println(mt.getFuncaoTransicao());
		
	}
}