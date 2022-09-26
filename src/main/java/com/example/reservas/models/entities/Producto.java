package com.example.reservas.models.entities;

	
	import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

	@Entity
	@Table(name="productos", schema = "public", catalog = "Reservas_db")
	public class Producto implements Serializable {
		private static final long serialVersionUID = 1L;
		@Id
		@Column(name = "id", nullable = false)
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "nombre", nullable = false, length = 100)
		private String nombre;
		
		@Column(name = "descripcion", nullable = false, length = 200)
		private String descripcion;
		
		@Column(name = "precio", nullable = false, precision = 2)
		private Double precio;
		
		@Column(name = "existencia", nullable = false)
		private Integer existencia;
		
		@Column(name = "marca", nullable = false)
		private String marca;
		
		
		@Column(name = "imagen", nullable = true, length = 100)
		private String imagen;
		
		//RELACION
		@OneToMany(mappedBy = "producto", cascade = CascadeType.REFRESH, orphanRemoval = true)
		@JsonBackReference
		private List<Detalle_Reserva> detalle_reserva = new ArrayList<>();
		

		//Getters and Setters
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

		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public Double getPrecio() {
			return precio;
		}
		public void setPrecio(Double precio) {
			this.precio = precio;
		}

		public Integer getExistencia() {
			return existencia;
		}
		public void setExistencia(Integer existencia) {
			this.existencia = existencia;
		}
		

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getImagen() {
			return imagen;
		}
		public void setImagen(String imagen) {
			this.imagen = imagen;
		}
		
}
