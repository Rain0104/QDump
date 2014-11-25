package org.data.art.qdump.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.data.art.qdump.config.AppConfig;
import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonRoleEnums;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Component
public class PersonTest {
	@Autowired
	private QDumpService qDumpService;
	private static PersonEntity person;
	
	@Before
	public void addPersonTest() {	
		person = new PersonEntity();
		//person.setId(1);
		person.setFirstname("Anastasiia");
		person.setLastname("Hrankina");
		person.setEmail("aaa@mail.ru");
		person.setLogin("nastya");
		person.setPass("nastyaa");
		person.setPersonRoleEnums(PersonRoleEnums.ADMIN);
		qDumpService.addPerson(person);
		System.out.println(qDumpService.getPerson(1));
	}
	
	@Test
	public void PersoNTest() {	
		System.out.println("Test");	
		System.out.println(qDumpService.getPerson(1));	
		
		person.setId(1);
		assertEquals(qDumpService.getPerson(1).toString(),
				person.toString());
	
		PersonEntity person2 = new PersonEntity();

		person2.setFirstname("Ivan");
		person2.setLastname("Ivanov");
		person2.setEmail("iva@mail.ru");
		person2.setLogin("ivan");
		person2.setPass("1234");
		person2.setPersonRoleEnums(PersonRoleEnums.USER);
		qDumpService.addPerson(person2);
		
		person2.setId(2);
		assertEquals(qDumpService.getPerson(2).toString(),
		person2.toString());
		
		PersonEntity person3 = new PersonEntity();

		person3.setFirstname("Petr");
		person3.setLastname("Petrov");
		person3.setEmail("petr@mail.ru");
		person3.setLogin("petr2003");
		person3.setPass("12345");
		person3.setPersonRoleEnums(PersonRoleEnums.USER);
		qDumpService.addPerson(person3);
		
		person3.setId(3);
		assertEquals(qDumpService.getPerson(3).toString(),
		person3.toString());
	
		System.out.println("Test GET_PERSONS");
		List<PersonEntity> persons = qDumpService.getPersons();
		for(PersonEntity pers : persons) {
			System.out.println(pers.getFirstname());
		}
		
		System.out.println("Test GET_PERSON_BY_EMAIL (petr@mail.ru)");
		persons = qDumpService.getPersonByEmail("petr@mail.ru");
		for(PersonEntity pers : persons) {
			System.out.println(pers.getFirstname());
		}
		
		System.out.println("Test GET_PERSON_BY_LOGIN (nastya)");
		persons = qDumpService.getPersonByLogin("nastya");
		for(PersonEntity pers : persons) {
			System.out.println(pers.getFirstname());
		}
		
		System.out.println("Test GET_PERSONS_BY_ROLE (User)");
		persons = qDumpService.getPersonByRole(PersonRoleEnums.USER);
		for(PersonEntity pers : persons) {
			System.out.println(pers.getFirstname());
		}
		
		System.out.println("Test GET_PERSONS_BY_ROLE (Admin)");
		persons = qDumpService.getPersonByRole(PersonRoleEnums.ADMIN);
		for(PersonEntity pers : persons) {
			System.out.println(pers.getFirstname());
		}
	}
}
