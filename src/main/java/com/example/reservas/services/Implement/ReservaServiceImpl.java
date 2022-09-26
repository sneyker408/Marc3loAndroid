package com.example.reservas.services.Implement;

	import java.util.ArrayList;
import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;

import com.example.reservas.models.dao.IDetalle_ReservaDAO;
import com.example.reservas.models.dao.IReservaDAO;
import com.example.reservas.models.entities.Detalle_Reserva;
import com.example.reservas.models.entities.Reserva;
	import com.example.reservas.services.interfaces.IReservaService;

	@Service
	public class ReservaServiceImpl implements IReservaService{

		// Estamos haciendo una inyeccion
		@Autowired
		private IReservaDAO reservaDAO;
		
		@Autowired
		private IDetalle_ReservaDAO detalleDao;
		
		@Override
		public List<Reserva> findAll() {
			return (List<Reserva>)reservaDAO.findAll();
		}

		@Override
		public Reserva findById(Long id) {
			return reservaDAO.findById(id).orElse(null);
		}

		@Override
		@Transactional
		public Reserva save(Reserva reserva) {
			List<Detalle_Reserva> detalles = reserva.getDetalle_reserva();
			
			reserva.setDetalle_reserva(new ArrayList<Detalle_Reserva>());
			
			Reserva reservaPersisted = reservaDAO.save(reserva);
			
			for(Detalle_Reserva detalle : detalles) {
				detalle.setReserva(reservaPersisted);
			}
			
			detalleDao.saveAll(detalles);
			
			return reservaPersisted;
		}

		@Override
		@Transactional
		public void delete(Long id) {
			reservaDAO.deleteById(id);
		}

		@Override
		public List<Reserva> isExist(Reserva reserva) {
			return reservaDAO.findByIdCreate_dt(reserva);
		}

}
