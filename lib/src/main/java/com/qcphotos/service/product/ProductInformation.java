package com.qcphotos.service.product;

import com.qcphotos.model.QcPhotoSet;

import java.util.Set;

public class ProductInformation {
    private final float price;
    private final float domesticFreight;
    private final float width;
    private final float length;
    private final float height;
    private final String thumbnailUrl;
    private final Set<QcPhotoSet> qcPhotoSets;

    protected ProductInformation(float price, float domesticFreight, float width, float length, float height, String thumbnailUrl, Set<QcPhotoSet> qcPhotoSets) {
        this.price = price;
        this.domesticFreight = domesticFreight;
        this.width = width;
        this.length = length;
        this.height = height;
        this.thumbnailUrl = thumbnailUrl;
        this.qcPhotoSets = qcPhotoSets;
    }

    public float getPrice() {
        return price;
    }

    public float getDomesticFreight() {
        return domesticFreight;
    }

    public float getWidth() {
        return width;
    }

    public float getLength() {
        return length;
    }

    public float getHeight() {
        return height;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Set<QcPhotoSet> getQcPhotoSets() {
        return qcPhotoSets;
    }
}
