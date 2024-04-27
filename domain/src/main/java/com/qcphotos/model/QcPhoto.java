package com.qcphotos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class QcPhoto {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private QcPhotoSet qcPhotoSet;

    private String photoUrl;

    protected QcPhoto() {}

    public QcPhoto(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setQcPhotoSet(QcPhotoSet qcPhotoSet) {
        this.qcPhotoSet = qcPhotoSet;
    }

    public Long getId() {
        return id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public QcPhotoSet getQcPhotoSet() {
        return qcPhotoSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QcPhoto qcPhoto = (QcPhoto) o;
        return Objects.equals(photoUrl, qcPhoto.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoUrl);
    }
}
