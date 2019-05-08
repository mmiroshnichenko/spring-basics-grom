package com.lesson3.hw.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STORAGE")
public class Storage {
    private long id;
    private String formatsSupported;
    private String storageCountry;
    private long storageMaxSize;
    private List<File> files;

    @Id
    @SequenceGenerator(name = "STOR_SEQ", sequenceName = "STORAGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STOR_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "FORMATS_SUPPORTED")
    public String getFormatsSupported() {
        return formatsSupported;
    }

    @Transient
    public String[] getFormatsSupportedArray() {
        return formatsSupported.split(",");
    }

    @Column(name = "STORAGE_COUNTRY")
    public String getStorageCountry() {
        return storageCountry;
    }

    @Column(name = "STORAGE_MAX_SIZE")
    public long getStorageMaxSize() {
        return storageMaxSize;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="storage")
    public List<File> getFiles() { return files; }

    public void setId(long id) {
        this.id = id;
    }

    public void setFormatsSupported(String formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public void setStorageMaxSize(long storageMaxSize) {
        this.storageMaxSize = storageMaxSize;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public boolean isSupportedFormat(String format) {
        for (String supportedFormat : getFormatsSupportedArray()) {
            if (supportedFormat.equals(format)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported=" + formatsSupported +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageMaxSize=" + storageMaxSize +
                '}';
    }
}
