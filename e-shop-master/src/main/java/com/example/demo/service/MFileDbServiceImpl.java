package com.example.demo.service;

import com.example.demo.dao.MetaFileDBDao;
import com.example.demo.model.MetaFileDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MFileDbServiceImpl  implements MetaFileDbService{
    @Autowired
    private MetaFileDBDao mFileDao;

    @Transactional
    @Override
    public List<MetaFileDB> getAllFiles() {
        return mFileDao.getAllFiles();
    }

    @Transactional
    @Override
    public void add(MetaFileDB mFile) {
        mFileDao.add(mFile);
    }

    @Transactional
    @Override
    public void edit(MetaFileDB mFile) {
        mFileDao.edit(mFile);
    }

    @Transactional
    @Override
    public void delete(MetaFileDB mFile) {
        mFileDao.delete(mFile);
    }

    @Transactional
    @Override
    public MetaFileDB getFileById(Long id) {
        return mFileDao.getFileById(id);
    }

    @Transactional
    public String getFileIdByParam(String filename, byte[] data) {
        return mFileDao.getFileIdByParam(filename,data);
    }
}
