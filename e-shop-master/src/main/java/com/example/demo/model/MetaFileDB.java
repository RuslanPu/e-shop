package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "offer")
public class MetaFileDB {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name_offer")
    private String nameOffer;

    @Column(name = "desc_offer")
    private String descOffer;

    @Column(name = "price_offer")
    private String priceOffer;

    @Column(name = "offer_category")
    private String offerCategory;

    @Column(name = "alt_image")
    private String altImage;

    public String getAltImage() {
        return altImage;
    }

    public void setAltImage(String altImage) {
        this.altImage = altImage;
    }

    public String getOfferCategory() {
        return offerCategory;
    }

    public void setOfferCategory(String offerCategory) {
        this.offerCategory = offerCategory;
    }

    @Column(name = "image_id")
    private String imageId;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public MetaFileDB() {}

    public MetaFileDB(String nameOffer, String descOffer, String priceOffer, String category, String imageId, String altImage) {
        this.nameOffer = nameOffer;
        this.descOffer = descOffer;
        this.priceOffer = priceOffer;
        this.offerCategory = category;
        this.imageId = imageId;
        this.altImage = altImage;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOffer() {
        return nameOffer;
    }

    public void setNameOffer(String nameOffer) {
        this.nameOffer = nameOffer;
    }

    public String getDescOffer() {
        return descOffer;
    }

    public void setDescOffer(String descOffer) {
        this.descOffer = descOffer;
    }

    public String getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(String priceOffer) {
        this.priceOffer = priceOffer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetaFileDB that = (MetaFileDB) o;
        return Objects.equals(id, that.id) && Objects.equals(nameOffer, that.nameOffer) && Objects.equals(descOffer, that.descOffer) && Objects.equals(priceOffer, that.priceOffer) && Objects.equals(imageId, that.imageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOffer, descOffer, priceOffer, imageId);
    }
}
