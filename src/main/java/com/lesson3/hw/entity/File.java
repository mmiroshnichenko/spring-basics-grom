package com.lesson3.hw.entity;

import javax.persistence.*;

@Entity
@Table(name = "FILES")
public class File {
    private long id;
    private String name;
    private String format;
    private long size;
    private Storage storage;

    @Id
    @SequenceGenerator(name = "FL_SEQ", sequenceName = "FILES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FL_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "FORMAT")
    public String getFormat() {
        return format;
    }

    @Column(name = "FILE_SIZE")
    public long getSize() {
        return size;
    }

    @ManyToOne
    @JoinColumn(name = "STORAGE_ID", nullable = true)
    public Storage getStorage() {
        return storage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", format='" + format + '\'' +
                ", size=" + size +
                ", storageId=" + (storage != null ? storage.getId() : null) +
                '}';
    }
}

