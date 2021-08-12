package com.example.demo.dao;

import com.example.demo.model.MainInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainInfoRepository extends JpaRepository<MainInfo, Long> {
}
