package com.example.reservas.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reservas.models.entities.Rol;

@Repository
public interface IRoleDAO extends JpaRepository<Rol, Integer> {
	
	public List<Rol> findByNombre(String nombre);

}


