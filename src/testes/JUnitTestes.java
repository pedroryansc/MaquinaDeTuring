package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.iplinski.automato.*;

class JUnitTestes {

	MaquinaDeTuring mt = new MaquinaDeTuring("0^(n)1^(n)", 'B');
	
	@Test
	public void testAddEstado() {
		mt.addEstado("q0");
		assertEquals("q0", mt.getEstados().get(0));
	}
	
	@Test
	public void testAddTransicao1() {
		Estado q0 = mt.addEstado("q0").addTransicao('0', "q1", 'X', true);
		Transicao t = new Transicao("q1", 'X', true);
		assertEquals(t.getEstado(), q0.getTransicoes().get('0').getEstado());
	}
	
	@Test
	public void testAddTransicao2() {
		Estado q0 = mt.addEstado("q0").addTransicao('0', "q1", 'X', true);
		Transicao t = new Transicao("q1", 'X', true);
		assertEquals(t.getSimbolo(), q0.getTransicoes().get('0').getSimbolo());
	}
	
	@Test
	public void testAddTransicao3() {
		Estado q0 = mt.addEstado("q0").addTransicao('0', "q1", 'X', true);
		Transicao t = new Transicao("q1", 'X', true);
		assertEquals(t.getDirecao(), q0.getTransicoes().get('0').getDirecao());
	}
	
	public void criarTransicoes() {
		mt.addEstado("q0").addTransicao('0', "q1", 'X', true).addTransicao('Y', "q3", 'Y', true);
		mt.addEstado("q1").addTransicao('0', "q1", '0', true).addTransicao('1', "q2", 'Y', false).addTransicao('Y', "q1", 'Y', true);
		mt.addEstado("q2").addTransicao('Y', "q2", 'Y', false).addTransicao('0', "q2", '0', false).addTransicao('X', "q0", 'X', true);
		mt.addEstado("q3").addTransicao('Y', "q3", 'Y', true).addTransicao('B', "q4", 'B', true);
	}
	
	@Test
	public void testGetSimbolosEntrada() {
		criarTransicoes();
		
		List<Character> simbolos = new ArrayList<Character>();
		simbolos.add('0');
		simbolos.add('1');
		
		assertEquals(simbolos, mt.getSimbolosEntrada());
	}
	
	@Test
	public void testGetSimbolosFita() {
		criarTransicoes();
		
		List<Character> simbolos = new ArrayList<Character>();
		simbolos.add('0');
		simbolos.add('Y');
		simbolos.add('1');
		simbolos.add('X');
		simbolos.add('B');
		
		assertEquals(simbolos, mt.getSimbolosFita());
	}
	
	@Test
	public void testGetFuncaoTransicao1() {
		mt.addEstado("q0").addTransicao('Y', "q3", 'Y', true);
		assertEquals("δ(q0, Y) = (q3, Y, D)", mt.getFuncaoTransicao().get(0));
	}
	
	@Test
	public void testGetFuncaoTransicao2() {
		mt.addEstado("q0").addTransicao('Y', "q3", 'Y', true);
		mt.getFuncaoTransicao();
		mt.addEstado("q1").addTransicao('1', "q2", 'Y', false);
		assertNotEquals("δ(q0, Y) = (q3, Y, D)", mt.getFuncaoTransicao().get(1));
	}
	
	@Test
	public void testGetEstadoInicial() {
		assertEquals(null, mt.getEstadoInicial());
	}
	
	@Test
	public void testGetEstadosFinais() {
		mt.addEstado("q3");
		mt.addEstado("q4").estadoFinal();
		assertEquals("q4", mt.getEstadosFinais().get(0));
	}
	
	public void criarMT() {
		Estado q0 = mt.addEstado("q0").addTransicao('0', "q1", 'X', true).addTransicao('Y', "q3", 'Y', true);
		mt.setEstadoInicial(q0);
		
		mt.addEstado("q1").addTransicao('0', "q1", '0', true).addTransicao('1', "q2", 'Y', false).addTransicao('Y', "q1", 'Y', true);
		
		mt.addEstado("q2").addTransicao('Y', "q2", 'Y', false).addTransicao('0', "q2", '0', false).addTransicao('X', "q0", 'X', true);
		
		mt.addEstado("q3").addTransicao('Y', "q3", 'Y', true).addTransicao('B', "q4", 'B', true);
		
		mt.addEstado("q4").estadoFinal();
	}
	
	@Test
	public void testVerificarCadeia1() {
		criarMT();
		assertTrue(mt.verificarCadeia("0000011111"));
	}
	
	@Test
	public void testVerificarCadeia2() {
		criarMT();
		assertFalse(mt.verificarCadeia("001"));
	}
	
	@Test
	public void testVerificarCadeia3() {
		criarMT();
		assertFalse(mt.verificarCadeia("010101"));
	}
	
	@Test
	public void testGerarID1() {
		criarMT();
		String id = "(B\033[1mq0\033[0m01B) -> (BX\033[1mq1\033[0m1B) -> (B\033[1mq2\033[0mXYB) -> (BX\033[1mq0\033[0mYB) -> (BXY\033[1mq3\033[0mB) -> (BXYB\033[1mq4\033[0m) -> Aceita";
		assertEquals(id, mt.gerarID("01"));
	}
	
	@Test
	public void testGerarID2() {
		criarMT();
		String id = "(B\033[1mq0\033[0m0B) -> (BX\033[1mq1\033[0mB) -> Não aceita";
		assertEquals(id, mt.gerarID("0"));
	}

}
