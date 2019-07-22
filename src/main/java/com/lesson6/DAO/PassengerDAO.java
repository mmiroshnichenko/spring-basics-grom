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
            "SELECT fp.passenger_id, COUNT(f.id) FROM FLIGHT f " +
            "INNER JOIN FLIGHT_PASSENGER fp ON fp.flight_id = f.id " +
            "WHERE EXTRACT(YEAR FROM f.date_flight) = ?1 " +
            "GROUP BY fp.passenger_id HAVING COUNT(f.id) > 25 ) PC ON PC.passenger_id = P.id";

    public PassengerDAO() {
        super(Passenger.class);
    }

    public List<Passenger> regularPassengers(int year) {
        return entityManager.createNativeQuery(findRegularPassengersQuery, Passenger.class)
                .setParameter(1, year)
                .getResultList();
    }

}
