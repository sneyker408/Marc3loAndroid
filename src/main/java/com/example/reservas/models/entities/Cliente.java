package com.example.reservas.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="clientes", schema="public", catalog="Reservas_db")

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="nombre", nullable = false, length=100)
	private String nombre;
	
	@Column(name="telefono", nullable = true, length=100)
	private String telefono;

	@Column(name="direccion", nullable = false, length=100)
	private String direccion;
		
	@OneToMany(mappedBy = "cliente")
    private List<Reserva> reserva;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
