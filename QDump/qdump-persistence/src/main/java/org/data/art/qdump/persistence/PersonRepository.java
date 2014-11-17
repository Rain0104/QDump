package org.data.art.qdump.persistence;

import java.util.List;

import org.data.art.qdump.entities.Person;
import org.data.art.qdump.entities.PersonGroup;

public interface PersonRepository {
	
	public void addPerson(Person person);
	
	public Person getPerson(int id);
	public List<Person> getPersons();
	public <T> List<T> getPersonsByGroup(
			Class<T> choice, PersonGroup group);
	
	public void deletePerson(int id);
	public void deletePersons();

}
