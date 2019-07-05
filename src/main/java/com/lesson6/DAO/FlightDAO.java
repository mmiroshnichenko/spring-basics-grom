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
        Join<Flight, Plane> planeJoin = root.join("plane");

        addPredicates(predicates, builder, root.get("dateFlight"), filter.getDateFrom(), Operator.GTE);
        addPredicates(predicates, builder, root.get("dateFlight"), filter.getDateTo(), Operator.LTE);
        addPredicates(predicates, builder, root.get("cityFrom"), filter.getCityFrom(), Operator.EQ);
        addPredicates(predicates, builder, root.get("cityTo"), filter.getCityTo(), Operator.EQ);
        addPredicates(predicates, builder, planeJoin.get("model"), filter.getModelPlane(), Operator.EQ);

        /*if (filter.getDateFrom() != null && filter.getDateTo() != null) {
            predicates.add(builder.between(root.get("dateFlight"), filter.getDateFrom(), filter.getDateTo()));
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
*/
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    protected void addPredicates(List<Predicate> predicates, CriteriaBuilder builder, Path path, Object filterValue, Operator operator) {
        if (filterValue == null) {
            return;
        }

        switch (operator) {
            case EQ:
                predicates.add(builder.equal(path, filterValue));
                break;
            case NE:
                predicates.add(builder.notEqual(path, filterValue));
                break;
            case LIKE:
                predicates.add(builder.like(path, "%" + filterValue + "%"));
                break;
            case LT:
                predicates.add(builder.lessThan(path, (Comparable) filterValue));
                break;
            case GT:
                predicates.add(builder.greaterThan(path, (Comparable) filterValue));
                break;
            case LTE:
                predicates.add(builder.lessThanOrEqualTo(path, (Comparable) filterValue));
                break;
            case GTE:
                predicates.add(builder.greaterThanOrEqualTo(path, (Comparable) filterValue));
                break;
        }
    }
}
