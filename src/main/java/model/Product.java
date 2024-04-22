package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Embeddable
class ProductId implements Serializable {
    private String listingId;

    private Long storefrontId;

    protected ProductId() {}  // JPA

    public ProductId(String listingId, Long storefrontId) {
        this.listingId = listingId;
        this.storefrontId = storefrontId;
    }

    public String getListingId() {
        return listingId;
    }

    public Long getStorefrontId() {
        return storefrontId;
    }
}

@Entity
public class Product {
    @EmbeddedId
    private ProductId productId;

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
        this.productId = new ProductId(listingId, storefront.getId());
        this.storefront = storefront;
        this.timeFetched = new Date(System.currentTimeMillis());
    }

    public Product(String listingId, Storefront storefront, float price, float domesticFreight, float width, float length, float height, String thumbnailUrl) {
        this.productId = new ProductId(listingId, storefront.getId());
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

    public String getListingId() {
        return productId.getListingId();
    }
    public Storefront getStorefront() {
        return storefront;
    }
    public Set<QcPhotoSet> getQcPhotoSets() {
        return qcPhotoSets;
    }

    //<editor-fold desc="dumb">
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

    public Date getTimeFetched() {
        return timeFetched;
    }

    public void setTimeFetched(Date timeFetched) {
        this.timeFetched = timeFetched;
    }
    //</editor-fold>
}
