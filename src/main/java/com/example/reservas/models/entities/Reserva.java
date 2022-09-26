package com.example.reservas.models.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonBackReference;


	@Entity
	@Table(name="reservas", schema="public", catalog="Reservas_db")

	public class Reserva implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(name="id", nullable = false)
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		
		@Column(name="Create_dt")
		@Temporal(TemporalType.DATE)
		private Date Create_dt;

		@Column(name="Estado", nullable = false, length=100)
		private String Estado;
		
		//RELACIONES
		@ManyToOne
	    @JoinColumn(name = "cliente_id",nullable = false)
	    private Cliente cliente;
		
		@ManyToOne
	    @JoinColumn(name = "usuario_id",nullable = false)
	    private Usuario usuarios;
		
		/*
		@ManyToOne(fetch = FetchType.LAZY)
		@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
		@JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
		private Cliente cliente;
		*/
		
		@OneToMany(mappedBy = "reserva", cascade = CascadeType.REFRESH, orphanRemoval = true)
		private List<Detalle_Reserva> detalle_reserva = new ArrayList<>();
	
		//get y set 
		@PrePersist
		public void setCreate_dt() {
			this.Estado = "A";
			this.Create_dt = new Date();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public Date getCreate_dt() {
			return Create_dt;
		}

		public void setCreate_dt(Date create_dt) {
			this.Create_dt = create_dt;
		}

		public String getEstado() {
			return Estado;
		}

		public void setEstado(String estado) {
			this.Estado = estado;
		}
		
		public Cliente getCliente() {
			return cliente;
		}

		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		public Usuario getUsuarios() {
			return usuarios;
		}

		public void setUsuarios(Usuario usuarios) {
			this.usuarios = usuarios;
		}

		public List<Detalle_Reserva> getDetalle_reserva() {
			return detalle_reserva;
		}

		public void setDetalle_reserva(List<Detalle_Reserva> detalle_reserva) {
			this.detalle_reserva = detalle_reserva;
		}
		
		
		
		
		
}
