package com.example.reservas.services.interfaces;

import java.util.List;

import com.example.reservas.models.entities.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> getAll();
	
	public Usuario getById(Integer id);
	
	public Usuario save(Usuario usuario);
	
	public Usuario update(Usuario usuario, Integer integer);
	
	public void delete(Integer id);
	
	public Usuario getByCorreo(String correo);

}
