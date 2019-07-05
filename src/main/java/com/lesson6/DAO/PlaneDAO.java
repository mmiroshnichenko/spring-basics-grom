package com.lesson6.DAO;

import com.lesson6.entity.Plane;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class PlaneDAO extends BaseDAO<Plane> {
    private static final String findPlanesOlderYearProducedQuery = "SELECT * FROM PLANE WHERE YEAR_PRODUCED < ?1";
    private static final String findRegularPlanesQuery =
            "SELECT P.* FROM PLANE P " +
            "INNER JOIN ( " +
            "SELECT PLANE_ID, COUNT(ID) FROM FLIGHT " +
            "WHERE EXTRACT(YEAR FROM DATE_FLIGHT) = ?1 " +
            "GROUP BY PLANE_ID HAVING COUNT(ID) > 300) F ON F.PLANE_ID = P.ID";

    public PlaneDAO() {
        super(Plane.class);
    }

    public List<Plane> getPlanesOlderYearProduced(Date yearProduced) {
        return entityManager.createNativeQuery(findPlanesOlderYearProducedQuery, Plane.class)
                .setParameter(1, yearProduced, TemporalType.DATE)
                .getResultList();
    }

    public List<Plane> regularPlanes(int year) {
        return entityManager.createNativeQuery(findRegularPlanesQuery, Plane.class)
                .setParameter(1, year)
                .getResultList();
    }
}