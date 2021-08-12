package com.example.demo.service;

import com.example.demo.model.MetaFileDB;

import java.util.List;

public interface MetaFileDbService {
    List<MetaFileDB> getAllFiles();
    void add(MetaFileDB metaFileDB);
    void edit(MetaFileDB metaFileDB);
    void delete(MetaFileDB metaFileDB);
    MetaFileDB getFileById(Long id);
    String getFileIdByParam(String filename, byte[] data);
}