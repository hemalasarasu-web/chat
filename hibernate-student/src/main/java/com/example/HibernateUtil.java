package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();

        configuration.setProperty(
                "hibernate.connection.driver_class",
                "com.mysql.cj.jdbc.Driver"
        );

        configuration.setProperty(
                "hibernate.connection.url",
                "jdbc:mysql://localhost:3306/studentdb"
        );

        configuration.setProperty(
                "hibernate.connection.username",
                "root"
        );

        configuration.setProperty(
                "hibernate.connection.password",
                "Hema123456789"
        );

        configuration.setProperty(
                "hibernate.dialect",
                "org.hibernate.dialect.MySQLDialect"
        );

        configuration.setProperty(
                "hibernate.hbm2ddl.auto",
                "update"
        );

        configuration.setProperty(
                "hibernate.show_sql",
                "true"
        );

        configuration.addAnnotatedClass(Student.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}