package com.bolsadeideas.springboot.app.view.xml;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

//"clientes" va a representar la primera etiqueta rail del xml
@XmlRootElement(name="clienteList")
public class ClienteList {

	//"cliente" va a representar cada cliente dentro del xml
	@XmlElement(name="cliente")
	public List<Cliente> clientes;
		

	public ClienteList() {

	}


	public ClienteList(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	
	
	
	
}
