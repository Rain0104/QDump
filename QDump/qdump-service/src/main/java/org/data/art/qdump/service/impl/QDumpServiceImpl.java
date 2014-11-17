package org.data.art.qdump.service.impl;

import java.util.List;

import org.data.art.qdump.entities.Person;
import org.data.art.qdump.entities.PersonGroup;
import org.data.art.qdump.persistence.PersonRepository;
import org.data.art.qdump.service.QDumpService;
import org.springframework.beans.factory.annotation.Autowired;

public class QDumpServiceImpl implements QDumpService{
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public void addPerson(Person person) {
		personRepository.addPerson(person);
	}

	@Override
	public Person getPerson(int id) {
		return personRepository.getPerson(id);
	}

	@Override
	public List<Person> getPersons() {
		return personRepository.getPersons();
	}

	@Override
	public <T> List<T> getPersonsByGroup(Class<T> choice, PersonGroup group) {
		return personRepository.getPersonsByGroup(choice, group);
	}

	@Override
	public void deletePerson(int id) {
		personRepository.deletePerson(id);
	}

	@Override
	public void deletePersons() {
		personRepository.deletePersons();
	}

}
