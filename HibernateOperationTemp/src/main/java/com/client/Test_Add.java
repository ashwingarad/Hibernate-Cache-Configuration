package com.client;

import com.config.HibernateUtil;
import com.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class Test_Add {
    public static void main(String... args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            Session session = sessionFactory.openSession();
            Student student = new Student();
            student.setSname("Ashwin");
            student.setMarks(550);

            Student student3 = new Student();
            student3.setSname("Ashnil");
            student3.setMarks(550);

            Student student1 = new Student();
            student1.setSname("Mahesh");
            student1.setMarks(650);

            Student student2 = new Student();
            student2.setSname("Omkar");
            student2.setMarks(640);

            Transaction transaction = session.beginTransaction();
            session.persist(student);
            session.persist(student1);
            session.persist(student2);
            session.persist(student3);
            transaction.commit();

            if (transaction.getStatus() == TransactionStatus.COMMITTED)
                System.out.println("Saved");
            else
                System.out.println("Something went wrong");

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
