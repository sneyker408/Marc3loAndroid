package com.example.reservas.services.Implement;


	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;

	import com.example.reservas.models.dao.IProductoDAO;
	import com.example.reservas.models.entities.Producto;
import com.example.reservas.services.interfaces.IProductoService;

	@Service
	public class ProductoServiceImpl implements IProductoService{

		// Estamos haciendo una inyeccion
		@Autowired
		private IProductoDAO productoDAO;
		
		@Override
		public List<Producto> findAll() {
			return (List<Producto>)productoDAO.findAll();
		}

		@Override
		public Producto findById(Long id) {
			return productoDAO.findById(id).orElse(null);
		}

		@Override
		@Transactional
		public Producto save(Producto producto) {
			return productoDAO.save(producto);
		}

		@Override
		@Transactional
		public void delete(Long id) {
			productoDAO.deleteById(id);
		}

		@Override
		public List<Producto> isExist(Producto producto) {
			return productoDAO.findByNombreMarca(producto);
		}

	}
