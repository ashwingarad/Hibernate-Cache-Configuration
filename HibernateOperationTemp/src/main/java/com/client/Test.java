package com.client;

import com.config.HibernateUtil;
import com.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Test {
    public static void main(String... args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        try {
            Session session = sessionFactory.openSession();
            /*Student student = new Student();
            student.setSname("Ashwin");
            student.setMarks(450);

            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();

            if (transaction.getStatus() == TransactionStatus.COMMITTED)
                System.out.println("Saved");
            else
                System.out.println("Something went wrong");*/

            Query<Student> query = session.createQuery("From Student where roll_no=1");
            query.setCacheable(true);
            query.setCacheRegion("student");

            Student student = query.getSingleResult();
            student.display();

            Query<Student> query1 = session.createQuery("From Student where roll_no=1");
            query1.setCacheable(true);
            query1.setCacheRegion("student");

            Student student1 = query1.getSingleResult();
            student1.display();

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
