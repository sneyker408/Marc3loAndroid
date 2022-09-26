package com.example.reservas.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.reservas.models.entities.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente,Long>{
	
	@Query("FROM Cliente c WHERE c.nombre=:#{#cliente.nombre}")
	List<Cliente> findByNombreDireccion(Cliente cliente);
	

}
