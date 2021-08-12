package com.example.demo.dao;

import com.example.demo.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FileDBDao extends JpaRepository<FileDB, String> {

}
