package org.dataart.qdump.entities.person;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dataart.qdump.entities.enums.QuestionTypeEnums;
import org.dataart.qdump.entities.enums.QuestionnaireStatusEnums;
import org.dataart.qdump.entities.questionnaire.QuestionnaireEntity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;

@Entity
@Table(name = "person_questionnaires")
@AttributeOverride(name = "id", column = @Column(name = "id_person_questionnaire", insertable = false, updatable = false))
@JsonAutoDetect
public class PersonQuestionnaireEntity extends PersonQuestionnaireBaseEntity implements Serializable{
	private static final long serialVersionUID = 586942138808550795L;
	private QuestionnaireEntity questionnaireEntity;
	private String status;
	private List<PersonQuestionEntity> personQuestionEntities;

	@OneToOne
	@JoinColumn(name = "id_questionnaire", referencedColumnName = "id_questionnaire")
	@JsonProperty("questionnaire_entity")
	public QuestionnaireEntity getQuestionnaireEntity() {
		return questionnaireEntity;
	}

	public void setQuestionnaireEntity(QuestionnaireEntity questionnaireEntity) {
		this.questionnaireEntity = questionnaireEntity;
	}

	@Column(name = "status", columnDefinition = "VARCHAR(100) DEFAULT 'not specified'")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "personQuestionnaireEntity", orphanRemoval = true)
	@JsonProperty("person_question_entities")
	public List<PersonQuestionEntity> getPersonQuestionEntities() {
		return personQuestionEntities;
	}

	public void setPersonQuestionEntities(
			List<PersonQuestionEntity> personQuestionEntities) {
		this.personQuestionEntities = personQuestionEntities;
	}

	/**
	 * Check status of {@link PersonQuestionnaireEntity}, should be used after 
	 * persistence {@link PersonQuestionnaireEntity}.
	 * If all {@link PersonQuestionEntity} in current object is correct, 
	 * status of {@link PersonQuestionnaireEntity} should be
	 * set as "Passed".
	 * If all {@link PersonQuestionEntity} is not correct
	 * status should be set as "Not Passed".
	 * If any of {@link PersonQuestionEntity} is has {@link QuestionTypeEnums}
	 * set as Field and any of {@link PersonQuestionEntity} is not correct 
	 * status should be set as "In Checking Process".
	 * Be default all started {@link PersonQuestionnaireEntity} should have status
	 * "In Progress"
	 */
	public void checkStatus() {
		if(checkPersonQuestionEntitiesIsCorrect()) {
			status = QuestionnaireStatusEnums.PASSED.getName();
		} else if(!checkPersonQuestionEntitiesIsCorrect()) {
			status = QuestionnaireStatusEnums.NOT_PASSED.getName();
		} else if(!checkQuestionsType() && !checkPersonQuestionEntitiesIsCorrect()) {
			status = QuestionnaireStatusEnums.IN_CHECKING_PROCESS.getName();
		} else {
			status = QuestionnaireStatusEnums.IN_PROGRESS.getName();
		}
	}
	
	/**
	 * Validate if all {@link PersonQuestionEntity} is correct. Return true if and only if
	 * all {@link PersonQuestionEntity} isCorrect field is true.
	 * @return
	 */
	private boolean checkPersonQuestionEntitiesIsCorrect() {
		for(PersonQuestionEntity questionEntity : Preconditions.checkNotNull(personQuestionEntities)) {
			if(questionEntity.isCorrect() == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Validate type of question. If one of this {@link QuestionTypeEnums} is equals to FIELD
	 * method will return false.
	 * @return boolean 
	 */
	private boolean checkQuestionsType() {
		for(PersonQuestionEntity questionEntity : Preconditions.checkNotNull(personQuestionEntities)) {
			if(Preconditions.checkNotNull(questionEntity.getQuestionEntity()).getType() == QuestionTypeEnums.FIELD) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "PersonQuestionnaireEntity [getQuestionnaireEntity()="
				+ getQuestionnaireEntity() == null ? "null" : getQuestionnaireEntity().toString() + ", getStatus()=" + getStatus()
				+ ", getId()=" + getId() + ", getOwnBy()=" + getOwnBy() == null ? "null" : getOwnBy().toString()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedDate()=" + getModifiedDate() + "]";
	}
 	
	
	
}
