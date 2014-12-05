package org.data.art.qdump.service;

import java.util.List;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonRoleEnums;


public interface QDumpService{

	//Person repository
	public void addPerson(PersonEntity person);
	
	public PersonEntity getPerson(int id);
	public List<PersonEntity> getPersons();
	public List<PersonEntity> getPersonByEmail(String email);
	public List<PersonEntity> getPersonByLogin(String login);
	public List<PersonEntity> getPersonByRole(PersonRoleEnums role);
	
	public void deletePerson(int id);
	public void deletePersons();
	public void deletePersonByEmail(String email);
	public void deletePersonByLogin(String login);
	public void deletePersonByRole(PersonRoleEnums role);
}

