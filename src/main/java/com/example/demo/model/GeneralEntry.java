package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "generalEntry")
public class GeneralEntry {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alt_image")
    private String altImage;

    @Column(name = "image_id")
    private String imageId;

    @Column(name = "caption")
    private String caption;

    @Column(name = "text")
    private String text;

    @Column(name = "category")
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GeneralEntry() {
    }

    public GeneralEntry(String altImage, String imageId, String caption, String text) {
        this.altImage = altImage;
        this.imageId = imageId;
        this.caption = caption;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralEntry generalEntry = (GeneralEntry) o;
        return Objects.equals(id, generalEntry.id) &&
                Objects.equals(altImage, generalEntry.altImage) &&
                Objects.equals(imageId, generalEntry.imageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, altImage, imageId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAltImage() {
        return altImage;
    }

    public void setAltImage(String altImage) {
        this.altImage = altImage;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}