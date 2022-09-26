package com.example.reservas.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reservas.models.entities.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
	
	public long countByCorreo(String correo);
	
	public Usuario findByCorreoAndPassword(String correo, String password);
	
	public Usuario findByCorreo(String correo);

}



