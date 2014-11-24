package org.data.art.qdump.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.data.art.qdump.config.PersonDAO;
import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonRoleEnums;
import org.data.art.qdump.service.QDumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QDumpServiceImpl implements QDumpService{
	@Autowired
	private PersonDAO personDAO;

	@Override
	public void addPerson(PersonEntity person) {
		personDAO.addPerson(person);	
	}

	@Override
	public PersonEntity getPerson(int id) {
		return personDAO.getPerson(id);
	}

	@Override
	public List<PersonEntity> getPersons() {
		return null;
				//(List<PersonEntity>) personCrudRepository.findAll();
	}

	@Override
	public void deletePerson(int id) {
		//personCrudRepository.delete(id);
	}

	@Override
	public void deletePersons() {
		//personCrudRepository.deleteAll();		
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