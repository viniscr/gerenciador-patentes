package br.edu.univasf.patentes.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.univasf.patentes.model.Grupo;
import br.edu.univasf.patentes.model.Usuario;
import br.edu.univasf.patentes.repository.Usuarios;
import br.edu.univasf.patentes.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
		Usuario usuario = usuarios.porEmail(email);
		
		UsuarioSistema user = null;
		
		if(usuario != null){
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for(Grupo grupo: usuario.getGrupos()){
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return authorities;
	}

}
