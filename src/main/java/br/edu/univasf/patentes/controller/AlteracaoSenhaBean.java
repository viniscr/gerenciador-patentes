package br.edu.univasf.patentes.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.edu.univasf.patentes.model.Usuario;
import br.edu.univasf.patentes.service.CadastroUsuarioService;
import br.edu.univasf.patentes.service.NegocioException;
import br.edu.univasf.patentes.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AlteracaoSenhaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;

	@Inject
	private Usuario usuario;
	
	private String senhaAtual;
	private String novaSenha;
	private String confirmaNovaSenha;

	public AlteracaoSenhaBean(){
		usuario = new Usuario();
	}
	
	private void limpar(){
		this.senhaAtual = "";
		this.novaSenha = "";
		this.confirmaNovaSenha = "";
	}
	
	public void salvar() {
		try{
			if(verificaSenhaAtual(senhaAtual) && comparaNovasSenhas(novaSenha, confirmaNovaSenha)){
				this.usuario.setSenha(novaSenha);
				this.usuario = cadastroUsuarioService.salvar(this.usuario);
				FacesUtil.addInfoMessage("Senha alterada com sucesso");
				limpar();
			}else{
				throw new NegocioException("Erro ao alterar senha.");
			}
		}catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public boolean verificaSenhaAtual(String senhaInformada){
		if(StringUtils.equals(senhaInformada, this.usuario.getSenha())){
			return true;
		}
		
		FacesUtil.addErrorMessage("A senha informada não confere com sua senha atual");
		return false;
	}
	
	public boolean comparaNovasSenhas(String novaSenha, String confirmaNovaSenha){
		if(StringUtils.equals(novaSenha, confirmaNovaSenha)){
			return true;
		}
		
		FacesUtil.addErrorMessage("As novas senhas informadas são diferentes!");
		return false;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmaNovaSenha() {
		return confirmaNovaSenha;
	}

	public void setConfirmaNovaSenha(String confirmaNovaSenha) {
		this.confirmaNovaSenha = confirmaNovaSenha;
	}
	
	
}
