package com.example.demo.dao;

import com.example.demo.model.GeneralEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepository extends JpaRepository<GeneralEntry, Long> {
    @Query("SELECT entry FROM GeneralEntry entry where entry.category = ?1")
    List<GeneralEntry> getEntryByCategory(String category);
}
