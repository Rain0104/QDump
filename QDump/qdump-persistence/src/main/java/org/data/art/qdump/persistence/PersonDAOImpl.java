package org.data.art.qdump.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.data.art.qdump.config.PersonDAO;
import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonRoleEnums;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonDAOImpl implements PersonDAO{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addPerson(PersonEntity person) {
		em.persist(person);
	}

	@Override
	@Transactional(readOnly=true)
	public PersonEntity getPerson(int id) {
		return em.find(PersonEntity.class, id);
	}

	@Override
	public List<PersonEntity> getPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonEntity> getPersonByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonEntity> getPersonByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonEntity> getPersonByRole(PersonRoleEnums role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePerson(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonByEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonByLogin(String login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePersonByRole(PersonRoleEnums role) {
		// TODO Auto-generated method stub
		
	}

}
