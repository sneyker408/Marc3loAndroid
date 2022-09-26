	package com.example.reservas.models.dao;

	import java.util.List;

	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.CrudRepository;

	import com.example.reservas.models.entities.Reserva;

	public interface IReservaDAO extends CrudRepository<Reserva,Long>{
		
		@Query("FROM Reserva c WHERE c.id=:#{#reserva.id}")
		List<Reserva> findByIdCreate_dt(Reserva reserva);
		

}
