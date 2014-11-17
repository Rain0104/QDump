package org.data.art.qdump.service;

import java.util.List;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonGroupEnums;


public interface QDumpService{
	
	//Person repository
	public void addPerson(PersonEntity person);
	
	public PersonEntity getPerson(int id);
	public List<PersonEntity> getPersons();
	public <T> List<T> getPersonsByGroup(
			Class<T> choice, PersonGroupEnums group);
	
	public void deletePerson(int id);
	public void deletePersons();

}

