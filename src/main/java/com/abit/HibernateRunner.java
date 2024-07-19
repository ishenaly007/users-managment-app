package com.abit;

import com.abit.entity.Role;
import com.abit.entity.User;
import com.abit.userdao.UserDao;
import com.abit.utils.HibernateUtil;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class HibernateRunner {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        try (var sessionFactory = new HibernateUtil().getSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.delete(User.builder().username("Ishenaly070")
                    .firstname("Ishenaly")
                    .lastname("Narkozuev")
                    .age(17)
                    .birthDate(LocalDate.of(2007, 02, 03))
                    .role(Role.ADMIN)
                    .build());

            session.getTransaction().commit();
        }

        String firstname1 = "Tilek";
        var user = User.builder().username("Tilek404")
                .firstname("Tilek")
                .lastname("Erkebaev")
                .age(17)
                .birthDate(LocalDate.of(2006, 8, 1))
                .role(Role.ADMIN)
                .build();
        System.out.println(userDao.getAllUsers());
        //System.out.println(userDao.getUser(username1));
        //userDao.saveUser(user);
        System.out.println(userDao.getUsersByFirstname(firstname1));
    }
}