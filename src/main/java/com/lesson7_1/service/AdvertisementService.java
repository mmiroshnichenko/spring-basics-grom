package com.lesson7_1.service;

import com.lesson7_1.DAO.AdvertisementDAO;
import com.lesson7_1.DAO.UserDAO;
import com.lesson7_1.entity.Advertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdvertisementService {
    private AdvertisementDAO advertisementDAO;
    private UserService userService;

    @Autowired
    public AdvertisementService(AdvertisementDAO advertisementDAO, UserService userService) {
        this.advertisementDAO = advertisementDAO;
        this.userService = userService;
    }

    public Advertisement save(Advertisement advertisement) throws Exception {
        validate(advertisement);

        return advertisementDAO.save(advertisement);
    }

    public Advertisement update(Advertisement advertisement) throws Exception {
        validate(advertisement);

        Advertisement advertisementDb = advertisementDAO.findById(advertisement.getId());
        advertisementDb.setUser(userService.findById(advertisement.getUser().getId()));
        advertisementDb.setTitle(advertisement.getTitle());
        advertisementDb.setDescription(advertisement.getDescription());
        advertisementDb.setPrice(advertisement.getPrice());
        advertisementDb.setCurrency(advertisement.getCurrency());
        advertisementDb.setCategory(advertisement.getCategory());
        advertisementDb.setSubcategory(advertisement.getSubcategory());
        advertisementDb.setCity(advertisement.getCity());
        advertisementDb.setPhone(advertisement.getPhone());

        return advertisementDAO.update(advertisementDb);
    }

    public void delete(long id) throws Exception {
        Advertisement advertisement = advertisementDAO.findById(id);

        advertisementDAO.delete(advertisement);
    }

    public Advertisement findById(long id) throws Exception {
        return advertisementDAO.findById(id);
    }

    private void validate(Advertisement advertisement) {

    }
}
