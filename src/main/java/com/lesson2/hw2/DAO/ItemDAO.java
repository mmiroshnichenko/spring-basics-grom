package com.lesson2.hw2.DAO;

import com.lesson2.hw2.entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

public class ItemDAO {
    private static SessionFactory sessionFactory;

    public Item save(Item item) throws HibernateException {
        try(Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();

            tr.begin();

            session.save(item);

            tr.commit();

            return item;
        } catch (HibernateException e) {
            throw new HibernateException("Save is failed");
        }
    }

    public Item update(Item item) throws HibernateException {
        try (Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.update(item);

            tr.commit();

            return item;
        } catch (HibernateException e) {
            throw new HibernateException("Update is failed");
        }
    }

    public void delete(Item item) {
        try (Session session = createSessionFactory().openSession()) {
            Transaction tr = session.getTransaction();
            tr.begin();

            session.delete(item);

            tr.commit();
        } catch (HibernateException e) {
            throw new HibernateException("Delete is failed");
        }
    }

    public Item findById(long itemId) {
        try (Session session = createSessionFactory().openSession()) {

            return session.get(Item.class, itemId);
        } catch (HibernateException e) {
            throw new HibernateException("Search is failed");
        }
    }

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }
}
