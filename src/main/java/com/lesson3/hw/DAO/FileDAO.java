package com.lesson3.hw.DAO;

import com.lesson3.hw.entity.File;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FileDAO extends BaseDAO<File> {
    public FileDAO() {
        super(File.class);
    }

    public void updateFilesList(List<File> files) throws HibernateException {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            for (File file : files) {
                session.update(file);
            }

            tr.commit();
        } catch (HibernateException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw new HibernateException("Update files list is failed");
        }
    }
}
