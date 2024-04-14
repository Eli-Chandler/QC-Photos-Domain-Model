package model;

import javax.persistence.*;
import java.io.Serializable;
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
    @ManyToOne(cascade = CascadeType.PERSIST)  // We don't want to destroy a storefront if it's orphaned
    private Storefront storefront;

    @OneToMany(cascade = CascadeType.ALL)
    private final Set<QcPhotoSet> qcPhotoSets = new HashSet<>();

    protected Product() {} // JPA

    public Product(Storefront storefront, String listingId) {
        this.productId = new ProductId(listingId, storefront.getId());
        this.storefront = storefront;
    }

    public String getListingId() {
        return productId.getListingId();
    }

    public Storefront getStorefront() {
        return storefront;
    }

    public Set<QcPhotoSet> getQCPhotoSets() {
        return qcPhotoSets;
    }

    public void addQCPhotoSet(QcPhotoSet qcPhotoSet) {
        qcPhotoSets.add(qcPhotoSet);
    }
}
