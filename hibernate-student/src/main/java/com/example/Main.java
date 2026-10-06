package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Student student = new Student(
                "Hemala",
                "hemalasarasu@gmail.com",
                "Data Analytics"
        );

        session.persist(student);

        transaction.commit();

        System.out.println("Student inserted successfully!");
        System.out.println("Generated ID: " + student.getId());

        session.close();
    }
}