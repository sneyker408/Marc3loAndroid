	package com.example.reservas.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.reservas.models.entities.Producto;
import com.example.reservas.services.interfaces.IProductoService;
import com.example.reservas.services.interfaces.IUploadFileService;

	@CrossOrigin(origins = "*.*")
	@RestController
	@RequestMapping("/api")
	public class ProductoController {

		@Autowired
		private IProductoService productoService;
		
		@Autowired
		private IUploadFileService uploadService;
		
		private Logger logger = LoggerFactory.getLogger(ProductoController.class);
		

		
		@GetMapping("/productos")
		public List<Producto> getAll(){
			return productoService.findAll();			
		}
		//METODO MOSTRAR imagen (GET)
		@GetMapping(value = "/image/{filename}")
	    public @ResponseBody void getImage(HttpServletResponse response, @PathVariable("filename") String filename ) throws IOException {

			String UPLOAD_FOLDER = "src//main//resources/images";

	        File fl = new File(UPLOAD_FOLDER + "//" + filename);

	        InputStream resource = new FileInputStream(fl);

	        StreamUtils.copy(resource, response.getOutputStream());
	        
	        resource.close();

	    }
		//
		@GetMapping("/productos/{id}")
		public ResponseEntity<?> getById(@PathVariable Long id) {
			Producto producto = null;
			Map<String, Object> response = new HashMap<>();
			try {
				producto = productoService.findById(id);
			}catch(DataAccessException e) {
				response.put("message", "Error al realizar la consulta en la base de datos");
				response.put("error", e.getMessage());
			}
			if(producto == null){
				response.put("message", "El producto con ID".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);	
			}
			return new ResponseEntity<Producto>(producto,HttpStatus.OK);
		}
		
		//METODO INSERTAR
		@PostMapping("/productos")
		public ResponseEntity<?> save(@RequestPart Producto producto,
				@RequestPart(name = "imagen", required = false) MultipartFile imagen) throws IOException {
			String imageNewName = "";
			Map<String, Object> response = new HashMap<>();
			try {
				if (productoService.isExist(producto).size() > 0 && producto.getId() == null) {
					response.put("message", "Ya existe un registro con este nombre y descripcion en la base de datos");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CONFLICT);
				} else {
					if (imagen != null)
						imageNewName = uploadService.copyFile(imagen);
					producto.setImagen(imageNewName);
					productoService.save(producto);
				}

			} catch (DataAccessException e) {
				response.put("message", "Error al insertar registro en la base de datos");
				logger.error("ERROR: ".concat(e.getMessage()));
				uploadService.deleteFile(imageNewName);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			response.put("message", "Producto registrado con exito...");
			response.put("producto", producto);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
		//ACTUALIZAR
		@PutMapping("/productos/{id}")
		public ResponseEntity<?> update(@RequestPart Producto producto, @PathVariable Long id,
				@RequestPart(name = "imagen", required = false) MultipartFile imagen) throws IOException {

			String imageNewName = null;
			Producto productoActual = productoService.findById(id);
			Producto productoUpdated = null;
			Map<String, Object> response = new HashMap<>();
			if (productoActual == null) {
				response.put("message", "Error: no se puede editar, el producto con ID: "
						.concat(id.toString().concat("no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			try {
				productoActual.setNombre(producto.getNombre());
				productoActual.setExistencia(producto.getExistencia());
				productoActual.setMarca(producto.getMarca());
				if (productoActual.getImagen() != null && productoActual.getImagen().length() > 0) {
					String imgAnterior = productoActual.getImagen();
					Path rutaImgAnterior = uploadService.getPath(imgAnterior);
					File archivoImgAnterior = rutaImgAnterior.toFile();
					if (archivoImgAnterior.exists() && archivoImgAnterior.canRead()) {
						archivoImgAnterior.delete();
					}

				}
				if (imagen != null)
					imageNewName = uploadService.copyFile(imagen);
				productoActual.setImagen(imageNewName);
				productoUpdated = productoService.save(productoActual);

			} catch (DataAccessException e) {
				response.put("message", "Error al actualizar el producto");
				logger.error("ERROR: ".concat(e.getMessage()));
				uploadService.deleteFile(imageNewName);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			response.put("message", "Producto actualizado con exito..");
			response.put("producto", productoUpdated);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
		//DELITE
		@PostMapping("/imagen/delete/{name}")
		public ResponseEntity<?> deleteImagen(@PathVariable(name = "name") String imagen) {
			if (uploadService.deleteFile(imagen))
				return ResponseEntity.ok().build();
			else
				return ResponseEntity.status(500).build();
		}

}
