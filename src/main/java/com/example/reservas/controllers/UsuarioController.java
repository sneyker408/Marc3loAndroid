
package com.example.reservas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.reservas.models.entities.Usuario;
import com.example.reservas.services.interfaces.IUsuarioService;

@RestController
@CrossOrigin()
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {
		return new ResponseEntity<List<Usuario>>(usuarioService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id ) {
		return new ResponseEntity<Usuario>(usuarioService.getById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
		return new ResponseEntity<Usuario>(usuarioService.save(usuario), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Integer id) {
		return new ResponseEntity<Usuario>(usuarioService.update(usuario, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		usuarioService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
/*
	/*
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
	*
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

	import com.example.reservas.models.entities.Usuario;
	import com.example.reservas.services.interfaces.IUsuarioService;
	
	@CrossOrigin(origins = "*.*")
	@RestController
	@RequestMapping("/api")
	public class UsuarioController {}
	/*	
		@Autowired
		private IUsuarioService usuarioService;
		
		@GetMapping("/usuarios")
		public List<Usuario> getAll(){
			return usuarioService.findAll();			
		}
		@GetMapping("/usuarios/{id}")
		public ResponseEntity<?> getById(@PathVariable Long id) {
			Usuario usuario = null;
			Map<String, Object> response = new HashMap<>();
			try {
				usuario = usuarioService.(id);
			}catch(DataAccessException e) {
				response.put("message", "Error al realizar la consulta en la base de datos");
				response.put("error", e.getMessage());
			}
			if(usuario == null){
				response.put("message", "El Usuario con ID".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
		}
		
		//METODO POST
		
		@PostMapping("/usuarios")
		public ResponseEntity<?> save(@RequestBody Usuario usuario){
			Map<String, Object> response = new HashMap<>();
			try {
				if(usuarioService.isExist(usuario).size()>0) {
					response.put("message", "Este Usuario ya esta registrado");
					return new ResponseEntity<Map<String , Object>>(response, HttpStatus.CONFLICT);
				}else {
					usuarioService.save(usuario);
				}
				
			}catch(DataAccessException e) {
				response.put("message", "Error al insertar el Usuario en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "Usuario registrado con exito...");
			response.put("usuarios", usuario);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
		
		//METODO PUT
		@PutMapping("/usuarios/{id}")
		public ResponseEntity<?> update(@RequestBody Usuario role, @PathVariable Long id){
		
			Usuario usuarioActual = usuarioService.findById(id);
			Usuario usuarioUpdated = null;
			Map<String, Object> response = new HashMap<>();
			if(usuarioActual == null) {
				response.put("message", "Error: no se puede editar, el Usuario ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
			try {
				usuarioActual.setNombre(role.getNombre());
				usuarioUpdated = usuarioService.save(usuarioActual);
			}catch(DataAccessException e) {
				response.put("message", "Error al actualizar el Usuario");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "Usuario actualizado con exito...");
			response.put("usuarios", usuarioUpdated);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
		//METODO DELETE 
		@DeleteMapping("/usuarios/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id){
			Map<String, Object> response = new HashMap<>();
			
			try {
				usuarioService.delete(id);
			}catch(DataAccessException e) {
				response.put("message", "Error al Eliminar el Usuario de la base de datos");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "El Usuario ha sido eliminada...");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
		
	}
*/
