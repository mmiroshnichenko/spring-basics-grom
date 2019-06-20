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
    public PlaneDAO() {
        super(Plane.class);
    }

    public List<Plane> oldPlanes() {
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, -20);
        Date oldYearProduced = cal.getTime();

        return entityManager.createQuery("SELECT e FROM Plane e WHERE e.yearProduced < :oldYearProduced")
                .setParameter("oldYearProduced", oldYearProduced, TemporalType.DATE)
                .getResultList();
    }
}