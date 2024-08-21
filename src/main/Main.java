package main;

import automato.*;

public class Main {
	public static void main(String[] args) {
		
		MaquinaDeTuring mt = new MaquinaDeTuring("0^(n)1^(n)", 'B');
		
		Estado q0 = mt.addEstado("q0").addTransicao('0', "q1", 'X', true).addTransicao('Y', "q3", 'Y', true);
		mt.setEstadoInicial(q0);
		
		mt.addEstado("q1").addTransicao('0', "q1", '0', true).addTransicao('1', "q2", 'Y', false).addTransicao('Y', "q1", 'Y', true);
		
		mt.addEstado("q2").addTransicao('Y', "q2", 'Y', false).addTransicao('0', "q2", '0', false).addTransicao('X', "q0", 'X', true);
		
		mt.addEstado("q3").addTransicao('Y', "q3", 'Y', true).addTransicao('B', "q4", 'B', true);
		
		mt.addEstado("q4").estadoFinal();
		
		String cadeia = "0011";
		
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