package org.data.art.qdump.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	@Transactional(readOnly=true)
	public List<PersonEntity> getPersons() {
		return em.createNamedQuery(PersonEntity.GET_PERSONS, 
				PersonEntity.class).getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PersonEntity> getPersonByEmail(String email) {
		TypedQuery<PersonEntity> query = em.createNamedQuery(
				PersonEntity.GET_PERSONS_BY_EMAIL, 
				PersonEntity.class);
		query.setParameter("email", email);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PersonEntity> getPersonByLogin(String login) {
		TypedQuery<PersonEntity> query = em.createNamedQuery(
				PersonEntity.GET_PERSONS_BY_LOGIN, 
				PersonEntity.class);
		query.setParameter("login", login);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PersonEntity> getPersonByRole(PersonRoleEnums role) {
		TypedQuery<PersonEntity> query = em.createNamedQuery(
				PersonEntity.GET_PERSONS_BY_ROLE, 
				PersonEntity.class);
		query.setParameter("role", role.name());
		return query.getResultList();
	}

	@Override
	public void deletePerson(int id) {
	}

	@Override
	public void deletePersons() {
	}

	@Override
	public void deletePersonByEmail(String email) {
	}

	@Override
	public void deletePersonByLogin(String login) {
	}

	@Override
	public void deletePersonByRole(PersonRoleEnums role) {
	}

}
