package com.lesson6.service;

import com.lesson6.DAO.PlaneDAO;
import com.lesson6.entity.Plane;
import com.lesson6.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PlaneService {

    private PlaneDAO planeDAO;

    @Autowired
    public PlaneService(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    public Plane save(Plane plane) throws Exception {
        return planeDAO.save(plane);
    }

    public Plane update(Plane plane) throws Exception {
        Plane planeDb = findById(plane.getId());
        planeDb.setModel(plane.getModel());
        planeDb.setCode(plane.getCode());
        planeDb.setYearProduced(plane.getYearProduced());
        planeDb.setAvgFuelConsumption(plane.getAvgFuelConsumption());

        return planeDAO.update(planeDb);
    }

    public void delete(long id) throws Exception {
        planeDAO.delete(findById(id));
    }

    public Plane findById(long id) throws Exception {
        Plane plane = planeDAO.findById(id);
        if (plane == null) {
            throw new BadRequestException("Error: plane(id = " + id + ") was not found");
        }

        return plane;
    }

    public List<Plane> oldPlanes() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -20);
        Date oldYearProduced = cal.getTime();

        return planeDAO.getPlanesOlderYearProduced(oldYearProduced);
    }

    public List<Plane> regularPlanes(int year) {
        return planeDAO.regularPlanes(year);
    }
}
