package br.edu.univasf.patentes.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.edu.univasf.patentes.service.NegocioException;

import br.edu.univasf.patentes.model.FuncaoUsuario;
import br.edu.univasf.patentes.model.Grupo;
import br.edu.univasf.patentes.model.SexoUsuario;
import br.edu.univasf.patentes.model.Universidade;
import br.edu.univasf.patentes.model.Usuario;
import br.edu.univasf.patentes.model.Vinculacao;
import br.edu.univasf.patentes.repository.Grupos;
import br.edu.univasf.patentes.repository.Universidades;
import br.edu.univasf.patentes.service.CadastroUsuarioService;
import br.edu.univasf.patentes.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	@Inject
	private Usuario usuario;
	@Inject
	private Grupos grupos;
	
	private Grupo grupo;
	private List<Grupo> listaUsuario = new ArrayList<>();
	private String confirmaSenha;
	 
	public CadastroUsuarioBean() {
		limpar();
	}
	
	public void inicializar(){
		if(FacesUtil.isNotPostback()){
			this.grupo = this.grupos.porNome("Autores");
			this.listaUsuario.add(grupo);
		}
	}
	
	private void limpar(){
		usuario = new Usuario();
	}

	public void salvar() {
		try{
			this.usuario.setGrupos(listaUsuario);
			this.usuario = cadastroUsuarioService.salvar(this.usuario);
			if(!isEditando()){
				limpar();
			}
			FacesUtil.addInfoMessage("Usuário salvo com sucesso");
		}catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
	}
	
	public void cadastrar(){
		try{
			if(comparaSenhas(this.usuario.getSenha(), this.confirmaSenha)){
				this.usuario.setGrupos(listaUsuario);
				this.usuario = cadastroUsuarioService.salvar(this.usuario);
				limpar();
				FacesUtil.addInfoMessage("Usuário cadastrado com sucesso");
			}else{
				FacesUtil.addErrorMessage("As senhas informadas são diferentes!");
			}
		}catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
					
		}
	
	public boolean isEditando(){
		return this.usuario.getId() != null;
	}
	
	public FuncaoUsuario[] getFuncoesUsuario(){
		return FuncaoUsuario.values();
	}
	
	public SexoUsuario[] getSexosUsuario(){
		return SexoUsuario.values();
	}
	
	public Vinculacao[] getVinculos(){
		return Vinculacao.values();
	}
	
	public boolean comparaSenhas(String senha, String confirmaSenha){
		if(StringUtils.equals(senha, confirmaSenha)){
			return true;
		}
		return false;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	}
