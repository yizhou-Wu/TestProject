package com.example.AuthService.dao;

import com.example.AuthService.entity.Login;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class LoginDAO {
    private SessionFactory sessionFactory;
    @Autowired
    public LoginDAO(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    public void create(Login login){
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(login);
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }

    public List<Login> getAllByUserId(int user_id){
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Login> criteriaQuery = criteriaBuilder.createQuery(Login.class);
            Root<Login> root = criteriaQuery.from(Login.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("user_id"),user_id));
            return session.createQuery(criteriaQuery).stream().collect(Collectors.toList());
        }
        catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }
}
