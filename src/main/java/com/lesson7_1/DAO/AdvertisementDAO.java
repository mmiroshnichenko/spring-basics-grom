package com.lesson7_1.DAO;

import com.lesson7_1.entity.Advertisement;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class AdvertisementDAO extends BaseDAO<Advertisement> {
    public AdvertisementDAO() {
        super(Advertisement.class);
    }
}
