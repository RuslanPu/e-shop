package com.example.demo.service;

import com.example.demo.dao.FileDBDao;
import com.example.demo.model.FileDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBDao fileDBDao;

    @Transactional
    public FileDB store(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        return fileDBDao.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBDao.findById(id).get();
    }
    public Stream<FileDB> getAllFiles() {
        return fileDBDao.findAll().stream();
    }
}
