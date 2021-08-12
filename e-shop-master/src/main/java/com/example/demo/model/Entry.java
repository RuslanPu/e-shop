package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entity")
public class Entry {
    @Id
    @NotNull
    @Column(name = "unique_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "category")
    private String category;

    @NotNull
    @Column(name = "alt_image")
    private String altImage;


}
