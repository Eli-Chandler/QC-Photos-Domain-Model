package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class QcPhotoSet {
    @Id
    @GeneratedValue
    Long id;

    @ManyToOne(cascade = CascadeType.PERSIST) // We don't want to destroy a QcPhotoProvider if it's orphaned
    private QcPhotoProvider qcPhotoProvider;

    @OneToMany(cascade = CascadeType.ALL)
    private final Set<QcPhoto> qcPhotos = new HashSet<>();

    protected QcPhotoSet() {}

    public QcPhotoSet(QcPhotoProvider qcPhotoProvider) {
        this.qcPhotoProvider = qcPhotoProvider;
    }

    public Long getId() {
        return id;
    }

    public QcPhotoProvider getQcPhotoProvider() {
        return qcPhotoProvider;
    }

    public Set<QcPhoto> getQcPhotos() {
        return qcPhotos;
    }

    public void addQcPhoto(QcPhoto qcPhoto) {
        qcPhotos.add(qcPhoto);
    }
}
