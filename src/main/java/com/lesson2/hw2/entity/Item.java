//package com.lesson2.hw2.entity;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "ITEM")
//public class Item {
//    private long id;
//    private String name;
//    private Date dateCreated;
//    private Date lastUpdatedDate;
//    private String description;
//
//    @Id
//    @SequenceGenerator(name = "ITM_SEQ", sequenceName = "ITEM_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITM_SEQ")
//    @Column(name = "ID")
//    public long getId() {
//        return id;
//    }
//
//    @Column(name = "NAME")
//    public String getName() {
//        return name;
//    }
//
//    @Column(name = "DATE_CREATED")
//    public Date getDateCreated() {
//        return dateCreated;
//    }
//
//    @Column(name = "LAST_UPDATED_DATE")
//    public Date getLastUpdatedDate() {
//        return lastUpdatedDate;
//    }
//
//    @Column(name = "DESCRIPTION")
//    public String getDescription() {
//        return description;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setDateCreated(Date dateCreated) {
//        this.dateCreated = dateCreated;
//    }
//
//    public void setLastUpdatedDate(Date lastUpdatedDate) {
//        this.lastUpdatedDate = lastUpdatedDate;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Override
//    public String toString() {
//        return "Item{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", dateCreated=" + dateCreated +
//                ", lastUpdatedDate=" + lastUpdatedDate +
//                ", description='" + description + '\'' +
//                '}';
//    }
//}
