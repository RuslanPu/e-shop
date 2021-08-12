package com.example.demo.service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDAO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.add(role);
    }



    @Transactional
    @Override
    public boolean unicEmail(String email) {
        return userDAO.unicEmail(email);
    }

    @Transactional
    @Override
    public List<Role> getAllRole() {
        return roleDao.getAllRole();
    }

    @Transactional
    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Transactional
    @Override
    public void add(User user, String[] checkboxRoles) {
        List<Role> roles = new ArrayList<>();
        parseString(roles, checkboxRoles);
        user.setRoles(roles);
        userDAO.add(user);
    }

    @Transactional
    @Override
    public void edit(User user, String[] checkedRoles) {
        List<Role> roles = new ArrayList<>();
        parseString(roles,checkedRoles);
        user.setRoles(roles);
        userDAO.edit(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    void parseString(List<Role> roles, String[] checkboxRoles) {
        for (String role : checkboxRoles) {
            Role roleDb = roleDao.getRoleByName(role);
            if (roleDb != null) {
                roles.add(roleDb);
            }
        }
    }
}

