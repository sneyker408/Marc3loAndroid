package com.example.reservas.services.interfaces;
	
import java.util.List;

import com.example.reservas.models.entities.Rol;

public interface  IRoleService{
	
	public List<Rol> getAll();
	
	public Rol getById(Integer id);
	
	public Rol save(Rol rol);
	
	public Rol update(Rol rol, Integer id);
	
	public void delete(Integer id);

}


