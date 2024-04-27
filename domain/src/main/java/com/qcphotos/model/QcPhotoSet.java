package com.qcphotos.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
public class QcPhotoSet {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST) // We don't want to destroy a QcPhotoProvider if it's orphaned
    private QcPhotoPlatform qcPhotoPlatform;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<QcPhoto> qcPhotos = new HashSet<>();

    protected QcPhotoSet() {}

    public QcPhotoSet(QcPhotoPlatform qcPhotoPlatform) {
        this.qcPhotoPlatform = qcPhotoPlatform;
    }

    public Long getId() {
        return id;
    }
    public Product getProduct() {
        return product;
    }
    public QcPhotoPlatform getQcPhotoPlatform() {
        return qcPhotoPlatform;
    }
    public Set<QcPhoto> getQcPhotos() {
        return qcPhotos;
    }

    public void setProduct(Product product) {
        this.product = product;

        if ( ! product.getQcPhotoSets().contains(this)) {
            product.addQcPhotoSet(this);
        }
    }

    public void addQcPhoto(QcPhoto qcPhoto) {
        qcPhotos.add(qcPhoto);
        qcPhoto.setQcPhotoSet(this);
    }

    public void removeQcPhoto(QcPhoto qcPhoto) {
        qcPhotos.remove(qcPhoto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QcPhotoSet that = (QcPhotoSet) o;
        return Objects.equals(qcPhotoPlatform, that.qcPhotoPlatform) && Objects.equals(qcPhotos, that.qcPhotos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qcPhotoPlatform, qcPhotos);
    }
}
