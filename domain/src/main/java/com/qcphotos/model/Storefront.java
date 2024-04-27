package com.qcphotos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Storefront {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String displayName;

    private String urlFormat;

    protected Storefront() {}

    public Storefront(String name, String displayName, String urlFormat) {
        this.name = name;
        this.displayName = displayName;
        this.urlFormat = urlFormat;
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

    public String getUrlFormat() {
        return urlFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storefront that = (Storefront) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
