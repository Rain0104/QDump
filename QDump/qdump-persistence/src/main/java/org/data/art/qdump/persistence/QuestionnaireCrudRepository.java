package org.data.art.qdump.persistence;

import java.util.List;

import org.dataart.qdump.entities.questionnaire.QuestionnaireEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for {@link QuestionnaireEntity}
 * 
 * @author Ibrichak
 *
 */

public interface QuestionnaireCrudRepository  extends CrudRepository<QuestionnaireEntity, Long>{
	

	/**
	 * This method return Questionnaire by name.
	 * 
	 * @param name
	 *            {@link QuestionnaireEntity#getName()}
	 * @return
	 */
	public List<QuestionnaireEntity> getQuestionnaireByName(String name);

}
