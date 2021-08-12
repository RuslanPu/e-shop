package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "info_page")
public class InfoPage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_page")
    private String namePage;

    @Column(name = "desc_page")
    private String descPage;

    @Column(name = "title")
    private String title;

    @Column(name = "keywords")
    private String keywords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamePage() {
        return namePage;
    }

    public void setNamePage(String namePage) {
        this.namePage = namePage;
    }

    public String getDescPage() {
        return descPage;
    }

    public void setDescPage(String descPage) {
        this.descPage = descPage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoPage infoPage = (InfoPage) o;
        return Objects.equals(id, infoPage.id) && Objects.equals(namePage, infoPage.namePage) && Objects.equals(descPage, infoPage.descPage) && Objects.equals(title, infoPage.title) && Objects.equals(keywords, infoPage.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, namePage, descPage, title, keywords);
    }
}
