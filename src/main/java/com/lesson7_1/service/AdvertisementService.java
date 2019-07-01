package com.lesson7_1.service;

import com.lesson7_1.DAO.AdvertisementDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdvertisementService {
    private AdvertisementDAO advertisementDAO;

    @Autowired
    public AdvertisementService(AdvertisementDAO advertisementDAO) {
        this.advertisementDAO = advertisementDAO;
    }
}
