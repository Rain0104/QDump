package org.dataart.qdump.entities.person;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.dataart.qdump.entities.questionnaire.AnswerEntity;
import org.dataart.qdump.entities.questionnaire.BaseEntity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "person_answers")
@AttributeOverride(name = "id", column = @Column(name = "id_person_answer", insertable = false, updatable = false))
@JsonAutoDetect
public class PersonAnswerEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 5266384349299279727L;
	private AnswerEntity answerEntity;
	private String personAnswer;
	private boolean marked;
	@JsonBackReference
	private PersonQuestionEntity personQuestionEntity;

	@OneToOne
	@JoinColumn(name = "id_answer")
	@JsonProperty("answer_entity")
	public AnswerEntity getAnswerEntity() {
		return answerEntity;
	}

	public void setAnswerEntity(AnswerEntity answerEntity) {
		this.answerEntity = answerEntity;
	}

	@Column(name = "person_answer", length = 500)
	@JsonProperty("person_answer")
	public String getPersonAnswer() {
		return personAnswer;
	}

	public void setPersonAnswer(String personAnswer) {
		this.personAnswer = personAnswer;
	}

	@Column(name = "marked", columnDefinition = "BIT(1) DEFAULT 0")
	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_person_question", referencedColumnName = "id_person_question")
	public PersonQuestionEntity getPersonQuestionEntity() {
		return personQuestionEntity;
	}

	public void setPersonQuestionEntity(
			PersonQuestionEntity personQuestionEntity) {
		this.personQuestionEntity = personQuestionEntity;
	}

	@Override
	public String toString() {
		return "PersonAnswerEntity [getAnswerEntity()=" + getAnswerEntity() == null ? "null" : getAnswerEntity().toString()
				+ ", getPersonAnswer()=" + getPersonAnswer() + ", isMarked()="
				+ isMarked() + ", getPersonQuestionEntity()="
				+ getPersonQuestionEntity() == null ? "null" : getPersonQuestionEntity().toString() + ", getId()=" + getId()
				+ ", getCreatedDate()="
				+ getCreatedDate() + ", getModifiedDate()=" + getModifiedDate()
				+ "]";
	}

	
}
