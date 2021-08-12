package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;

public class UploadForm {
    private Long id;

    private String name;

    private String category;

    private String cost;

    private String descOffer;

    private String altImage;

    private String caption;

    private String text;

    private String nameCompany;

    private String telephone;

    private String email;

    private String timeWork;

    private String aboutCompany;

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public String getAboutCompany() {
        return aboutCompany;
    }

    public void setAboutCompany(String aboutCompany) {
        this.aboutCompany = aboutCompany;
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

    public String getAltImage() {
        return altImage;
    }

    public void setAltImage(String altImage) {
        this.altImage = altImage;
    }

    private MultipartFile[] file;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadForm that = (UploadForm) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(cost, that.cost) && Objects.equals(descOffer, that.descOffer) && Arrays.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, category, cost, descOffer);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    public UploadForm(String name, String category, String cost, String descOffer, String altImage, MultipartFile[] file) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.descOffer = descOffer;
        this.altImage = altImage;
        this.file = file;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescOffer() {
        return descOffer;
    }

    public void setDescOffer(String descOffer) {
        this.descOffer = descOffer;
    }

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }
}