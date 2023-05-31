package com.bolsadeideas.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Rol;
import com.bolsadeideas.springboot.app.models.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	
	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null)
		{
			logger.error("Error login: no existe el usuario '" + username + "'");
			throw new UsernameNotFoundException("Username " + username + " no existe en el sistema!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Rol rol: usuario.getRoles())
		{
			authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
		}
		
		if(authorities.isEmpty())
		{
			logger.error("Error login: usuario '" + username + "' no tiene rol asignado!");
			throw new UsernameNotFoundException("Error login: usuario \" + username + \" no tiene rol asignado!");
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

}
