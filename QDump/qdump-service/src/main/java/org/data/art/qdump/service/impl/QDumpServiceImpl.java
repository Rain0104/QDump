package org.data.art.qdump.service.impl;

import java.util.List;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonGroupEnums;
import org.data.art.qdump.persistence.PersonRepository;
import org.data.art.qdump.service.QDumpService;
import org.springframework.beans.factory.annotation.Autowired;

public class QDumpServiceImpl implements QDumpService{
	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public void addPerson(PersonEntity person) {
		personRepository.addPerson(person);
	}

	@Override
	public PersonEntity getPerson(int id) {
		return personRepository.getPerson(id);
	}

	@Override
	public List<PersonEntity> getPersons() {
		return personRepository.getPersons();
	}

	@Override
	public <T> List<T> getPersonsByGroup(Class<T> choice, PersonGroupEnums group) {
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
