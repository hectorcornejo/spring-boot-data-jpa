package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//120
@Entity
@Table(name = "clientes")
//Conviete el objeto en una secuencia de bits para poder enviarlo a una bd
public class Cliente implements Serializable {

	
	@Id // PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty //para String
	//@Size(min=1, max=12)
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
	private String email;

	@NotNull //todo lo que no se String
	@Column(name = "create_at") // @Column se usa en el caso de que el nombre sea distinto en la bd
	@Temporal(TemporalType.DATE) // solo para fechas, indica el formato en el que se va a guardar en la bd
	@DateTimeFormat(pattern="yyyy-MM-dd") //Establecer un formato predefinido para el formulario
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") //Establecer el formato de fecha cuando se convierte la vista a json
	private Date createAt;
	
	//un cliente puede tener muchas facturas
	//Lazy para no llamar a todas las facturas del cliente (no sobrecargar la bd)
	//foreign key "cliente" porque es una relacion en ambos sentidos
	@OneToMany(mappedBy = "cliente", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference //No interesa tener las facturas del cliente en el json, por eso se ignora
	private List<Factura> facturas;
	
	
	public Cliente() 
	{
		facturas = new ArrayList<Factura>();
	}

	private String foto;

	
	private static final long serialVersionUID = 1L;
	
	/* Se emplearia si no hubira un campo fecha en el formulario, ya que completa el campo fecha de forma automatica con la fecha actual
	 * 
	 *@PrePersist
	public void preGuardarBd()
	{
		createAt = new Date();
	}*/
	
	
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	
	public void addFactura(Factura factura)
	{
		facturas.add(factura);
	}
	
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

	

}
