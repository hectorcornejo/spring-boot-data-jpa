package com.bolsadeideas.springboot.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.view.xml.ClienteList;

//Esta anotacion implica que todas las respuestas, es decir, todos los return van a ser solo de tipo json o xml
//Por este motivo no es necesario el @ResponseBody, ya que va por dentro de @RestController, al igual que @Controller
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;

	@GetMapping("/listar-rest")
	public ClienteList listarRest() {
		
		return new ClienteList(clienteService.findAll());
		
	}
}
