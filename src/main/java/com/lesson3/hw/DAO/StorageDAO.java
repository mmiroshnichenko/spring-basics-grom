package com.lesson3.hw.DAO;

import com.lesson3.hw.entity.Storage;
import org.springframework.stereotype.Repository;

@Repository
public class StorageDAO extends BaseDAO<Storage> {
    public StorageDAO() {
        super(Storage.class);
    }
}
