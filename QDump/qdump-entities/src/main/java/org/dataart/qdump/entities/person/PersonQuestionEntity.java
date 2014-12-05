package org.dataart.qdump.entities.person;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dataart.qdump.entities.questionnaire.AnswerEntity;
import org.dataart.qdump.entities.questionnaire.BaseEntity;
import org.dataart.qdump.entities.questionnaire.QuestionEntity;

import com.google.common.base.Preconditions;

@Entity
@Table(name = "person_questions")
@AttributeOverride(name = "id", column = @Column(name = "id_person_question", insertable = false, updatable = false))
public class PersonQuestionEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -6691017410211190245L;
	private PersonQuestionnaireEntity personQuestionnaireEntity;
	private QuestionEntity questionEntity;
	private boolean isCorrect;
	private List<PersonAnswerEntity> personAnswerEntities;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person_questionnaire", referencedColumnName = "id_person_questionnaire")
	public PersonQuestionnaireEntity getPersonQuestionnaireEntity() {
		return personQuestionnaireEntity;
	}

	public void setPersonQuestionnaireEntity(
			PersonQuestionnaireEntity personQuestionnaireEntity) {
		this.personQuestionnaireEntity = personQuestionnaireEntity;
	}

	@OneToOne
	@JoinColumn(name = "id_question")
	public QuestionEntity getQuestionEntity() {
		return questionEntity;
	}

	public void setQuestionEntity(QuestionEntity questionEntity) {
		this.questionEntity = questionEntity;
	}

	@Column(name = "correct", columnDefinition = "BIT(1) DEFAULT 0")
	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "personQuestionEntity", orphanRemoval = true)
	public List<PersonAnswerEntity> getPersonAnswerEntities() {
		return personAnswerEntities;
	}

	public void setPersonAnswerEntities(
			List<PersonAnswerEntity> personAnswerEntities) {
		this.personAnswerEntities = personAnswerEntities;
	}
	
	/**
	 * Check if question is correct. Question is set as correct when all answers
	 * is correct.
	 * @return
	 */
	public void checkQuestionIsCorrect() {
		isCorrect = checkPersonAnswersIsCorrect();
	}

	/**
	 * Validate {@link PersonAnswerEntity} if it is correct. Return true if and only if all
	 * {@link PersonAnswerEntity} that was marked by {@link PersonEntity} has a corresponding 
	 * correct {@link AnswerEntity}.
	 * Validator return false if one of the {@link PersonAnswerEntity} that was marked by user
	 * has no corresponding correct {@link AnswerEntity} or if one of {@link PersonAnswerEntity} 
	 * is not marked by {@link PersonEntity} by has corresponding correct {@link AnswerEntity}.
	 * @return boolean
	 */
	private boolean checkPersonAnswersIsCorrect() {
		for(PersonAnswerEntity answerEntity : Preconditions.checkNotNull(personAnswerEntities)) {
			if((answerEntity.isMarked() 
					&& !Preconditions.checkNotNull(answerEntity.getAnswerEntity()).isCorrect()) 
				|| (!answerEntity.isMarked() 
					&& Preconditions.checkNotNull(answerEntity.getAnswerEntity().isCorrect()))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "PersonQuestionEntity [getPersonQuestionnaireEntity()="
				+ getPersonQuestionnaireEntity() == null ? "null" : getPersonQuestionnaireEntity().toString() + ", getQuestionEntity()="
				+ getQuestionEntity() == null ? "null" : getQuestionEntity().toString() + ", isCorrect()=" + isCorrect()
				+ ", getId()=" + getId()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedDate()=" + getModifiedDate() + "]";
	}
	
	
	
}
