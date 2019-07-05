package com.lesson6.service;

import com.lesson6.DAO.FlightDAO;
import com.lesson6.entity.Filter;
import com.lesson6.entity.Flight;
import com.lesson6.entity.Passenger;
import com.lesson6.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FlightService {
    private FlightDAO flightDAO;
    private PassengerService passengerService;
    private PlaneService planeService;

    @Autowired
    public FlightService(FlightDAO flightDAO, PassengerService passengerService, PlaneService planeService) {
        this.flightDAO = flightDAO;
        this.passengerService = passengerService;
        this.planeService = planeService;
    }

    public Flight save(Flight flight) throws Exception{
        validate(flight);

        flight.setPlane(planeService.findById(flight.getPlane().getId()));
        setPassengers(flight, flight.getPassengers());

        return flightDAO.save(flight);
    }

    public Flight update(Flight flight) throws Exception {
        validate(flight);

        Flight flightDb = findById(flight.getId());
        flightDb.setPlane(planeService.findById(flight.getPlane().getId()));
        flightDb.setDateFlight(flight.getDateFlight());
        flightDb.setCityFrom(flight.getCityFrom());
        flightDb.setCityTo(flight.getCityTo());
        setPassengers(flightDb, flight.getPassengers());

        return flightDAO.update(flightDb);
    }

    public void delete(long id) throws Exception {
        flightDAO.delete(findById(id));
    }

    public Flight findById(long id) throws Exception {
        Flight flight = flightDAO.findById(id);
        if (flight == null) {
            throw new BadRequestException("Error: flight(id = " + id + ") was not found");
        }

        return flight;
    }

    public List<String> mostPopularTo() {
        return flightDAO.mostPopularTo();
    }

    public List<String> mostPopularFrom() {
        return flightDAO.mostPopularFrom();
    }

    public List<Flight> flightsByDate(Filter filter) {
        return flightDAO.flightsByDate(filter);
    }

    private void setPassengers(Flight flightDb, List<Passenger> flightPassengers) throws Exception {
        if (flightPassengers == null) {
            return;
        }

        List<Passenger> passengers = new ArrayList<>();
        for (Passenger flightPassenger: flightPassengers) {
            passengers.add(passengerService.findById(flightPassenger.getId()));
        }
        flightDb.setPassengers(passengers);
    }

    private void validate(Flight flight) throws BadRequestException {
        if (flight.getDateFlight() == null) {
            throw new BadRequestException("Error: flight Date is required");
        }

        if (flight.getCityFrom() == null || flight.getCityFrom().isEmpty()) {
            throw new BadRequestException("Error: flight City from is required");
        }

        if (flight.getCityTo() == null || flight.getCityTo().isEmpty()) {
            throw new BadRequestException("Error: flight City to is required");
        }
    }
}
