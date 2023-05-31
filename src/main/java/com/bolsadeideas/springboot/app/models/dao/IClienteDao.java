package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>
{
	//Los metodos del servicio estan dentro del CrudRepository
	//Aqui van las consultas con Query
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWhithFacturas(Long id);
}
