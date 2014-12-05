package org.dataart.qdump.entities.person;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
public abstract class PersonQuestionnaireBaseEntity implements Serializable {
	private static final long serialVersionUID = 7666829487657683004L;
	private long id;
	private Date createdDate;
	private Date modifiedDate;
	@JsonBackReference
	private PersonEntity ownBy;

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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ownBy", referencedColumnName = "id_person")
	public PersonEntity getOwnBy() {
		return ownBy;
	}

	public void setOwnBy(PersonEntity ownBy) {
		this.ownBy = ownBy;
	}
}
