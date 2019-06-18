package com.lesson6.DAO;

import com.lesson6.entity.Passenger;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class PassengerDAO extends BaseDAO<Passenger> {
    public PassengerDAO() {
        super(Passenger.class);
    }
}
