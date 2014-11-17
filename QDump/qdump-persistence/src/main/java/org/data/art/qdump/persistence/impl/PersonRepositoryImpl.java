package org.data.art.qdump.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonGroupEnums;
import org.data.art.qdump.persistence.PersonRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
	
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
	public <T> List<T> getPersonsByGroup(Class<T> choice, 
			PersonGroupEnums group) {
		TypedQuery<T> query = em.createNamedQuery(
				PersonEntity.GET_PERSONS_BY_GROUP, choice);
		query.setParameter("category", group.name());
		return query.getResultList();
	}

	@Override
	public void deletePerson(int id) {
		Query query = em.createNamedQuery(PersonEntity.DELETE_PERSON);
		query.setParameter("id", id);
	}

	@Override
	public void deletePersons() {
		em.createNamedQuery(PersonEntity.DELETE_PERSONS);
	}

}
