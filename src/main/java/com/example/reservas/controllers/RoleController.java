	package com.example.reservas.controllers;
/*
	import java.util.HashMap;
	import java.util.List;
	import java.util.Map;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.DataAccessException;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	*/
	import org.springframework.web.bind.annotation.CrossOrigin;
	/*
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
*/
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;
/*
	import com.example.reservas.models.entities.Rol;
	import com.example.reservas.services.interfaces.IRoleService;
*/
	@CrossOrigin(origins = "*.*")
	@RestController
	@RequestMapping("/api")
	public class RoleController {}
		/*
		@Autowired
		private IRoleService roleService;
		
		@GetMapping("/roles")
		public List<Rol> getAll(){
			return roleService.findAll();			
		}
		@GetMapping("/roles/{id}")
		public ResponseEntity<?> getById(@PathVariable Long id) {
			Rol role = null;
			Map<String, Object> response = new HashMap<>();
			try {
				role = roleService.findById(id);
			}catch(DataAccessException e) {
				response.put("message", "Error al realizar la consulta en la base de datos");
				response.put("error", e.getMessage());
			}
			if(role == null){
				response.put("message", "El Rol con ID".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<Rol>(role,HttpStatus.OK);
		}
		//METODO POST
		
		@PostMapping("/roles")
		public ResponseEntity<?> save(@RequestBody Rol role){
			Map<String, Object> response = new HashMap<>();
			try {
				if(roleService.isExist(role).size()>0) {
					response.put("message", "Este Rol ya esta registrado");
					return new ResponseEntity<Map<String , Object>>(response, HttpStatus.CONFLICT);
				}else {
					roleService.save(role);
				}
				
			}catch(DataAccessException e) {
				response.put("message", "Error al insertar el Rol en la base de datos");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "Rol registrado con exito...");
			response.put("roles", role);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
		
		//METODO PUT
		@PutMapping("/roles/{id}")
		public ResponseEntity<?> update(@RequestBody Rol role, @PathVariable Long id){
		
			Rol roleActual = roleService.findById(id);
			Rol roleUpdated = null;
			Map<String, Object> response = new HashMap<>();
			if(roleActual == null) {
				response.put("message", "Error: no se puede editar, el Rol ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
			}
			try {
				roleActual.setNombre(role.getNombre());
				roleUpdated = roleService.save(roleActual);
			}catch(DataAccessException e) {
				response.put("message", "Error al actualizar el Rol");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "Rol actualizado con exito...");
			response.put("roles", roleUpdated);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
		//METODO DELETE 
		@DeleteMapping("/roles/{id}")
		public ResponseEntity<?> delete(@PathVariable Long id){
			Map<String, Object> response = new HashMap<>();
			
			try {
				roleService.delete(id);
			}catch(DataAccessException e) {
				response.put("message", "Error al Eliminar el Rol de la base de datos");
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "El Rol ha sido eliminada...");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
		}
		
	}
*/