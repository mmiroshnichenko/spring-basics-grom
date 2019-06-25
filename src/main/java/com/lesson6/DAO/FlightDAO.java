package com.lesson6.DAO;

import com.lesson6.entity.Flight;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class FlightDAO extends BaseDAO<Flight> {
    public FlightDAO() {
        super(Flight.class);
    }

    public List<String> mostPopularTo() {
        String sql = "SELECT C.city_to FROM (SELECT F.city_to,  COUNT(F.id) AS count_flight FROM FLIGHT F " +
                     "GROUP BY F.city_to ORDER BY count_flight DESC) C " +
                     "WHERE ROWNUM <= 10";

        return entityManager.createNativeQuery(sql).getResultList();
    }

    public List<String> mostPopularFrom() {
        String sql = "SELECT C.city_from FROM (SELECT F.city_from,  COUNT(F.id) AS count_flight FROM FLIGHT F " +
                     "GROUP BY F.city_from ORDER BY count_flight DESC) C " +
                     "WHERE ROWNUM <= 10";

        return entityManager.createNativeQuery(sql).getResultList();
    }
}
