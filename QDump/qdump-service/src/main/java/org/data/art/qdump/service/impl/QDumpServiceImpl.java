package org.data.art.qdump.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonRoleEnums;
import org.data.art.qdump.persistence.PersonCrudRepository;
import org.data.art.qdump.service.QDumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QDumpServiceImpl implements QDumpService{
	@Autowired
	private PersonCrudRepository personCrudRepository;

	@Override
	public void addPerson(PersonEntity person) {
		personCrudRepository.save(person);	
	}

	@Override
	public PersonEntity getPerson(int id) {
		return personCrudRepository.findOne(id);
	}

	@Override
	public List<PersonEntity> getPersons() {
		return (List<PersonEntity>) personCrudRepository.findAll();
	}

	@Override
	public void deletePerson(int id) {
		personCrudRepository.delete(id);
	}

	@Override
	public void deletePersons() {
		personCrudRepository.deleteAll();		
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
