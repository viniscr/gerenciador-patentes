package br.edu.univasf.patentes.model;

public enum Vinculacao {
	UNIVASF("Univasf"), EXTERNO("Externo");
	
	private String descricao;
	
	Vinculacao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
