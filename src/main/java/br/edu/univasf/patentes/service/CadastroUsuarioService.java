package br.edu.univasf.patentes.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.univasf.patentes.model.Usuario;
import br.edu.univasf.patentes.repository.Usuarios;
import br.edu.univasf.patentes.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario){
		
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
		
		if(usuarioExistente != null && ! usuarioExistente.equals(usuario)){
			throw new NegocioException("Já existe um usuario com o email Informado.");
		}
		
		return usuarios.guardar(usuario);
	}


}
