package com.example.UserService.dao;

import com.example.UserService.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDAO {
    private SessionFactory sessionFactory;
    @Autowired
    public UserDAO(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    };
    public void create(User user){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(user);
        }
        catch (HibernateException e){
            return;
        }
    }

    public User getById(int Id){
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"),Id));
            return session.createQuery(criteriaQuery).uniqueResult();
        }
        catch (HibernateException e){
            return null;
        }
    }
    public User getByEmail(String email){
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("email"),email));
            return session.createQuery(criteriaQuery).uniqueResult();
        }
        catch (HibernateException e){
            return null;
        }
    }
}
