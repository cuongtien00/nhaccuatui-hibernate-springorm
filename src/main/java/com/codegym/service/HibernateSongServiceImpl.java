package com.codegym.service;

import com.codegym.model.Song;
import com.codegym.service.ISongService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collections;
import java.util.List;

@Service
public class HibernateSongServiceImpl implements ISongService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Song> findAll() {
        String queryStr = "SELECT c FROM Song AS c";
        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
        return query.getResultList();
    }
    @Override
    public Song save(Song song) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(song);
            transaction.commit();
            return song;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;

    }

    @Override
    public Song findOne(Long id) {
//        String queryStr = "SELECT c FROM Song AS c WHERE c.id = :id";
//        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
//        query.setParameter("id", id);
//        return query.getSingleResult();
        Song song = entityManager.find(Song.class, id);
        return song;
    }


    @Override
    public Song update(Song song) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Song origin = findOne(song.getId());
            origin.setName(song.getName());
            origin.setAuthor(song.getAuthor());
            origin.setType(song.getType());
            origin.setPath(song.getPath());
            session.saveOrUpdate(origin);
            transaction.commit();
            return song;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        entityManager.refresh(song);
        return null;
    }

    @Override
    public List<Song> save(List<Song> songs) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Song> findAll(List<Long> ids) {
        return Collections.emptyList();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {

        String queryStr = "DELETE FROM Song AS s WHERE s.id = :id";
        SessionFactory sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery(queryStr);
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
//        TypedQuery<Song> query = entityManager.createQuery(queryStr, Song.class);
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
////        Query query = session.createQuery(queryStr);
//        query.setParameter("id",id);
//        query.getSingleResult();
//        transaction.commit();
    }

    @Override
    public void delete(Song song) {
    }

    @Override
    public void delete(List<Song> songs) {
    }

    @Override
    public void deleteAll() {
    }
}
