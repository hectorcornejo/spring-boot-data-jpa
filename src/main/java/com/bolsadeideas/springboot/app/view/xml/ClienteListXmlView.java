package com.bolsadeideas.springboot.app.view.xml;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("listar.xml")
public class ClienteListXmlView extends MarshallingView{

	//Este constructor se ha especificado como un componente de Spring desde la clase mvcConfig
	@Autowired
	public ClienteListXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		//Los elemntos que se borran se borran del model porque no son necesarios para convertir a xml
		//solo son necesarios los
		model.remove("titulo");
		model.remove("page");
		
		
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		
		model.remove("clientes");
		
		//Los clientes tienen que estar dentro de la clase CleinteList, por eso se pasan los clientes al constructor de dicha clase
		//El getContent() es para sacar los clientes paginados
		model.put("clienteList", new ClienteList(clientes.getContent()));
		
		//Se deja porque esta llamando al obejeto del padre
		super.renderMergedOutputModel(model, request, response);
	}

	
}
