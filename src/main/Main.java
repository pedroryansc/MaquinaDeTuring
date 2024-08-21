package main;

import automato.Estado;
import automato.MaquinaDeTuring;

public class Main {
	public static void main(String[] args) {
		
		MaquinaDeTuring mt = new MaquinaDeTuring('B');
		
		Estado q0 = mt.addEstado("q0").addTransicao('0', "q1", 'X', 'D')
			.addTransicao('Y', "q3", 'Y', 'D');
		mt.setEstadoInicial(q0);
		
		mt.addEstado("q1").addTransicao('0', "q1", '0', 'D')
			.addTransicao('1', "q2", 'Y', 'E')
			.addTransicao('Y', "q1", 'Y', 'D');
		
		mt.addEstado("q2").addTransicao('Y', "q2", 'Y', 'E')
			.addTransicao('0', "q2", '0', 'E')
			.addTransicao('X', "q0", 'X', 'D');
		
		mt.addEstado("q3").addTransicao('Y', "q3", 'Y', 'D')
			.addTransicao('B', "q4", 'B', 'D');
		
		mt.addEstado("q4").estadoFinal();
		
		mt.verificarCadeia("0011");
		
	}
}