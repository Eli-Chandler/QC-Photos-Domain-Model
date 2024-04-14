package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class QcPhotoSet {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST) // We don't want to destroy a QcPhotoProvider if it's orphaned
    private QcPhotoProvider qcPhotoProvider;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<QcPhoto> qcPhotos = new HashSet<>();

    protected QcPhotoSet() {}

    public QcPhotoSet(QcPhotoProvider qcPhotoProvider) {
        this.qcPhotoProvider = qcPhotoProvider;
    }

    public Long getId() {
        return id;
    }
    public Product getProduct() {
        return product;
    }
    public QcPhotoProvider getQcPhotoProvider() {
        return qcPhotoProvider;
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
}
