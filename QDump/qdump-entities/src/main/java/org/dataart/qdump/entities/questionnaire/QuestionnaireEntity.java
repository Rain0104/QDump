package org.dataart.qdump.entities.questionnaire;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "questionnaires")
@AttributeOverrides(value = {
		@AttributeOverride(name = "id", column = @Column(name = "id_questionnaire", insertable = false, updatable = false)),
		@AttributeOverride(name = "created_by", column = @Column(name = "created_by", insertable = false, updatable = false, nullable = false)) })
@JsonAutoDetect
public class QuestionnaireEntity extends QuestionnaireBaseEntity implements Serializable{
	private static final long serialVersionUID = 8952388499186170808L;
	private String name;
	private String description;
	private boolean isPublished;
	private List<QuestionEntity> questionEntities;

	@Column(name = "name", nullable = false, length = 1000)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 4500)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "is_published", columnDefinition = "BIT(1) DEFAULT 0")
	@JsonProperty("published")
	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnaireEntity", fetch = FetchType.EAGER, orphanRemoval = true)
	@JsonProperty("question_entities")
	public List<QuestionEntity> getQuestionEntities() {
		return questionEntities;
	}

	public void setQuestionEntities(List<QuestionEntity> questionEntities) {
		this.questionEntities = questionEntities;
	}

	@Override
	public String toString() {
		return "QuestionnaireEntity [getName()=" + getName()
				+ ", getDescription()=" + getDescription() + ", isPublished()="
				+ isPublished() + ", getId()=" + getId() + ", getCreatedBy()="
				+ getCreatedBy() == null ? "null" : getCreatedBy().toString() + ", getModifiedBy()=" + getModifiedBy() == null ? "null" : getModifiedBy().toString()
				+ ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedDate()=" + getModifiedDate() + "]";
	}

	
}

