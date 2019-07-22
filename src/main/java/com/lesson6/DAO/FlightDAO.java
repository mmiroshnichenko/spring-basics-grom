package com.lesson6.DAO;

import com.lesson6.entity.Filter;
import com.lesson6.entity.Flight;
import com.lesson6.entity.Plane;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FlightDAO extends BaseDAO<Flight> {
    private static final String findMostPopularToQuery =
            "SELECT C.city_to FROM (SELECT F.city_to,  COUNT(F.id) AS count_flight FROM FLIGHT F " +
            "GROUP BY F.city_to ORDER BY count_flight DESC) C " +
            "WHERE ROWNUM <= 10";

    private static final String findMostPopularFromQuery =
            "SELECT C.city_from FROM (SELECT F.city_from,  COUNT(F.id) AS count_flight FROM FLIGHT F " +
            "GROUP BY F.city_from ORDER BY count_flight DESC) C " +
            "WHERE ROWNUM <= 10";

    public FlightDAO() {
        super(Flight.class);
    }

    public List<String> mostPopularTo() {
        return entityManager.createNativeQuery(findMostPopularToQuery).getResultList();
    }

    public List<String> mostPopularFrom() {
        return entityManager.createNativeQuery(findMostPopularFromQuery).getResultList();
    }

    public List<Flight> flightsByDate(Filter filter) {
        startFilter();

        Join<Flight, Plane> planeJoin = root.join("plane");

        addPredicates(root.get("dateFlight"), filter.getDateFrom(), Operator.GTE);
        addPredicates(root.get("dateFlight"), filter.getDateTo(), Operator.LTE);
        addArrayEqualPredicates(
            new Path[]{root.get("cityFrom"), root.get("cityTo"), planeJoin.get("model")},
            new Object[]{filter.getCityFrom(), filter.getCityTo(), filter.getModelPlane()}
        );

        return getFilteredList();
    }
}
