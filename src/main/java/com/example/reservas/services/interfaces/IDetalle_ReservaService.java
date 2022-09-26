package com.example.reservas.services.interfaces;

	import java.util.List;

	import com.example.reservas.models.entities.Detalle_Reserva;

	public interface IDetalle_ReservaService {
	
		public List<Detalle_Reserva> findAll();
		
		public Detalle_Reserva findById(Long id);
		
		public Detalle_Reserva save(Detalle_Reserva detalle_reserva);
		
		public void delete(Long id);
		
		public List<Detalle_Reserva> isExist(Detalle_Reserva detalle_reserva);

	
}

