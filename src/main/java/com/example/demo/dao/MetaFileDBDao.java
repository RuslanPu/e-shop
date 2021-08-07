package com.example.demo.dao;

import com.example.demo.model.MetaFileDB;

import java.util.List;

public interface MetaFileDBDao  {
    List<MetaFileDB> getAllFiles();
    void add(MetaFileDB metaFileDB);
    void edit(MetaFileDB metaFileDB);
    void delete(MetaFileDB metaFileDB);
    MetaFileDB getFileById(Long id);
    String getFileIdByParam(String fileName, byte[] dataFile);

}
