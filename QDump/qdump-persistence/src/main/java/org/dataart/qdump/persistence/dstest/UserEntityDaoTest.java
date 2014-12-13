package org.dataart.qdump.persistence.dstest;

import org.dataart.qdump.entities.dstest.UserEntity;

public class UserEntityDaoTest {
	public static void main(String[] args) {
		UserEntityDao dao = new UserEntityDao();
		UserEntity entity = new UserEntity();
		entity.setLogin("hello");
		entity.setPassword("hello");
		dao.save(entity);
	}
}
