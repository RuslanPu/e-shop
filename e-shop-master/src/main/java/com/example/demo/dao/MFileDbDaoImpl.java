package com.example.demo.dao;

import com.example.demo.model.FileDB;
import com.example.demo.model.MetaFileDB;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MFileDbDaoImpl implements MetaFileDBDao{
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public List<MetaFileDB> getAllFiles() {
        return entityManager.createQuery("select files from MetaFileDB files").getResultList();
    }

    @Override
    public void add(MetaFileDB metaFileDB) {
        entityManager.persist(metaFileDB);
    }

    @Override
    public void edit(MetaFileDB metaFileDB) {
        entityManager.merge(metaFileDB);
    }

    @Override
    public void delete(MetaFileDB file) {
        if(file != null) {
            Query query = entityManager.createQuery("DELETE FROM MetaFileDB e WHERE e.id = :paramId");
            query.setParameter("paramId",file.getId());
            query.executeUpdate();

        }
    }

    @Override
    public MetaFileDB getFileById(Long id) {
        return entityManager.find(MetaFileDB.class, id);
    }

    public String getFileIdByParam(String fileName, byte[] dataFile) {
        Query query = entityManager.createQuery("select f from FileDB f where f.name = :paramName and f.data = :paramData");
        List<FileDB> resultList= query.setParameter("paramName", fileName)
                .setParameter("paramData", dataFile)
                .getResultList();
        return resultList.get(0).getId().toString();

    }
}
