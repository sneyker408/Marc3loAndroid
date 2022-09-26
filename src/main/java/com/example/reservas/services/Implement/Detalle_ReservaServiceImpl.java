package com.example.reservas.services.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservas.models.dao.IDetalle_ReservaDAO;
import com.example.reservas.models.entities.Detalle_Reserva;
import com.example.reservas.services.interfaces.IDetalle_ReservaService;

	
	@Service
	public class Detalle_ReservaServiceImpl implements IDetalle_ReservaService{

		// Estamos haciendo una inyeccion
		@Autowired
		private IDetalle_ReservaDAO detalle_reservaDAO;
		
		@Override
		public List<Detalle_Reserva> findAll() {
			return (List<Detalle_Reserva>)detalle_reservaDAO.findAll();
		}

		@Override
		public Detalle_Reserva findById(Long id) {
			return detalle_reservaDAO.findById(id).orElse(null);
		}

		@Override
		@Transactional
		public Detalle_Reserva save(Detalle_Reserva detalle_reserva) {
			return detalle_reservaDAO.save(detalle_reserva);
		}

		@Override
		@Transactional
		public void delete(Long id) {
			detalle_reservaDAO.deleteById(id);
		}

		@Override
		public List<Detalle_Reserva> isExist(Detalle_Reserva detalle_reserva) {
			return detalle_reservaDAO.findById(detalle_reserva);
		}

}

