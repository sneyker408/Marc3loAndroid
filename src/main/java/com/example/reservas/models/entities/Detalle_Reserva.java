package com.example.reservas.models.entities;
	

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="detalle_reservas", schema = "public", catalog = "Reservas_db")
public class Detalle_Reserva implements Serializable  {
private static final long serialVersionUID = 1L;
	

@Id
@Column(name = "id", nullable = false)
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "Id_Reserva", referencedColumnName = "id", nullable = false)
@JsonBackReference
private Reserva reserva;

@ManyToOne(fetch = FetchType.LAZY)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JoinColumn(name = "Id_Producto", referencedColumnName = "id", nullable = false)
private Producto producto;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Reserva getReserva() {
	return reserva;
}

public void setReserva(Reserva reserva) {
	this.reserva = reserva;
}

public Producto getProducto() {
	return producto;
}

public void setProducto(Producto producto) {
	this.producto = producto;
}

}
