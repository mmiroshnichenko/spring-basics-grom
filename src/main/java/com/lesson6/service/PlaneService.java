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
        validate(plane);

        return planeDAO.save(plane);
    }

    public Plane update(Plane plane) throws Exception {
        validate(plane);

        Plane planeDb = planeDAO.findById(plane.getId());
        planeDb.setModel(plane.getModel());
        planeDb.setCode(plane.getCode());
        planeDb.setYearProduced(plane.getYearProduced());
        planeDb.setAvgFuelConsumption(plane.getAvgFuelConsumption());

        return planeDAO.update(planeDb);
    }

    public void delete(long id) throws Exception {
        Plane plane = planeDAO.findById(id);

        planeDAO.delete(plane);
    }

    public Plane findById(long id) throws Exception {
        return planeDAO.findById(id);
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

    private void validate(Plane plane) throws BadRequestException {
        if (plane.getModel() == null || plane.getModel().isEmpty()) {
            throw new BadRequestException("Error: plane Model is required");
        }

        if (plane.getCode() == null || plane.getCode().isEmpty()) {
            throw new BadRequestException("Error: plane Code is required");
        }

        if (plane.getYearProduced() == null) {
            throw new BadRequestException("Error: plane Year produced is required");
        }

        if (plane.getAvgFuelConsumption() <= 0 ) {
            throw new BadRequestException("Error: incorrect Avg Fuel Consumption value");
        }
    }
}
