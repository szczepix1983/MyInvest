package com.szczepix.myinvest.services.entityService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.logging.Logger;

@Service
@Transactional
public class EntityService {

    private static final Logger LOG = Logger.getAnonymousLogger();

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    public void save(final BaseEntity entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(entity);

        session.getTransaction().commit();
        sessionFactory.close();
    }
}
