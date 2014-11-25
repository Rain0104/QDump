package org.data.art.qdump.service;

import static org.junit.Assert.assertEquals;


import java.util.Date;

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
	}
	
}
