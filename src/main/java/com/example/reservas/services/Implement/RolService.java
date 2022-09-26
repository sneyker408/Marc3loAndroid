package com.example.reservas.services.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.reservas.models.entities.Rol;
import com.example.reservas.models.dao.IRoleDAO;
import com.example.reservas.services.interfaces.IRoleService;

@Service
public class RolService implements  IRoleService{
	
	@Autowired
	private IRoleDAO repository;

	@Override
	public List<Rol> getAll() {

		return repository.findAll();
	}

	@Override
	public Rol getById(Integer id) {
	
		return repository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El rol no existe"));
	}

	@Override
	public Rol save(Rol rol) {
		List<Rol> isExists = repository.findByNombre(rol.getNombre());
		
		if(isExists.size() > 0) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "El rol con nombre " + rol.getNombre() + " ya existe");
		}
		
		return repository.save(rol);
	}

	@Override
	public Rol update(Rol rol, Integer id) {
		Rol rolUpdate = getById(id);
		
		rolUpdate.setNombre(rol.getNombre());
		
		return repository.save(rolUpdate);
	}

	@Override
	public void delete(Integer id) {
		Rol rolDelete = getById(id);
		
		repository.delete(rolDelete);
		
	}

}
