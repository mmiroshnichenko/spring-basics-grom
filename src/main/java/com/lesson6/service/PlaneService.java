package com.lesson6.service;

import com.lesson6.DAO.PlaneDAO;
import com.lesson6.entity.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlaneService {

    private PlaneDAO planeDAO;

    @Autowired
    public PlaneService(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    public Plane save(Plane plane) {
        return planeDAO.save(plane);
    }

    public Plane update(Plane plane) throws Exception {
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
}
