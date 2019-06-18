package com.lesson6.DAO;

import com.lesson6.entity.Plane;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class PlaneDAO extends BaseDAO<Plane> {
    public PlaneDAO() {
        super(Plane.class);
    }
}