package org.data.art.qdump.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
@Embeddable
public class HasModificationInfo implements Serializable {
	
	protected int id;
	private PersonEntity createdBy;
	private PersonEntity modifiedBy;
	private Date createdDate;
	//private Date createdAt;
	//private Date modifiedAt;
	
	public HasModificationInfo() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@OneToOne
	@JoinColumn(name="created_by", referencedColumnName="person_id")
	public PersonEntity getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(PersonEntity createdBy) {
		this.createdBy = createdBy;
	}
	
	@OneToOne
	@JoinColumn(name="modified_by", referencedColumnName="person_id")
	public PersonEntity getModifiedBy() {
		return modifiedBy;
	}
	
	public void setModifiedBy(PersonEntity modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/*	
	@Column(name="created_at", nullable=false)
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name="modified_at")
	public Date getModifiedAt() {
		return modifiedAt;
	}
	
	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	*/
}
