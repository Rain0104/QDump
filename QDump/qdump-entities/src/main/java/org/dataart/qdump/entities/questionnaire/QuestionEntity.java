package org.dataart.qdump.entities.questionnaire;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.dataart.qdump.entities.enums.QuestionTypeEnums;

@Entity
@Table(name = "questions")
@AttributeOverride(name = "id", column = @Column(name = "id_question", insertable = false, updatable = false))
@NamedQueries({
	@NamedQuery(name = "QuestionEntity.getQuestionByName", query = "FROM QuestionEntity q "
			+ "WHERE q.name = ?1"),
	@NamedQuery(name = "QuestionEntity.getQuestionByQuestionnaireId", query = "FROM QuestionEntity q  "
					+ "WHERE q.questionnaireEntity.id = ?1")		
	 })
public class QuestionEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 7827573669263895832L;
	private String question;
	private QuestionTypeEnums type;
	private List<AnswerEntity> answerEntities;
	private QuestionnaireEntity questionnaireEntity;

	@Column(name = "question", nullable = false, length = 1500)
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Column(name = "vizualization_type", nullable = false)
	@Enumerated(EnumType.STRING)
	public QuestionTypeEnums getType() {
		return type;
	}

	public void setType(QuestionTypeEnums type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questionEntity", fetch = FetchType.LAZY)
	public List<AnswerEntity> getAnswerEntities() {
		return answerEntities;
	}

	public void setAnswerEntities(List<AnswerEntity> answerEntities) {
		this.answerEntities = answerEntities;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_questionnaire", referencedColumnName = "id_questionnaire")
	public QuestionnaireEntity getQuestionnaireEntity() {
		return questionnaireEntity;
	}

	public void setQuestionnaireEntity(QuestionnaireEntity questionnaireEntity) {
		this.questionnaireEntity = questionnaireEntity;
	}

	@Override
	public String toString() {
		return "QuestionEntity [getQuestion()=" + getQuestion()
				+ ", getType()=" + getType() + ", getQuestionnaireEntity()="
				+ getQuestionnaireEntity() == null ? "null" : getQuestionnaireEntity().toString() + ", getId()=" + getId()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedDate()=" + getModifiedDate() + "]";
	}

	
}

