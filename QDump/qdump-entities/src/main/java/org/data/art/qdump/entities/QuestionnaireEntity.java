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
@Table(name="QUESTIONNAIRE")
public class QuestionnaireEntity implements PlainModificationObject{

	private static final long serialVersionUID = -3930985889654129892L;

		@Column(name = "ID", nullable = false)
		private String questionnaireId;
		
	    @Column(name = "QUESTIONNAIRE_NAME")
		private String questionnaireName;
	    
	    @Column(name = "DESCRIPTION")
		private String description;
	    
	    @Column(name = "IS_PUBLISHED", nullable = false)		
		private String isPublished;

	    @OneToMany(mappedBy = "questionnaire_id", fetch = FetchType.LAZY)
		private List<QuestionEntity> questions;
		
	    @ManyToOne(targetEntity = PersonEntity.class)
		@JoinColumn(table = "person_questionnaire", name = "idPerson", referencedColumnName = "idPerson")
		private PersonEntity personEntity;
	    
		@Embedded
	    private final HasModificationInfo modification = new HasModificationInfo();
				
	    public List<QuestionEntity> getQuestions() {
			return questions;
		}

		public void setQuestions(List<QuestionEntity> questions) {
			this.questions = questions;
		}

		public PersonEntity getPersonEntity() {
			return personEntity;
		}

		public void setPersonEntity(PersonEntity personEntity) {
			this.personEntity = personEntity;
		}

		public String getQuestionnaireId() {
			return questionnaireId;
		}

		public void setQuestionnaireId(String questionnaireId) {
			this.questionnaireId = questionnaireId;
		}

		public String getQuestionnaireName() {
			return questionnaireName;
		}

		public void setQuestionnaireName(String questionnaireName) {
			this.questionnaireName = questionnaireName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getIsPublished() {
			return isPublished;
		}

		public void setIsPublished(String isPublished) {
			this.isPublished = isPublished;
		}

		@Override
		public HasModificationInfo getModification() {		
			return modification;
		}   
	    		
}
