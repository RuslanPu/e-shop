package com.example.demo.dao;

import com.example.demo.model.ListImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListImageRepository extends JpaRepository<ListImage, Long> {
    @Query("select i from ListImage i where i.productId = ?1")
    List<ListImage> getListImageByProductId(String id);
}
