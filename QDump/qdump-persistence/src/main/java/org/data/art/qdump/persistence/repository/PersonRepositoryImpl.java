package org.data.art.qdump.persistence.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.data.art.qdump.entities.Person;
import org.data.art.qdump.entities.PersonGroup;
import org.data.art.qdump.persistence.PersonRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addPerson(Person person) {
		em.persist(person);
	}

	@Override
	@Transactional(readOnly=true)
	public Person getPerson(int id) {
		return em.find(Person.class, id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Person> getPersons() {
		return em.createNamedQuery(Person.GET_PERSONS, 
				Person.class).getResultList();
	}

	@Override
	public <T> List<T> getPersonsByGroup(Class<T> choice, 
			PersonGroup group) {
		TypedQuery<T> query = em.createNamedQuery(
				Person.GET_PERSONS_BY_GROUP, choice);
		query.setParameter("category", group.name());
		return query.getResultList();
	}

	@Override
	public void deletePerson(int id) {
		Query query = em.createNamedQuery(Person.DELETE_PERSON);
		query.setParameter("id", id);
	}

	@Override
	public void deletePersons() {
		em.createNamedQuery(Person.DELETE_PERSONS);
	}

}
