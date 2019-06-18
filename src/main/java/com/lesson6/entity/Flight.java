package com.lesson6.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FLIGHT")
public class Flight {
    private long id;
    private Plane plane;
    private Date dateFlight;
    private String cityFrom;
    private String cityTo;
    private List<Passenger> passengers;

    @Id
    @SequenceGenerator(name = "FLT_SEQ", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLT_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "PLANE_ID", nullable = false)
    public Plane getPlane() {
        return plane;
    }

    @Column(name = "DATE_FLIGHT")
    public Date getDateFlight() {
        return dateFlight;
    }

    @Column(name = "CITY_FROM")
    public String getCityFrom() {
        return cityFrom;
    }

    @Column(name = "CITY_TO")
    public String getCityTo() {
        return cityTo;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "FLIGHT_PASSENGER", joinColumns = {
            @JoinColumn(name = "FLIGHT_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "PASSENGER_ID",
                    nullable = false, updatable = false) })
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
