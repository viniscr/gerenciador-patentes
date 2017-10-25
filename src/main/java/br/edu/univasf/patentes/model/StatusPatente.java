package br.edu.univasf.patentes.model;

public enum StatusPatente {
	
	EDICAO("Em edição"), FINALIZADA("Finalizada");
	
	private String descricao;
	
	StatusPatente(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
