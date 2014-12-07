package org.dataart.qdump.persistence;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.dataart.qdump.entities.enums.PersonGroupEnums;
import org.dataart.qdump.entities.enums.QuestionTypeEnums;
import org.dataart.qdump.entities.person.PersonAnswerEntity;
import org.dataart.qdump.entities.person.PersonEntity;
import org.dataart.qdump.entities.person.PersonQuestionEntity;
import org.dataart.qdump.entities.person.PersonQuestionnaireEntity;
import org.dataart.qdump.entities.questionnaire.AnswerEntity;
import org.dataart.qdump.entities.questionnaire.QuestionEntity;
import org.dataart.qdump.entities.questionnaire.QuestionnaireEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JpaTest {
	public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("qdump-persistence");
		EntityManager em = emf.createEntityManager();
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			//PersonEntity
			PersonEntity personEntity1 = createPersonEntity("first@gmail.com", "login1", "password1", "surname1", "name1", true, (byte) 1);
			PersonEntity personEntity2 = createPersonEntity("second@gmail.com", "login2", "password2", "surname2", "name2", true, (byte) 1);
			
			System.out.println(mapper.writeValueAsString(personEntity2));
			
			//AnswerEntity
			List<AnswerEntity> answerEntities1 = new ArrayList<AnswerEntity>();
			answerEntities1.add(createAnswerEntity("first answer first question", true));
			List<AnswerEntity> answerEntities2 = new ArrayList<AnswerEntity>();
			answerEntities2.add(createAnswerEntity("first answer second question", true));
			answerEntities2.add(createAnswerEntity("second answer second question", true));
			List<AnswerEntity> answerEntities3 = new ArrayList<AnswerEntity>();
			answerEntities3.add(createAnswerEntity("first answer third question", true));
			answerEntities3.add(createAnswerEntity("second answer third question", true));
			answerEntities3.add(createAnswerEntity("third answer third question", true));
			
			System.out.println(mapper.writeValueAsString(answerEntities1.get(0)));
			
			//QuestionEntity
			List<QuestionEntity> questionEntities1 = new ArrayList<QuestionEntity>();
			questionEntities1.add(createQuestionEntity(answerEntities1, "first question first questionnaire", QuestionTypeEnums.CHECKBOX));
			List<QuestionEntity> questionEntities2 = new ArrayList<QuestionEntity>();
			questionEntities2.add(createQuestionEntity(answerEntities2, "second question second questionnaire", QuestionTypeEnums.RADIO));
			List<QuestionEntity> questionEntities3 = new ArrayList<QuestionEntity>();
			questionEntities3.add(createQuestionEntity(answerEntities3, "third question third questionnaire", QuestionTypeEnums.SELECT));
			
			//QuestionnaireEntity
			List<QuestionnaireEntity> questionnaireEntities1 = new ArrayList<QuestionnaireEntity>();
			questionnaireEntities1.add(createQuestionnaireEntity(personEntity1, questionEntities1, "first questionnaire", "first", true));
			List<QuestionnaireEntity> questionnaireEntities2 = new ArrayList<QuestionnaireEntity>();
			questionnaireEntities2.add(createQuestionnaireEntity(personEntity2, questionEntities2, "second questionnaire", "second", true));
			List<QuestionnaireEntity> questionnaireEntities3 = new ArrayList<QuestionnaireEntity>();
			questionnaireEntities3.add(createQuestionnaireEntity(personEntity1, questionEntities3, "third questionnaire", "third", false));
			
			//PersonAnswerEntity
			List<PersonAnswerEntity> personAnswerEntities1 = new ArrayList<PersonAnswerEntity>();
			personAnswerEntities1.add(createPersonAnswerEntity(answerEntities1.get(0), "First PQuestion first PA", true));
			List<PersonAnswerEntity> personAnswerEntities2 = new ArrayList<PersonAnswerEntity>();
			personAnswerEntities2.add(createPersonAnswerEntity(answerEntities2.get(0), "Second PQuestion first PA", true));
			personAnswerEntities2.add(createPersonAnswerEntity(answerEntities2.get(1), "Second PQuestion second PA", false));
			List<PersonAnswerEntity> personAnswerEntities3 = new ArrayList<PersonAnswerEntity>();
			personAnswerEntities3.add(createPersonAnswerEntity(answerEntities3.get(0), "Third PQuestion first PA", true));
			personAnswerEntities3.add(createPersonAnswerEntity(answerEntities3.get(1), "Third PQuestion second PA", false));
			personAnswerEntities3.add(createPersonAnswerEntity(answerEntities3.get(2), "Third PQuestion third PA", true));
			
			//PersonQuestionEntity
			List<PersonQuestionEntity> personQuestionEntities1 = new ArrayList<PersonQuestionEntity>();
			personQuestionEntities1.add(createPersonQuestionEntity(personAnswerEntities1, questionEntities1.get(0)));
			personQuestionEntities1.stream().forEach(entity -> entity.checkQuestionIsCorrect());
			List<PersonQuestionEntity> personQuestionEntities2 = new ArrayList<PersonQuestionEntity>();
			personQuestionEntities2.add(createPersonQuestionEntity(personAnswerEntities2, questionEntities2.get(0)));
			personQuestionEntities2.stream().forEach(entity -> entity.checkQuestionIsCorrect());
			List<PersonQuestionEntity> personQuestionEntities3 = new ArrayList<PersonQuestionEntity>();
			personQuestionEntities3.add(createPersonQuestionEntity(personAnswerEntities3, questionEntities3.get(0)));
			personQuestionEntities3.stream().forEach(entity -> entity.checkQuestionIsCorrect());
			
			//PersonQuestionnaireEntity
			List<PersonQuestionnaireEntity> personQuestionnaireEntities1 = new ArrayList<PersonQuestionnaireEntity>();
			personQuestionnaireEntities1.add(createPersonQuestionnaireEntity(personQuestionEntities1, questionnaireEntities1.get(0), personEntity1));
			personQuestionnaireEntities1.stream().forEach(entity -> entity.checkStatus());
			List<PersonQuestionnaireEntity> personQuestionnaireEntities2 = new ArrayList<PersonQuestionnaireEntity>();
			personQuestionnaireEntities2.add(createPersonQuestionnaireEntity(personQuestionEntities2, questionnaireEntities2.get(0), personEntity2));
			personQuestionnaireEntities2.stream().forEach(entity -> entity.checkStatus());
			List<PersonQuestionnaireEntity> personQuestionnaireEntities3 = new ArrayList<PersonQuestionnaireEntity>();
			personQuestionnaireEntities3.add(createPersonQuestionnaireEntity(personQuestionEntities3, questionnaireEntities3.get(0), personEntity1));
			personQuestionnaireEntities3.stream().forEach(entity -> entity.checkStatus());
			
			em.getTransaction().begin();
			em.persist(personEntity1);
			em.persist(personEntity2);
			persistCollection(personQuestionnaireEntities1, em);
			persistCollection(personQuestionnaireEntities2, em);
			persistCollection(personQuestionnaireEntities3, em);
			persistCollection(questionnaireEntities1, em);
			persistCollection(questionnaireEntities2, em);
			persistCollection(questionnaireEntities3, em);
			em.getTransaction().commit();
		} finally {
			if(em.isOpen()) {
				em.close();
			}
			emf.close();
		}
	}
	public static AnswerEntity createAnswerEntity(String answer, boolean isCorrect) {
		AnswerEntity answerEntity = new AnswerEntity();
		answerEntity.setAnswer(answer);
		answerEntity.setCorrect(isCorrect);
		answerEntity.setCreatedDate(new Date());
		answerEntity.setModifiedDate(new Date(System.currentTimeMillis() + 100000));
		return answerEntity;
	}
	public static QuestionEntity createQuestionEntity(List<AnswerEntity> answerEntities, String question, QuestionTypeEnums type) {
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setCreatedDate(new Date());
		questionEntity.setModifiedDate(new Date(System.currentTimeMillis() + 100000));
		questionEntity.setQuestion(question);
		questionEntity.setType(type);
		for(AnswerEntity answerEntity : answerEntities) {
			answerEntity.setQuestionEntity(questionEntity);
		}
		questionEntity.setAnswerEntities(answerEntities);
		return questionEntity;
	}
	public static QuestionnaireEntity createQuestionnaireEntity(PersonEntity personEntity, List<QuestionEntity> questionEntities, String description, String name, boolean isPublished) {
		QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
		questionnaireEntity.setCreatedBy(personEntity);
		questionnaireEntity.setCreatedDate(new Date());
		questionnaireEntity.setDescription(description);
		questionnaireEntity.setModifiedBy(personEntity);
		questionnaireEntity.setModifiedDate(new Date(System.currentTimeMillis() + 100000));
		questionnaireEntity.setName(name);
		questionnaireEntity.setPublished(isPublished);
		for(QuestionEntity questionEntity : questionEntities) {
			questionEntity.setQuestionnaireEntity(questionnaireEntity);
		}
		questionnaireEntity.setQuestionEntities(questionEntities);
		return questionnaireEntity;
	}
	public static PersonEntity createPersonEntity(String email, String login, String password, String surname, String name, boolean isEnabled, byte gender) {
		PersonEntity personEntity = new PersonEntity();
		personEntity.setCreatedDate(new Date());
		personEntity.setEmail(email);
		personEntity.setEnabled(isEnabled);
		personEntity.setGender(gender);
		personEntity.setLogin(login);
		personEntity.setModifiedDate(new Date(System.currentTimeMillis() + 100000));
		personEntity.setName(name);
		personEntity.setPassword(password);
		personEntity.setPersonGroup(PersonGroupEnums.USER);
		personEntity.setSurname(surname);
		return personEntity;
	}
	public static PersonAnswerEntity createPersonAnswerEntity(AnswerEntity answerEntity, String personAnswer, boolean marked) {
		PersonAnswerEntity personAnswerEntity = new PersonAnswerEntity();
		personAnswerEntity.setAnswerEntity(answerEntity);
		personAnswerEntity.setCreatedDate(new Date());
		personAnswerEntity.setModifiedDate(new Date(System.currentTimeMillis() + 100000));
		personAnswerEntity.setPersonAnswer(personAnswer);
		personAnswerEntity.setMarked(marked);
		return personAnswerEntity;
	}
	public static PersonQuestionEntity createPersonQuestionEntity(List<PersonAnswerEntity> personAnswerEntities, QuestionEntity questionEntity) {
		PersonQuestionEntity personQuestionEntity = new PersonQuestionEntity();
		personQuestionEntity.setCreatedDate(new Date());
		personQuestionEntity.setModifiedDate(new Date(System.currentTimeMillis() + 100000));
		personQuestionEntity.setQuestionEntity(questionEntity);
		personQuestionEntity.setPersonAnswerEntities(personAnswerEntities);
		return personQuestionEntity;
	}
	public static PersonQuestionnaireEntity createPersonQuestionnaireEntity(List<PersonQuestionEntity> personQuestionEntities, QuestionnaireEntity questionnaireEntity, PersonEntity ownBy) {
		PersonQuestionnaireEntity personQuestionnaireEntity = new PersonQuestionnaireEntity();
		personQuestionnaireEntity.setCreatedDate(new Date());
		personQuestionnaireEntity.setModifiedDate(new Date(System.currentTimeMillis() + 100000));
		personQuestionnaireEntity.setQuestionnaireEntity(questionnaireEntity);
		List<PersonQuestionnaireEntity> personQuestionnaireEntities;
		if(ownBy.getPersonQuestionnaireEntities() != null) {
			personQuestionnaireEntities = ownBy.getPersonQuestionnaireEntities();
			personQuestionnaireEntities.add(personQuestionnaireEntity);
		} else {
			personQuestionnaireEntities = new ArrayList<PersonQuestionnaireEntity>();
			personQuestionnaireEntities.add(personQuestionnaireEntity);
		}
		ownBy.setPersonQuestionnaireEntities(personQuestionnaireEntities);
		personQuestionnaireEntity.setOwnBy(ownBy);
		for(PersonQuestionEntity personQuestionEntity : personQuestionEntities) {
			personQuestionEntity.setPersonQuestionnaireEntity(personQuestionnaireEntity);
		}
		personQuestionnaireEntity.setPersonQuestionEntities(personQuestionEntities);
		return personQuestionnaireEntity;
	}
	public static void persistCollection(List<?> collection, EntityManager em) {
		for(int i = 0; i < collection.size(); i++) {
			em.persist(collection.get(i));
		}
	}
}
