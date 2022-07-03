package com.pastry.ecom.serviceImpl;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pastry.ecom.dto.UsuarioDTO;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioDTO usuario=usuarioService.buscarByUsuario(username);
		System.out.println(usuario);
		if(usuario==null){
            throw new UsernameNotFoundException(username);
        }
        
        var rol=new ArrayList<GrantedAuthority>();
        session.setAttribute("idusuario", usuario.getCodigo());
        if(usuario.getId_tipo()==1) {
        	rol.add(new SimpleGrantedAuthority("ADMIN"));
        }else if(usuario.getId_tipo()==2) {
        	rol.add(new SimpleGrantedAuthority("CLIENTE"));
        }
		
		
        return new User(usuario.getUsuario(),usuario.getPass(),rol);
	}
	
	

}
