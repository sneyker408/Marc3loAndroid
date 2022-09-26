package com.example.reservas.services.interfaces;



	import java.util.List;

	import com.example.reservas.models.entities.Reserva;

	public interface IReservaService {
	
		public List<Reserva> findAll();
		
		public Reserva findById(Long id);
		
		public Reserva save(Reserva reserva);
		
		public void delete(Long id);
		
		public List<Reserva> isExist(Reserva reserva);

	
}
