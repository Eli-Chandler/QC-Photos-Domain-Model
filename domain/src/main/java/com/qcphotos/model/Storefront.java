package com.qcphotos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
