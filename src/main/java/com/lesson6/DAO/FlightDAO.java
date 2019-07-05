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

        Join<Flight, Plane> planeJoin = root.join("plane");

        addPredicates(predicates, builder, root.get("dateFlight"), filter.getDateFrom(), Operator.GTE);
        addPredicates(predicates, builder, root.get("dateFlight"), filter.getDateTo(), Operator.LTE);
        addPredicates(predicates, builder, root.get("cityFrom"), filter.getCityFrom(), Operator.EQ);
        addPredicates(predicates, builder, root.get("cityTo"), filter.getCityTo(), Operator.EQ);
        addPredicates(predicates, builder, planeJoin.get("model"), filter.getModelPlane(), Operator.EQ);

        return getCriteriaResultList();
    }
}
