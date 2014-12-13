package org.dataart.qdump.persistence.dstest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dataart.qdump.entities.dstest.UserEntity;

@Stateless
public class UserEntityDao {
    @PersistenceContext(unitName = "qdump-persistence")
    private EntityManager em;
 
    public void save(UserEntity user){
        em.persist(user);
    }
 
    public List<UserEntity> findAll(){
        return em.createQuery("select u from User u")
                .getResultList();
    }
}
