package com.qcphotos.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;




@Entity
public class Product {
    @EmbeddedId
    private ProductIdentifier productId;

    @MapsId("storefrontId")
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)  // We don't want to destroy a storefront if it's orphaned
    private Storefront storefront;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // We do want to destroy a QcPhotoSet if it's orphaned
    private final Set<QcPhotoSet> qcPhotoSets = new HashSet<>();

    private float price;
    private float domesticFreight;
    private float width;
    private float length;
    private float height;
    private String thumbnailUrl;
    private Date timeFetched;


    protected Product() {} // JPA

    public Product(String listingId, Storefront storefront) {
        this.productId = new ProductIdentifier(listingId, storefront.getId());
        this.storefront = storefront;
        this.timeFetched = new Date(System.currentTimeMillis());
    }

    public Product(String listingId, Storefront storefront, float price, float domesticFreight, float width, float length, float height, String thumbnailUrl) {
        this.productId = new ProductIdentifier(listingId, storefront.getId());
        this.storefront = storefront;
        this.price = price;
        this.domesticFreight = domesticFreight;
        this.width = width;
        this.length = length;
        this.height = height;
        this.thumbnailUrl = thumbnailUrl;
        this.timeFetched = new Date(System.currentTimeMillis());
    }

    public void addQcPhotoSet(QcPhotoSet qcPhotoSet) {
        qcPhotoSets.add(qcPhotoSet);
        qcPhotoSet.setProduct(this);
    }

    public void removeQcPhotoSet(QcPhotoSet qcPhotoSet) {
        qcPhotoSets.remove(qcPhotoSet);
    }

    public void updateFetchedTime() {
        timeFetched = new Date(System.currentTimeMillis());
    }

    //<editor-fold desc="View dumb getters and setters">
    public String getListingId() {
        return productId.getListingId();
    }

    public Storefront getStorefront() {
        return storefront;
    }

    public Set<QcPhotoSet> getQcPhotoSets() {
        return qcPhotoSets;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDomesticFreight() {
        return domesticFreight;
    }

    public void setDomesticFreight(float domesticFreight) {
        this.domesticFreight = domesticFreight;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Date getFetchedTime() {
        return timeFetched;
    }

    public void setFetchedTime(Date timeFetched) {
        this.timeFetched = timeFetched;
    }
    //</editor-fold>
}
