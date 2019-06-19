package com.lesson6.service;

import com.lesson6.DAO.PassengerDAO;
import com.lesson6.entity.Passenger;
import com.lesson6.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PassengerService {
    private PassengerDAO passengerDAO;

    @Autowired
    public PassengerService(PassengerDAO passengerDAO) {
        this.passengerDAO = passengerDAO;
    }

    public Passenger save(Passenger passenger) throws Exception {
        validate(passenger);

        return passengerDAO.save(passenger);
    }

    public Passenger update(Passenger passenger) throws Exception {
        validate(passenger);

        Passenger passengerDb = passengerDAO.findById(passenger.getId());
        passengerDb.setLastName(passenger.getLastName());
        passengerDb.setNationality(passenger.getNationality());
        passengerDb.setDateOfBirth(passenger.getDateOfBirth());
        passengerDb.setPassportCode(passenger.getPassportCode());


        return passengerDAO.update(passengerDb);
    }

    public void delete(long id) throws Exception {
        Passenger passenger = passengerDAO.findById(id);

        passengerDAO.delete(passenger);
    }

    public Passenger findById(long id) throws Exception {
        return passengerDAO.findById(id);
    }

    private void validate(Passenger passenger) throws BadRequestException {
        if (passenger.getLastName() == null || passenger.getLastName().isEmpty()) {
            throw new BadRequestException("Error: passenger Last name is required");
        }

        if (passenger.getNationality() == null || passenger.getNationality().isEmpty()) {
            throw new BadRequestException("Error: passenger Nationality is required");
        }

        if (passenger.getDateOfBirth() == null) {
            throw new BadRequestException("Error: passenger Date of birth is required");
        }

        if (passenger.getPassportCode() == null || passenger.getPassportCode().isEmpty()) {
            throw new BadRequestException("Error: passenger Passport code is required");
        }
    }
}
