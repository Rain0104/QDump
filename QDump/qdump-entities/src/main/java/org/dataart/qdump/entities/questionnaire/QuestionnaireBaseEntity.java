package org.dataart.qdump.entities.questionnaire;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.dataart.qdump.entities.person.PersonEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
public abstract class QuestionnaireBaseEntity implements Serializable{
	private static final long serialVersionUID = -1310872166991256747L;
	protected long id;
	private Date createdDate;
	private Date modifiedDate;
	private PersonEntity createdBy;
	private PersonEntity modifiedBy;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "created_date", nullable = false)
	@JsonProperty("created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "modified_date", nullable = true)
	@JsonProperty("modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@OneToOne
	@JoinColumn(name = "created_by", referencedColumnName = "id_person", nullable = true)
	@JsonProperty("created_by")
	public PersonEntity getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(PersonEntity createdBy) {
		this.createdBy = createdBy;
	}

	@OneToOne
	@JoinColumn(name = "modified_by", referencedColumnName = "id_person", nullable = true)
	@JsonProperty("modified_by")
	public PersonEntity getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(PersonEntity modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}

