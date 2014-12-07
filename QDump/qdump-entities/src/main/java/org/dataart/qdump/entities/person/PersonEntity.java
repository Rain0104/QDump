package org.dataart.qdump.entities.person;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.validator.routines.EmailValidator;
import org.dataart.qdump.entities.enums.PersonGroupEnums;
import org.dataart.qdump.entities.questionnaire.QuestionnaireBaseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "persons")
@AttributeOverride(name = "id", column = @Column(name = "id_person", insertable = false, updatable = false))
@JsonAutoDetect
@JsonIgnoreProperties({ "password" })
public class PersonEntity extends QuestionnaireBaseEntity implements Serializable {
	private static final long serialVersionUID = -219526512840281300L;
	private String firstname;
	private String lastname;
	private String email;
	private String login;
	private String password;
	@JsonProperty("enabled")
	private boolean isEnabled;
	private byte gender;
	@JsonProperty("person_group")
	private PersonGroupEnums personGroup = PersonGroupEnums.USER;
	@JsonManagedReference
	@JsonProperty("person_questionnaire_entities")
	private List<PersonQuestionnaireEntity> personQuestionnaireEntities;

	public PersonEntity() {
		super();
	}

	public PersonEntity(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	/**
	 * Length is set as requirement of Data Standards Catalogue. Name
	 * 
	 * @return
	 */
	@Column(name = "firstname", length = 35)
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = validateName(firstname);
	}

	@Column(name = "lastname", length = 35)
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = validateSurname(lastname);
	}

	@Column(name = "email", nullable = false, unique = true, length = 254)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		validateEmail(email);
		this.email = email;
	}

	@Column(name = "login", nullable = false, unique = true, length = 35)
	public String getLogin() {
		validateName(login);
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", nullable = false, length = 255)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	/**
	 * Set true if this use is verified by ADMIN {@link PersonGroupEnums} after
	 * email confirmation
	 * 
	 * @param isEnabled
	 */
	@Column(name = "enabled", nullable = false, columnDefinition = "BIT(1) DEFAULT 0")
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * Gender has three values 1 = Male, 2 = Female, 9 = Not Specified. By
	 * default - 9. This values is equals to Data Standards Catalogue.
	 * 
	 * @param gender
	 */
	@Column(name = "gender", columnDefinition = "TINYINT DEFAULT 9")
	public byte getGender() {
		return gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	/**
	 * All user that was created should have {@link PersonGroupEnums} by default
	 * persons has USER group.
	 * 
	 * @return person group for the current person
	 */
	@Column(name = "person_group", columnDefinition = "VARCHAR(10) DEFAULT 'USER'", nullable = false)
	@Enumerated(EnumType.STRING)
	public PersonGroupEnums getPersonGroup() {
		return personGroup;
	}

	public void setPersonGroup(PersonGroupEnums personGroup) {
		this.personGroup = personGroup;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ownBy", orphanRemoval = true)
	public List<PersonQuestionnaireEntity> getPersonQuestionnaireEntities() {
		return personQuestionnaireEntities;
	}

	public void setPersonQuestionnaireEntities(
			List<PersonQuestionnaireEntity> personQuestionnaireEntities) {
		this.personQuestionnaireEntities = personQuestionnaireEntities;
	}

	/**
	 * Name validator, persisted name should contains only letters from A to Z .
	 * Max length 35
	 * 
	 * @param name
	 */
	private String validateName(String name) {
		if (!name.matches("[A-Z][a-zA-Z]*") && name != null
				&& name.length() > 35) {
			throw new RuntimeException("Invalid input name, size should be "
					+ "between 3 and 35 characters");
		} else {
			return name;
		}
	}

	/**
	 * Surname validator, persisted surname should contains letter from A to Z,
	 * spaces, -, '. Max length 35
	 * 
	 * @param surname
	 *            Inserted surname for person
	 * @return
	 */
	private String validateSurname(String surname) {
		if (!surname.matches("[a-zA-z]+([ '-][a-zA-Z]+)*") && surname != null
				&& surname.length() > 35) {
			throw new RuntimeException("Invalid input surname, size should be "
					+ "between 3 and 35 characters");
		} else {
			return surname;
		}
	}

	/**
	 * Validate email with help of Apache Commons Validator
	 * 
	 * @param email
	 */
	private void validateEmail(String email) {
		if (!EmailValidator.getInstance().isValid(email)) {
			throw new RuntimeException("You enter invalid email address");
		}
	}

	@Override
	public String toString() {
		return "PersonEntity [getName()=" + getFirstname() + ", getSurname()="
				+ getLastname() + ", getEmail()=" + getEmail()
				+ ", getLogin()=" + getLogin() + ", getPassword()="
				+ getPassword() + ", isEnabled()=" + isEnabled()
				+ ", getGender()=" + getGender() + ", getPersonGroup()="
				+ getPersonGroup() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getModifiedDate()=" + getModifiedDate() + "]";
	}

}
