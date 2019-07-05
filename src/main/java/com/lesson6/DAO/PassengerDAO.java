package com.lesson6.DAO;

import com.lesson6.entity.Passenger;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PassengerDAO extends BaseDAO<Passenger> {
    private static final String findRegularPassengersQuery =
            "SELECT P.* FROM PASSENGER P " +
            "INNER JOIN ( " +
            "SELECT p.id, COUNT(f.id) FROM FLIGHT f " +
            "INNER JOIN FLIGHT_PASSENGER fp ON fp.flight_id = f.id " +
            "INNER JOIN PASSENGER p ON p.id = fp.passenger_id " +
            "WHERE EXTRACT(YEAR FROM f.date_flight) = ?1 " +
            "GROUP BY p.id HAVING COUNT(f.id) > 25 ) PC ON PC.id = P.id";

    public PassengerDAO() {
        super(Passenger.class);
    }

    public List<Passenger> regularPassengers(int year) {
        return entityManager.createNativeQuery(findRegularPassengersQuery, Passenger.class)
                .setParameter(1, year)
                .getResultList();
    }

}
