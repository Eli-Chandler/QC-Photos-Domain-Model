package com.qcphotos.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductIdentifier implements Serializable {
    private String listingId;

    private Long storefrontId;

    protected ProductIdentifier() {}  // JPA

    public ProductIdentifier(String listingId, Long storefrontId) {
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