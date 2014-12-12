package org.dataart.qdump.service.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.dataart.qdump.entities.person.PersonEntity;
import org.dataart.qdump.service.ServiceQdump;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/app")
@Component
public class TestResource {
	@Autowired
	private ServiceQdump service;
	
	@GET
	public Response getTest() {
		String value = "hello world"; 
		return Response.status(200).entity(value).build();
	}
	
	@GET
	@Path("hello/{name}")
	public String getName(@PathParam("name") String name) {
		return "hello " + name;
	}
	
	@GET
	@Path("get/person")
	public PersonEntity getPerson() {
		/*EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("qdump-persistence");
		EntityManager em = emf.createEntityManager();
		PersonEntity entity = null;
		try {
			em.getTransaction().begin();
			entity = em.find(PersonEntity.class, 1l);
			em.getTransaction().commit();
		} finally {
			if(em.isOpen()) {
				em.close();
			}
			emf.close();
		}
		
		return entity;*/
		return service.getPersonEntity(2l);
	}
	
	@GET
	@Path("get/persons")
	public List<PersonEntity> getPersons() {
		List<PersonEntity> entities = new ArrayList<PersonEntity>();
		entities.add(new PersonEntity("vlasovartem21@gmail.com", "422617"));
		entities.add(new PersonEntity("vlasovartem23@gmail.com", "422617"));
		entities.add(new PersonEntity("vlasovartem25@gmail.com", "422617"));
		return entities;
	}
	
}
