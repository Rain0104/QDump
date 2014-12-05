package org.data.art.qdump.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.data.art.qdump.enums.PersonRoleEnums;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="PERSONS")
@AttributeOverride(name="id", column=@Column(
		name="person_id", insertable=false, updatable=false))
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({@NamedQuery(name="getPersons", query="from PersonEntity"), 
	@NamedQuery(name="getPersonsByEmail", query="from PersonEntity where "
			+ "email =:email"),
	@NamedQuery(name="getPersonsByLogin", query="from PersonEntity where "
			+ "login =:login"),
	@NamedQuery(name="getPersonsByRole", query="from PersonEntity where "
		 	+ "role = :role"),
	@NamedQuery(name="deletePersons", query="delete PersonEntity"),
	@NamedQuery(name="deletePerson", query="delete PersonEntity where "
			+ "person_id = :id"),
	@NamedQuery(name="deletePersonByEmail", query="delete PersonEntity where "
			+ "email =:email"),
	@NamedQuery(name="deletePersonByLogin", query="delete PersonEntity where "
			+ "login =:login"),
	@NamedQuery(name="deletePersonsByRole", query="delete PersonEntity where "
			+ "role = :role")
})
public class PersonEntity extends HasModificationInfo{
	public final static String GET_PERSONS = "getPersons";
	public final static String GET_PERSONS_BY_EMAIL = "getPersonsByEmail";
	public final static String GET_PERSONS_BY_LOGIN = "getPersonsByLogin";
	public final static String GET_PERSONS_BY_ROLE = "getPersonsByRole";
	public final static String DELETE_PERSONS = "deletePersons";
	public final static String DELETE_PERSON = "deletePerson";
	public final static String DELETE_PERSON_BY_EMAIL = "getPersonsByEmail";
	public final static String DELETE_PERSON_BY_LOGIN = "getPersonsByLogin";
	public final static String DELETE_PERSONS_BY_ROLE = "getPersonsByRole";
	
		private String firstname;
		private String lastname;
		private String email;
		private String login;
		private String pass;
		private PersonRoleEnums role;
		
		public PersonEntity() {
			super();
		}
		
		public PersonEntity(String email, String pass){
			super();
			this.email = email;
			this.pass = pass;
		}

		@Override
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		public int getId() {
			return id;
		}
		
		@Override
		public void setId(int id) {
			this.id = id;
		}
		
		@Column(name="firstname",length=45)
		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		
		@Column(name="lastname",length=45)
		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		@Column(name="email",length=45,nullable=false,unique=true)
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Column(name="login",length=45,nullable=false,unique=true)
		public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		@Column(name="password",length=45,nullable=false)
		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}
		
		@Column(name="role",length=45)
		@Enumerated(EnumType.STRING)
		public PersonRoleEnums getPersonRoleEnums() {
			return role;
		}
		
		public void setPersonRoleEnums(PersonRoleEnums role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "PersonEntity [firstname=" + firstname + ", lastname="
					+ lastname + ", email=" + email + ", login=" + login
					+ ", pass=" + pass + ", role=" + role + ", id=" + id + "]";
		}

		
}
