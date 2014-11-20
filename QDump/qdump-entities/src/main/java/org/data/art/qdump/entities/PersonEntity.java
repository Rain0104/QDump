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

@Entity
@Table(name="PERSONS")
@AttributeOverride(name="id", column=@Column(
		name="person_id", insertable=false, updatable=false))
/*
@NamedQueries({
	@NamedQuery(name = "Person.deleteByEmail", query = "DELETE FROM Person p "
			+ "WHERE p.email = ?1"),
	@NamedQuery(name = "Person.deleteByLogin", query = "DELETE FROM Person p "
			+ "WHERE p.login = ?1"),
	@NamedQuery(name = "Person.deleteByRole", query = "DELETE FROM Person p "
			+ "WHERE p.role = ?1"),
	@NamedQuery(name = "Person.findByEmail", query = "FROM Person p "
			+ "WHERE p.email = ?1"),
	@NamedQuery(name = "Person.findByLogin", query = "FROM Person p "
			+ "WHERE p.login = ?1"),
	@NamedQuery(name = "Person.findByRole", query = "FROM Person p "
			+ "WHERE p.role = ?1") 
})
*/
public class PersonEntity extends HasModificationInfo{
	
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

}
