package com.example.demo.dao;

import com.example.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    public EntityManager entityManager;



    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> roleList = new ArrayList<>();
        roleList = entityManager.createQuery("Select role from Role role where role.name = :paramName")
                .setParameter("paramName",name)
                .getResultList();
        if (roleList.size() != 0) {
            return roleList.get(0);
        } else {
            return null;
        }



    }

    @Override
    public List<Role> getAllRole() {
        return entityManager.createQuery("select role from Role role").getResultList();
    }
}
