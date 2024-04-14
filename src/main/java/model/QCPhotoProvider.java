package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class QCPhotoProvider {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String displayName;

    protected QCPhotoProvider() {}

    public QCPhotoProvider(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
