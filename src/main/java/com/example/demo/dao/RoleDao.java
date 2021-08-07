package com.example.demo.dao;

import com.example.demo.model.Role;

import java.util.List;

public interface RoleDao {
    void add(Role role);
    Role getRoleById(Long id);
    Role getRoleByName(String name);
    List<Role> getAllRole();

}
