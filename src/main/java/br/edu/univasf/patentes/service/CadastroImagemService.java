package br.edu.univasf.patentes.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.univasf.patentes.model.Imagem;
import br.edu.univasf.patentes.repository.Imagens;
import br.edu.univasf.patentes.util.jpa.Transactional;

public class CadastroImagemService implements Serializable {

	private static final long serialVersionUID = 1L;
		
	@Inject
	private Imagens imagens;
	
	@Transactional
	public Imagem salvarImagem(Imagem imagem){
		return imagens.guardar(imagem);
	}

	
}