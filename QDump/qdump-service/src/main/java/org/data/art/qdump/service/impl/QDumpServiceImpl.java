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
		return personDAO.getPersons();
	}

	@Override
	public void deletePerson(int id) {
		personDAO.deletePerson(id);
	}

	@Override
	public void deletePersons() {
		personDAO.deletePersons();		
	}

	@Override
	public List<PersonEntity> getPersonByEmail(String email) {
		return personDAO.getPersonByEmail(email);
	}

	@Override
	public List<PersonEntity> getPersonByLogin(String login) {
		return personDAO.getPersonByLogin(login);
	}

	@Override
	public List<PersonEntity> getPersonByRole(PersonRoleEnums role) {
		return personDAO.getPersonByRole(role);
	}

	@Override
	public void deletePersonByEmail(String email) {
		personDAO.deletePersonByEmail(email);
	}

	@Override
	public void deletePersonByLogin(String login) {
		personDAO.deletePersonByLogin(login);
	}

	@Override
	public void deletePersonByRole(PersonRoleEnums role) {
		personDAO.deletePersonByRole(role);
	}

}
