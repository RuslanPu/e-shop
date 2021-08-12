package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    public EntityManager entityManager;


    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUser() {
        return entityManager.createQuery("select user from User user").getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        if(user != null) {
            Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :paramId");
            query.setParameter("paramId",user.getId());
            query.executeUpdate();

        }

    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public boolean unicEmail(String email) {
        List<String> listQuery =  entityManager.createQuery("select U.email from User U where U.email = :paramEmail")
                .setParameter("paramEmail", email)
                .getResultList();
        if (listQuery.size() > 0) {
            return false;
        } else {
            return true;
        }

    }
}

