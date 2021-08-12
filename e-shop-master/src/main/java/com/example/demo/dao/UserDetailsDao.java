package com.example.demo.dao;

import com.example.demo.model.User;

public interface UserDetailsDao {

    User getUserByName(String username);

}
