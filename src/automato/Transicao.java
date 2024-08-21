package automato;

public class Transicao {

	private String estado;
	private char simbolo;
	private boolean direcao;
	
	public Transicao(String estado, char simbolo, boolean direcao) {
		setEstado(estado);
		setSimbolo(simbolo);
		setDirecao(direcao);
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public char getSimbolo() {
		return simbolo;
	}
	
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
	
	public boolean getDirecao() {
		return direcao;
	}
	
	public void setDirecao(boolean direcao) {
		this.direcao = direcao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transicao [estado=");
		builder.append(estado);
		builder.append(", simbolo=");
		builder.append(simbolo);
		builder.append(", direcao=");
		builder.append(direcao);
		builder.append("]");
		return builder.toString();
	}
	
}