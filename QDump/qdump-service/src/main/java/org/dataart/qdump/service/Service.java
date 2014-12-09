package org.dataart.qdump.service;

import java.util.List;

import org.dataart.qdump.entities.person.PersonEntity;
import org.dataart.qdump.entities.person.PersonQuestionEntity;
import org.dataart.qdump.entities.person.PersonQuestionnaireEntity;

public interface Service {
	//PersonEntity
	void addPersonEntity(PersonEntity personEntity);
	void deletePersonEntity(int id);
	void deletePersonEntityByEmail(String email);
	void deletePersonEntityByLogin(String login);
	void deleteAllPersonEntities();
	PersonEntity getPersonEntity(int id);
	List<PersonEntity> getPersonEntities();
	
	//PersonQuestionnaireEntity
	void addPersonQuestionnaireEntity(PersonQuestionnaireEntity personQuestionnaireEntity);
	void deletePersonQuestionnaireEntity(int id);
	void deletePersonQuestionnaireEntityByOwnBy(String login);
	void deleteAllPersonQuestionnaireEntities();
	PersonQuestionnaireEntity getPersonQuestionnaireEntity(int id);
	List<PersonQuestionnaireEntity> getPersonQuestionnaireEntities();
	
	//PersonQuestionEntity
	void addPersonQuestionEntity(PersonQuestionEntity personQuestionEntity);
	void deletePersonQuestionEntity(int id);
	void deletePersonQuestionEntityByPersonQuestionnaireId(int id);
	void deleteAllPersonQuestionEntity();
	PersonQuestionEntity getPersonQuestionEntity(int id);
	List<PersonQuestionEntity> getPersonQuestionEntities();
	
	
	
	
}
