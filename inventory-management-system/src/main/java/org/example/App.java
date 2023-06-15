package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Hello world!
 *
 */
public class App 
{
//    public static void main(String[] args) {
//        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).addAnnotatedClass(DevStatus.class).addAnnotatedClass(Person.class);
//        SessionFactory sf = con.buildSessionFactory();
//        Session session = sf.openSession();
//        Transaction tx = session.beginTransaction();
//
//        User u1 = new User();
//        u1.setFirst_name("Vedant");
//        u1.setLast_name("Bhandare");
//        u1.setAge(19);
//        u1.setDob("2003-08-12");
//        u1.setContact_no("9156508309");
//        u1.setEmail_id("cs21btech11007@iith.ac.in");
//        u1.setDigest("Temp");
//
//
//        session.persist(u1);
//
//        DevStatus ds1 = new DevStatus();
//        ds1.setStatus("Hello. testing");
//        session.persist(ds1);
//        System.out.println(session.createQuery("from DevStatus where status_id = 1").list());
//        tx.commit();
//    }
}
