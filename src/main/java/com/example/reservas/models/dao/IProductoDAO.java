package com.example.reservas.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.reservas.models.entities.Producto;
	
public interface IProductoDAO extends CrudRepository<Producto,Long>{
		
	@Query("FROM Producto c WHERE c.nombre=:#{#producto.nombre}")
	List<Producto>findByNombreMarca(Producto producto);

}
