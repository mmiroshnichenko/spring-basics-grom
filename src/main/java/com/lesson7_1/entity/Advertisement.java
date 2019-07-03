package com.lesson7_1.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ADVERTISEMENT")
public class Advertisement {
    private long id;
    private User user;
    private String title;
    private String description;
    private int price;
    private Currency currency;
    private Category category;
    private Subcategory subcategory;
    private String city;
    private String phone;
    private Date dateCreated;
    private Date dateExpired;

    @Id
    @SequenceGenerator(name = "ADV_SEQ", sequenceName = "ADV_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADV_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Column(name = "PRICE")
    public int getPrice() {
        return price;
    }

    @Column(name = "CURRENCY")
    public String getCurrency() {
        return currency.toString();
    }

    @Transient
    public Currency getCurrencyEnum() {
        return currency;
    }

    @Column(name = "CATEGORY")
    public String getCategory() {
        return category.toString();
    }

    @Transient
    public Category getCategoryEnum() {
        return category;
    }

    @Column(name = "SUBCATEGORY")
    public String getSubcategory() {
        return subcategory.toString();
    }

    @Transient
    public Subcategory getSubcategoryEnum() {
        return subcategory;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "PHONE")
    public String getPhone() {
        return phone;
    }

    @Column(name = "DATE_CREATED")
    public Date getDateCreated() {
        return dateCreated;
    }

    @Column(name = "DATE_EXPIRED")
    public Date getDateExpired() {
        return dateExpired;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCurrency(String currency) {
        this.currency = Currency.valueOf(currency);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = Category.valueOf(category);
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = Subcategory.valueOf(subcategory);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateExpired(Date dateExpired) {
        this.dateExpired = dateExpired;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", user=" + user.getUserName() +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", currency=" + currency.toString() +
                ", category=" + category.toString() +
                ", subcategory=" + subcategory.toString() +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateExpired=" + dateExpired +
                '}';
    }
}
