package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class QcPhoto {
    @Id
    @GeneratedValue
    private Long id;

    private String photoUrl;

    protected QcPhoto() {}

    public QcPhoto(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getId() {
        return id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QcPhoto qcPhoto = (QcPhoto) o;
        return Objects.equals(id, qcPhoto.id) && Objects.equals(photoUrl, qcPhoto.photoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoUrl);
    }
}
