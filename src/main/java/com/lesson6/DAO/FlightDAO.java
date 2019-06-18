package com.lesson6.DAO;

import com.lesson6.entity.Flight;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class FlightDAO extends BaseDAO<Flight> {
    public FlightDAO() {
        super(Flight.class);
    }
}
