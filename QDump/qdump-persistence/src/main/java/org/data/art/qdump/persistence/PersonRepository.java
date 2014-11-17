package org.data.art.qdump.persistence;

import java.util.List;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonGroupEnums;

public interface PersonRepository {
	
	public void addPerson(PersonEntity person);
	
	public PersonEntity getPerson(int id);
	public List<PersonEntity> getPersons();
	public <T> List<T> getPersonsByGroup(
			Class<T> choice, PersonGroupEnums group);
	
	public void deletePerson(int id);
	public void deletePersons();

}
