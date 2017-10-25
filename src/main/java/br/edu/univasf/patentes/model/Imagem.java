package br.edu.univasf.patentes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="imagem")
public class Imagem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private byte[] imagem;
	private String descricao;
	private List<Patente> patentes;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Lob
	@NotNull
	@Column(columnDefinition = "LONGBLOB")
	public byte[] getImagem() {
		return imagem;
	}
	
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	@NotNull
	@Column(length = 50)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "patente_imagem", joinColumns = @JoinColumn(name = "imagem_id"), inverseJoinColumns = @JoinColumn(name = "patente_id"))
	public List<Patente> getPatentes() {
		return patentes;
	}
	
	public void setPatentes(List<Patente> patentes) {
		this.patentes = patentes;
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
		Imagem other = (Imagem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
