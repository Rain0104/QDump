package org.data.art.qdump.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="QUESTIONS")
public class QuestionEntity implements PlainModificationObject{
			
	private static final long serialVersionUID = 1707348230260564450L;

		@Column(name = "ID", nullable = false)
		private String questionId;
		
	    @Column(name = "QUESTION")
		private String question;
	    
	    @Column(name = "VIZUALIZATION_TYPE")
		private String vizualizationType;
	    
	    @Column(name = "QUESTIONNAIRE_ID", nullable = false)		
		private String questionnaire_id;
		
		@OneToMany(mappedBy = "QUESTION_ID")
		private List<Answer> answers;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "ID")
		private QuestionnaireEntity questionnaire; 
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "MODIFIED_BY")
		private PersonEntity person;
		
		@Embedded
	    private final HasModificationInfo modification = new HasModificationInfo();
		
		public List<Answer> getAnswers() {
			return answers;
		}

		public void setAnswers(List<Answer> answers) {
			this.answers = answers;
		}

		public QuestionnaireEntity getQuestionnaire() {
			return questionnaire;
		}

		public void setQuestionnaire(QuestionnaireEntity questionnaire) {
			this.questionnaire = questionnaire;
		}

		public PersonEntity getPerson() {
			return person;
		}

		public void setPerson(PersonEntity person) {
			this.person = person;
		}

		public String getQuestionId() {
			return questionId;
		}

		public void setQuestionId(String questionId) {
			this.questionId = questionId;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getVizualizationType() {
			return vizualizationType;
		}

		public void setVizualizationType(String vizualizationType) {
			this.vizualizationType = vizualizationType;
		}

		public String getQuestionnaire_id() {
			return questionnaire_id;
		}

		public void setQuestionnaire_id(String questionnaire_id) {
			this.questionnaire_id = questionnaire_id;
		}

		@Override
		public HasModificationInfo getModification() {
			return modification;
		}

}
