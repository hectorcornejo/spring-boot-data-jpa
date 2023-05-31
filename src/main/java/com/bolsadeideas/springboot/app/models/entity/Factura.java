package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.xml.bind.annotation.XmlTransient;

//Serializable es porque es una clase entity, todas las clases entity llevan la implementacion de serializable
@Entity
@Table(name="facturas")
public class Factura implements Serializable {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String descripcion;
	private String observacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createAt;
	
	//Muchas facturas un cliente
	//Lazy evitar traer todo de la consulta
	//Eager para traer todo y rapido
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonBackReference //Es la parte que se omite de la serializacion
	private Cliente cliente;
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	//Creacion de la foreign key "factura_id" en la tabla "facturas_id", se debe a que no es una relación en ambos sentidos (unidireccional UML)
	@JoinColumn(name = "factura_id")
	private List<ItemFactura> items;
	
	

	public Factura() 
	{
		this.items = new ArrayList<ItemFactura>();
	}
	

	//Antes de persistir
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	//Es para que no sea bidireccional cuando se serializa a xml (no lo incluye en xml)
	@XmlTransient
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}
	
	public void addItemFactura(ItemFactura item)
	{
		this.items.add(item);
	}
	
	public Double getTotal()
	{
		Double total = 0.0;
		
		int size = items.size();
		
		for(int i=0; i < size; i++)
		{
			total += items.get(i).calcularImporte();
		}
		return total;
		
	}
	
	//atributo obligatorio de serializable
	private static final long serialVersionUID = 1L;
	
}
