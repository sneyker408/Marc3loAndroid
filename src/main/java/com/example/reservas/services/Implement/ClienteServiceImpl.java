package com.example.reservas.services.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reservas.models.dao.IClienteDAO;
import com.example.reservas.models.entities.Cliente;
import com.example.reservas.services.interfaces.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	// Estamos haciendo una inyeccion
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>)clienteDAO.findAll();
	}

	@Override
	public Cliente findById(Long id) {
		return clienteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDAO.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDAO.deleteById(id);
	}

	@Override
	public List<Cliente> isExist(Cliente cliente) {
		return clienteDAO.findByNombreDireccion(cliente);
	}

}
