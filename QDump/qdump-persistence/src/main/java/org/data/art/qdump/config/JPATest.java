package org.data.art.qdump.config;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.data.art.qdump.entities.PersonEntity;
import org.data.art.qdump.enums.PersonRoleEnums;

public class JPATest {
	public static void main(String[] args) throws ClassNotFoundException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("qdump");
		EntityManager em = emf.createEntityManager();
		try {
			PersonEntity person = new PersonEntity();
			person.setFirstname("Nastya");
			person.setLastname("Hrankina");
			person.setEmail("aa@mail.ru");
			person.setPersonRoleEnums(PersonRoleEnums.ADMIN);
			person.setLogin("nasty");
			person.setPass("qq12");
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
			
			person = new PersonEntity();
			person.setFirstname("sss");
			person.setLastname("fff");
			person.setEmail("fff@mail.ru");
			person.setPersonRoleEnums(PersonRoleEnums.ADMIN);
			person.setLogin("sss");
			person.setPass("www");
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();
			
			em.getTransaction().begin();
			TypedQuery<PersonEntity> query = 
					em.createQuery("select p from PersonEntity p ", 
							PersonEntity.class);
			List<PersonEntity> persons = query.getResultList();
			for(PersonEntity pers : persons) {
				System.out.println(pers.getFirstname());
			}

			em.getTransaction().commit();
		} finally {
			if(em != null) {
				em.close();
			}
			emf.close();
		}
	}
}
		
