package br.edu.univasf.patentes.repository.filter;

import java.io.Serializable;

import br.edu.univasf.patentes.model.Usuario;

public class PatenteFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
