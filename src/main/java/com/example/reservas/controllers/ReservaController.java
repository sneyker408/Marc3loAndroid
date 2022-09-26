package com.example.reservas.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservas.models.entities.Reserva;
import com.example.reservas.services.interfaces.IReservaService;

@CrossOrigin(origins = "*.*")
@RestController
@RequestMapping("/api")
public class ReservaController {
	
	@Autowired
	private IReservaService reservaService;
	
	@GetMapping("/reservas")
	public List<Reserva> getAll(){
		return reservaService.findAll();			
	}
	@GetMapping("/reservas/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Reserva reserva = null;
		Map<String, Object> response = new HashMap<>();
		try {
			reserva = reservaService.findById(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
		}
		if(reserva == null){
			response.put("message", "la reserva con ID".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<Reserva>(reserva,HttpStatus.OK);
	}
	//METODO POST
	
	@PostMapping("/reservas")
	public ResponseEntity<?> save(@RequestBody Reserva reserva){
		Map<String, Object> response = new HashMap<>();
		try {
			if(reservaService.isExist(reserva).size()>0) {
				response.put("message", "Esta reserva ya esta registrado");
				return new ResponseEntity<Map<String , Object>>(response, HttpStatus.CONFLICT);
			}else {
				reservaService.save(reserva);
			}
			
		}catch(DataAccessException e) {
			response.put("message", "Error al insertar reserva en la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("message", "Reserva registrado con exito...");
		response.put("reserva", reserva);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	//METODO PUT
	@PutMapping("/reservas/{id}")
	public ResponseEntity<?> update(@RequestBody Reserva reserva, @PathVariable Long id){
	
		Reserva reservaActual = reservaService.findById(id);
		Reserva reservaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if(reservaActual == null) {
			response.put("message", "Error: no se puede editar, la reserva ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			reservaActual.setCreate_dt(reserva.getCreate_dt());
			reservaActual.setEstado(reserva.getEstado());
			reservaUpdated = reservaService.save(reservaActual);
		}catch(DataAccessException e) {
			response.put("message", "Error al actualizar la reserva");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("message", "Reserva actualizada con exito...");
		response.put("reserva", reservaUpdated);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	//METODO DELETE 
	@DeleteMapping("/reservas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
		try {
			reservaService.delete(id);
		}catch(DataAccessException e) {
			response.put("message", "Error al Eliminar la reserva de la base de datos");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("message", "la reserva ha sido eliminada...");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
}
