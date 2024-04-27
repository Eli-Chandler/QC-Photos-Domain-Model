package com.qcphotos.service.product;

import com.qcphotos.model.QcPhotoSet;

import java.util.HashSet;
import java.util.Set;

public class ProductInformationBuilder {
    private float price;
    private float domesticFreight;
    private float width;
    private float length;
    private float height;
    private String thumbnailUrl;
    private final Set<QcPhotoSet> qcPhotoSets = new HashSet<>();

    public ProductInformationBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public ProductInformationBuilder setDomesticFreight(float domesticFreight) {
        this.domesticFreight = domesticFreight;
        return this;
    }

    public ProductInformationBuilder setWidth(float width) {
        this.width = width;
        return this;
    }

    public ProductInformationBuilder setLength(float length) {
        this.length = length;
        return this;
    }

    public ProductInformationBuilder setHeight(float height) {
        this.height = height;
        return this;
    }

    public ProductInformationBuilder setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
        return this;
    }

    public ProductInformationBuilder addQcPhotoSet(QcPhotoSet qcPhotoSet) {
        this.qcPhotoSets.add(qcPhotoSet);
        return this;
    }

    public ProductInformation build() {
        return new ProductInformation(
                this.price,
                this.domesticFreight,
                this.width,
                this.length,
                this.height,
                this.thumbnailUrl,
                this.qcPhotoSets
        );
    }
}

