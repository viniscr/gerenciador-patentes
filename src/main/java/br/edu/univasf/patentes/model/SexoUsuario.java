package br.edu.univasf.patentes.model;

public enum SexoUsuario {
	MASCULINO("Masculino"), FEMININO("Feminino");
	
	private String descricao;
	
	SexoUsuario(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
