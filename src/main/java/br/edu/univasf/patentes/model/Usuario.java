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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.univasf.patentes.model.Usuario;

import br.edu.univasf.patentes.model.Grupo;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String qualificacao;
	private String documento;
	private String endereco;
	private String cep;
	private Vinculacao vinculo;
	private String telefone;
	private String fax;
	private List<Grupo> grupos = new ArrayList<>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank
	@Column(nullable = false, length = 80)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotBlank
	@Column(nullable = false, unique = true, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotBlank
	@Column(nullable = false, length = 20)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@NotBlank
	@Column(name="telefone", nullable = false, length = 15)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name="usuario_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	@NotBlank
	@Column(name="qualificacao", length = 50)
	public String getQualificacao() {
		return qualificacao;
	}

	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}
	
	@NotBlank
	@Column(name="documento", length = 20)
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	@NotBlank
	@Column(name="endereco", length = 70)
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@NotBlank
	@Column(name="cep", length = 10)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="vinculo", length = 7)
	public Vinculacao getVinculo() {
		return vinculo;
	}

	public void setVinculo(Vinculacao vinculo) {
		this.vinculo = vinculo;
	}
	
	@Column(name="fax", length = 15)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Transient
	public boolean isNovo(){
		return getId() == null;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}
	
	
	

}
