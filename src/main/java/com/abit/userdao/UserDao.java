package com.abit.userdao;

import com.abit.entity.Role;
import com.abit.entity.User;
import com.abit.utils.HibernateUtil;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class UserDao {
    public void saveUser(User user) {
        try (var sessionFactory = new HibernateUtil().getSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    public void saveOrUpdateUser(User user) {
        try (var sessionFactory = new HibernateUtil().getSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    public void deleteUser(User user) {
        try (var sessionFactory = new HibernateUtil().getSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    public User getUser(String username) {
        try (var sessionFactory = new HibernateUtil().getSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var user = session.get(User.class, username);
            session.getTransaction().commit();
            return user;
        }
    }

    public List<User> getAllUsers() {
        try (var sessionFactory = new HibernateUtil().getSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            List<User> users = query.getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    public List<User> getUsersByFirstname(String firstname) {
        try (var sessionFactory = new HibernateUtil().getSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createQuery("from User where firstname = :firstname", User.class);
            query.setParameter("firstname", firstname);
            session.getTransaction().commit();
            return query.list();
        }
    }
}