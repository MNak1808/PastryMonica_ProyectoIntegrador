package com.pastry.dao.api;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.pastry.model.Producto;
import com.pastry.model.Usuario;

public interface UsuarioDAOAPI extends JpaRepository<Usuario, Long>,CrudRepository<Usuario, Long>{
	Usuario findByUsuario(String usuario);
}
