package com.lesson3.hw.service;

import com.lesson3.hw.DAO.StorageDAO;
import com.lesson3.hw.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;

public class StorageService {
    private StorageDAO storageDAO;

    @Autowired
    public StorageService(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    public Storage save(Storage storage) throws Exception {
        return storageDAO.save(storage);
    }

    public void delete(long id) throws Exception {
        storageDAO.delete(id);
    }

    public Storage update(Storage storage) throws Exception {
        return storageDAO.update(storage);
    }

    public Storage findById(long id) throws Exception {
        return storageDAO.findById(id);
    }
}
