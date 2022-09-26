package com.example.reservas.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.reservas.models.entities.Detalle_Reserva;

public interface IDetalle_ReservaDAO extends CrudRepository<Detalle_Reserva,Long>{
	
	@Query("FROM Detalle_Reserva c WHERE c.id=:#{#detalle_reserva.id}")
	List<Detalle_Reserva> findById(Detalle_Reserva detalle_reserva);

}
