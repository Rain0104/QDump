package org.dataart.qdump.entities.questionnaire;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "answers")
@AttributeOverrides(value = { 
		@AttributeOverride(name = "id", column = @Column(name = "id_answer", insertable = false, updatable = false))})
@JsonAutoDetect
@JsonIgnoreProperties({"createdDate", "modifiedDate"})
public class AnswerEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = -5973094404031746982L;
	private String answer;
	private boolean isCorrect;
	@JsonBackReference
	private QuestionEntity questionEntity;

	@Column(name = "answer", length = 500)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "is_correct", columnDefinition = "BIT(1) DEFAULT 0")
	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_question", referencedColumnName = "id_question")
	@JsonProperty("question_entity")
	public QuestionEntity getQuestionEntity() {
		return questionEntity;
	}

	public void setQuestionEntity(QuestionEntity questionEntity) {
		this.questionEntity = questionEntity;
	}

	@Override
	public String toString() {
		return "AnswerEntity [getAnswer()=" + getAnswer() + ", isCorrect()="
				+ isCorrect() + ", getQuestionEntity()=" + getQuestionEntity() == null ? "null" : getQuestionEntity().toString()
				+ ", getId()=" + getId() + ", getCreatedBy()="
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedDate()=" + getModifiedDate() + "]";
	}
}

