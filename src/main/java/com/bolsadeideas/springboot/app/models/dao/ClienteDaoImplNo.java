package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository //("clienteDaoJPA")
public class ClienteDaoImplNo implements IClienteDaoNo2 {

	//Investigar mas sobre el entityManager
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	//@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() 
	{	
		return em.createQuery("from Cliente").getResultList();
	}
	
	@Override
	//@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		// JPA va a la bd y encuentra el cliente en base a su id
		return em.find(Cliente.class, id);
	}

	@Override
	//@Transactional
	public void save(Cliente cliente) 
	{
		if(cliente.getId() != null && cliente.getId() > 0) 
		{
			em.merge(cliente); //actualiza los datos existentes
		} 
		else 
		{
			em.persist(cliente);//crea un nuevo cliente y lo inserta
		}
	}
	

	@Override
	//@Transactional
	public void delete(Long id) {
		
		Cliente cliente = findOne(id); //con esto ya hemos encontrado al cliente
		em.remove(cliente); // con esto lo eliminamos
		
		//version optimizada = em.remove(findOne(id));
	}

}
