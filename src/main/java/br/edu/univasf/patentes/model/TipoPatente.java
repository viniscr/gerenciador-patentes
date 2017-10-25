package br.edu.univasf.patentes.model;

public enum TipoPatente {
	PI("Patente de Invenção"), MU("Modelo de Utilidade");
	
	private String descricao;
	
	TipoPatente(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
