package org.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Hello Hibernate!");

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Worker.class);
        configuration.addAnnotatedClass(Department.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        //our code (business logic) goes here
        Department department = new Department("IT");
        Worker bob = new Worker("Bob", department);
        logger.info("Worker before save {}", bob);
        session.persist(bob);
        logger.info("Worker after save {}", bob);
        logger.info("Department {}", department);


        transaction.commit();


        Worker workerFromSession = session.get(Worker.class, 1);

        logger.info("Worker form session {}", workerFromSession);


    }
}