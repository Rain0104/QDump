package org.dataart.qdump.entities.person;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.dataart.qdump.entities.questionnaire.BaseEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

@MappedSuperclass
public abstract class PersonQuestionnaireBaseEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 7666829487657683004L;
	@JsonBackReference
	private PersonEntity ownBy;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ownBy", referencedColumnName = "id_person", updatable = false)
	public PersonEntity getOwnBy() {
		return ownBy;
	}

	public void setOwnBy(PersonEntity ownBy) {
		this.ownBy = ownBy;
	}
}
