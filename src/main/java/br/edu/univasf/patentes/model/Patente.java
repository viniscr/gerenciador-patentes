package br.edu.univasf.patentes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="patente")
public class Patente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Usuario usuario;
	private List<Usuario> autores = new ArrayList<>();
	private String titulo;
	private String resumo;
	private String relatorio;
	private String reivindicacoes;
	private List<Imagem> imagens = new ArrayList<>();
	private TipoPatente tipo;
	private StatusPatente status = StatusPatente.EDICAO;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(columnDefinition="text", nullable = false)
	public String getResumo() {
		return resumo;
	}
	
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	
	@Column(columnDefinition="text", nullable = false)
	public String getRelatorio() {
		return relatorio;
	}
	
	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}
	
	@Column(columnDefinition="text", nullable = false)
	public String getReivindicacoes() {
		return reivindicacoes;
	}
	
	public void setReivindicacoes(String reivindicacoes) {
		this.reivindicacoes = reivindicacoes;
	}
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="usuario_id", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Column(nullable = false, length = 50)
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
	@JoinTable(name = "patente_imagem", joinColumns = @JoinColumn(name="patente_id"),
			inverseJoinColumns = @JoinColumn(name = "imagem_id"))
	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="status", length = 15)
	public StatusPatente getStatus() {
		return status;
	}

	public void setStatus(StatusPatente status) {
		this.status = status;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="tipo", length= 10)
	public TipoPatente getTipo() {
		return tipo;
	}

	public void setTipo(TipoPatente tipo) {
		this.tipo = tipo;
	}
	
	@ManyToMany
	@JoinTable(name = "autor_patente", joinColumns = @JoinColumn(name="patente_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	public List<Usuario> getAutores() {
		return autores;
	}

	public void setAutores(List<Usuario> autores) {
		this.autores = autores;
	}
	
	@Transient
	public boolean isNovo() {
		return getId() == null;
	}
	
	@Transient
	public boolean isExistente() {
		return !isNovo();
	}
	
	@Transient
	public boolean isEdicao() {
		return StatusPatente.EDICAO.equals(this.getStatus());
	}
	
	@Transient
	public boolean isFinalizada() {
		return StatusPatente.FINALIZADA.equals(this.getStatus());
	}

	@Transient
	public boolean isNaoFinalizavel() {
		return !this.isFinalizavel();
	}

	@Transient
	public boolean isFinalizavel() {
		return this.isExistente() && this.isEdicao();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patente other = (Patente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
