package org.dataart.qdump.persistence;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.dataart.qdump.entities.enums.PersonGroupEnums;
import org.dataart.qdump.entities.person.PersonEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {
	public static void main(String[] args) {
		PersonEntity entity = new PersonEntity();
		entity.setCreatedDate(new Date());
		entity.setEmail("test@gmail.com");
		entity.setEnabled(true);
		entity.setGender((byte) 1);
		entity.setLogin("login");
		entity.setModifiedDate(new Date(System.currentTimeMillis() + 100000));
		entity.setName("name");
		entity.setPassword("password");
		entity.setPersonGroup(PersonGroupEnums.USER);
		entity.setSurname("surname");
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(new File("/Users/artemvlasov/Documents/json.txt"), entity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
