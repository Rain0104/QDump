package org.data.art.qdump.service.impl;

import java.util.List;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonRoleEnums;
import org.data.art.qdump.persistence.PersonCrudRepistory;
import org.data.art.qdump.service.QDumpService;
import org.springframework.beans.factory.annotation.Autowired;

public class QDumpServiceImpl implements QDumpService{
	@Autowired
	private PersonCrudRepistory personCrudRepistory;

	@Override
	public void addPerson(PersonEntity person) {
		personCrudRepistory.save(person);	
	}

	@Override
	public PersonEntity getPerson(int id) {
		return personCrudRepistory.findOne(id);
	}

	@Override
	public List<PersonEntity> getPersons() {
		return (List<PersonEntity>) personCrudRepistory.findAll();
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
		personCrudRepistory.delete(id);
	}

	@Override
	public void deletePersons() {
		personCrudRepistory.deleteAll();		
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
