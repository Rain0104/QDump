package org.data.art.qdump.persistence;

import java.util.List;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonRoleEnums;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonCrudRepository extends CrudRepository<PersonEntity, Integer> {
	/*
	public void deleteByEmail(String email);
	public void deleteByLogin(String username);
	public void deleteByRole(PersonRoleEnums role);
	
	public List<PersonEntity> findByEmail(String email);
	public List<PersonEntity> findByLogin(String login);
	public List<PersonEntity> findByRole(PersonRoleEnums role);
	*/
	
}