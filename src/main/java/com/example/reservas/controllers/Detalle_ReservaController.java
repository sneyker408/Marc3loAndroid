package com.example.reservas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservas.models.entities.Detalle_Reserva;
import com.example.reservas.services.interfaces.IDetalle_ReservaService;
	
	@CrossOrigin(origins = "*.*")
	@RestController
	@RequestMapping("/api")
	public class Detalle_ReservaController {

		@Autowired
		private IDetalle_ReservaService detalle_reservaService;
		
		@GetMapping("/detalle_reservas")
		public List<Detalle_Reserva> getAll(){
			return detalle_reservaService.findAll();			
		}
		//PRIMER METODO GET (PARA MOSTRAR)
		@GetMapping("/detalle_reservas/{id}")
		public ResponseEntity<?> getById(@PathVariable Long id) {
			Detalle_Reserva detalle_reserva = null;
			Map<String, Object> response = new HashMap<>();
			try {
				detalle_reserva = detalle_reservaService.findById(id);
			}catch(DataAccessException e) {
				response.put("message", "Error al realizar la consulta en la base de datos");
				response.put("error", e.getMessage());
			}
			if(detalle_reserva == null){
				response.put("message", "El detalle reserva con ID".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<Detalle_Reserva>(detalle_reserva,HttpStatus.OK);
		}
		//SEGUNDO METODO POST (PARA INSERTAR)
		@PostMapping("/detalle_reservas")
		public ResponseEntity<?> save(@RequestBody Detalle_Reserva detalle_reserva){
			Map<String, Object> response = new HashMap<>();
			try {
				if(detalle_reservaService.isExist(detalle_reserva).size()>0) {
					response.put("message", "Este detalle ya esta registrado");
					return new ResponseEntity<Map<String , Object>>(response, HttpStatus.CONFLICT);
				}else {
					detalle_reservaService.save(detalle_reserva);
				}
				
			}catch(DataAccessException e) {
				response.put("message", "Error al insertar registro en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "Producto registrado con exito...");
			response.put("detalle_reserva", detalle_reserva);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
		//TERCER METODO PUT

		@PutMapping("/detalle_reservas/{id}")
		public ResponseEntity<?> update(@RequestBody Detalle_Reserva detalle_reserva, @PathVariable Long id){
		
			Detalle_Reserva detalle_reservaActual = detalle_reservaService.findById(id);
			Detalle_Reserva detalle_reservaUpdated = null;
			Map<String, Object> response = new HashMap<>();
			if(detalle_reservaActual == null) {
				response.put("message", "Error: no se puede editar, el Detallereserva ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
			try {
				detalle_reservaActual.setProducto(detalle_reserva.getProducto());
				detalle_reservaActual.setReserva(detalle_reserva.getReserva());
				detalle_reservaUpdated = detalle_reservaService.save(detalle_reservaActual);
			}catch(DataAccessException e) {
				response.put("message", "Error al actualizar el producto");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "detalle_reserva actualizado con exito...");
			response.put("detalle_reserva", detalle_reservaUpdated);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}

}
