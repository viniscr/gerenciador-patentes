package br.edu.univasf.patentes.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.univasf.patentes.model.Imagem;
import br.edu.univasf.patentes.repository.Imagens;
import br.edu.univasf.patentes.service.NegocioException;
import br.edu.univasf.patentes.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RemoveImagemBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Imagens imagens;
	
	private Imagem imagemSelecionada;

	public void excluir() {
		try {
			imagens.removerImagem(imagemSelecionada);
			
			FacesUtil.addInfoMessage("Imagem " + imagemSelecionada.getDescricao()
					+ " excluída com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public Imagem getImagemSelecionada() {
		return imagemSelecionada;
	}

	public void setImagemSelecionada(Imagem imagemSelecionada) {
		this.imagemSelecionada = imagemSelecionada;
	}

}
