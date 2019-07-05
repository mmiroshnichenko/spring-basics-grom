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
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> criteriaQuery = builder.createQuery(Flight.class);
        Root<Flight> root = criteriaQuery.from(Flight.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getDateFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dateFlight"), filter.getDateFrom()));
        }
        if (filter.getDateTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dateFlight"), filter.getDateTo()));
        }
        if (filter.getCityFrom() != null) {
            predicates.add(builder.equal(root.get("cityFrom"), filter.getCityFrom()));
        }
        if (filter.getCityTo() != null) {
            predicates.add(builder.equal(root.get("cityTo"), filter.getCityTo()));
        }
        if (filter.getModelPlane() != null) {
            Join<Flight, Plane> planeJoin = root.join("plane");
            predicates.add(builder.equal(planeJoin.get("model"), filter.getModelPlane()));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
