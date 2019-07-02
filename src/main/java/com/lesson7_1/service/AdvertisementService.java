package com.lesson7_1.service;

import com.lesson7_1.DAO.AdvertisementDAO;
import com.lesson7_1.DAO.UserDAO;
import com.lesson7_1.entity.Advertisement;
import com.lesson7_1.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Calendar cal = Calendar.getInstance();
        advertisement.setDateCreated(cal.getTime());
        cal.add(Calendar.DATE, 30);
        Date expiredDate = cal.getTime();
        advertisement.setDateExpired(expiredDate);

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

    private void validate(Advertisement advertisement) throws BadRequestException{
        if (advertisement.getTitle() == null || advertisement.getTitle().isEmpty()) {
            throw new BadRequestException("Error: title is required");
        }

        if (advertisement.getDescription().length() > 200) {
            throw new BadRequestException("Error: description must be less that 200");
        }

        if (advertisement.getPrice() < 0) {
            throw new BadRequestException("Error: incorrect price");
        }

        if (advertisement.getCurrencyEnum() == null) {
            throw new BadRequestException("Error: incorrect currency");
        }

        if (advertisement.getCategoryEnum() == null) {
            throw new BadRequestException("Error: incorrect category");
        }

        if (advertisement.getSubcategoryEnum() == null) {
            throw new BadRequestException("Error: incorrect subcategory");
        }

        if (advertisement.getCity() == null) {
            throw new BadRequestException("Error: city is required");
        }

        if (advertisement.getPhone() == null) {
            throw new BadRequestException("Error: phone is required");
        }

        String regex = "[0-9*#+() -]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(advertisement.getPhone());

        if(!matcher.matches()) {
            throw new BadRequestException("Error: incorrect phone format");
        }
    }
}
