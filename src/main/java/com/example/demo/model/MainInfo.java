package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "main_info")
public class MainInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_company")
    private String nameCompany;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "time_work")
    private String timeWork;

    @Column(name = "about_company")
    private String aboutCompany;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public MainInfo() {}

    public MainInfo(Long id, String nameCompany, String telephone, String email, String timeWork, String aboutCompany) {
        this.id = id;
        this.nameCompany = nameCompany;
        this.telephone = telephone;
        this.email = email;
        this.timeWork = timeWork;
        this.aboutCompany = aboutCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainInfo mainInfo = (MainInfo) o;
        return Objects.equals(id, mainInfo.id) && Objects.equals(nameCompany, mainInfo.nameCompany) && Objects.equals(telephone, mainInfo.telephone) && Objects.equals(email, mainInfo.email) && Objects.equals(timeWork, mainInfo.timeWork) && Objects.equals(aboutCompany, mainInfo.aboutCompany);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameCompany, telephone, email, timeWork, aboutCompany);
    }
}
