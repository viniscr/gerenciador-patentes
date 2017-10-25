package br.edu.univasf.patentes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.edu.univasf.patentes.model.Imagem;
import br.edu.univasf.patentes.model.Patente;
import br.edu.univasf.patentes.model.TipoPatente;
import br.edu.univasf.patentes.model.Usuario;
import br.edu.univasf.patentes.repository.Imagens;
import br.edu.univasf.patentes.repository.Usuarios;
import br.edu.univasf.patentes.security.UsuarioSistema;

import br.edu.univasf.patentes.service.CadastroPatenteService;
import br.edu.univasf.patentes.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroPatenteBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroPatenteService cadastroPatenteService;
	@Inject
	private Imagens imagens;
	@Inject
	private Usuarios usuarios;
	
	@Produces
	@PatenteEdicao
	private Patente patente;
	
	private Imagem imagem;
	private Imagem imagemSelecionada;
	private Usuario autorAdicionado;
	private List<Imagem> listaImagens = new ArrayList<>();
	private List<Usuario> listaAutores = new ArrayList<>();
	
	public CadastroPatenteBean() {
		limpar();
	}
	
	private void limpar() {
		this.imagem = new Imagem();
		this.patente = new Patente();
		this.patente.setUsuario(getUsuarioLogado().getUsuario());
		
		if (this.patente.getAutores().isEmpty()){
			this.patente.getAutores().add(getUsuarioLogado().getUsuario());
		}
		
		this.listaAutores = this.patente.getAutores();
		//this.listaImagens = this.imagens.porPatente(this.patente.getId());
	}
	
	public void salvar() {
		this.patente.setAutores(this.patente.getAutores());
		this.patente.setImagens(this.patente.getImagens());
		this.patente = cadastroPatenteService.salvar(this.patente);
		if(!isEditando()){
			limpar();
		}
		FacesUtil.addInfoMessage("Patente salva com sucesso!"); 
	}
	
	public void adiconarImagem(FileUploadEvent event) {
		UploadedFile file = event.getFile();
		System.out.println(file.getFileName());
		this.imagem.setImagem(file.getContents());
		this.imagem.setDescricao(file.getFileName());
		this.patente.getImagens().add(this.imagem);
		
		FacesUtil.addInfoMessage("Imagem Adicionada com Sucesso!");
	}
	
	public void removeImagem(){
		try{
			imagens.removerImagem(imagemSelecionada);
			this.patente.getImagens().remove(imagemSelecionada);
			this.patente = cadastroPatenteService.salvar(this.patente);
			FacesUtil.addInfoMessage("Imagem removida com sucesso!");
		}catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao remover imagem.");
		}
	}
	
	public void adicionarAutor(){
		System.out.println(this.patente.getAutores().contains(autorAdicionado));
		if(!this.patente.getAutores().contains(this.autorAdicionado)){
			this.patente.getAutores().add(autorAdicionado);
		}else{
			FacesUtil.addErrorMessage("Este autor já foi adicionado anteriormente");
		}
	}
	
	public Patente getPatente() {
		return patente;
	}
	
	public void setPatente(Patente patente) {
		this.patente = patente;
		
	}
	
	public Imagens getImagens() {
		return imagens;
	}

	public void setImagens(Imagens imagens) {
		this.imagens = imagens;
	}
	
	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public List<Imagem> getListaImagens() {
		return listaImagens;
	}

	public void setListaImagens(List<Imagem> listaImagens) {
		this.listaImagens = listaImagens;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	
	public Usuario getAutorAdicionado() {
		return autorAdicionado;
	}

	public void setAutorAdicionado(Usuario autorAdicionado) {
		this.autorAdicionado = autorAdicionado;
	}

	public List<Usuario> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<Usuario> listaAutores) {
		this.listaAutores = listaAutores;
	}
	
	public Imagem getImagemSelecionada() {
		return imagemSelecionada;
	}

	public void setImagemSelecionada(Imagem imagemSelecionada) {
		this.imagemSelecionada = imagemSelecionada;
	}

	public TipoPatente[] getTiposPatente(){
		return TipoPatente.values();
	}
	
	public boolean isEditando() {
		return this.patente.getId() != null;
	}
	
	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
				
		return usuario;
	}


}
