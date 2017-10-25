package br.edu.univasf.patentes.model;

public enum FuncaoUsuario {
	
	PROFESSOR("Professor"), FUNCIONARIO("Funcionário"), ESTUDANTE("Estudante");
	
	private String descricao;
	
	FuncaoUsuario(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
