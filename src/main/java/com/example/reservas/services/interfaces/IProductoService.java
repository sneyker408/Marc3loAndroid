package com.example.reservas.services.interfaces;

	import java.util.List;

import com.example.reservas.models.entities.Producto;

	public interface IProductoService {
		
		public List<Producto> findAll();
		
		public Producto findById(Long id);
		
		public Producto save(Producto producto);
		
		public void delete(Long id);
		
		public List<Producto> isExist(Producto producto);

}


