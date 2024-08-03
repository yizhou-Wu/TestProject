package com.example.UserService.dao;

import com.example.UserService.entity.Role;
import com.example.UserService.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.*;

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
            e.printStackTrace();
        }
    }

    public User getById(int id){
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"),id));
            return session.createQuery(criteriaQuery).uniqueResult();
        }
        catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }
    public User getByUsername(String username){
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"),username));
            return session.createQuery(criteriaQuery).uniqueResult();
        }
        catch (HibernateException e){
            e.printStackTrace();
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
            e.printStackTrace();
            return null;
        }
    }
    public Set<Role> getRolesById(int id){
        User user = getById(id);
        if (user == null){
            return null;
        }
        return new HashSet<Role>(user.getRoleSet());
    }
    public void setRole(int user_id, Role role){
        User user = getById(user_id);
        Set<Role> set = user.getRoleSet();
        set.add(role);
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
    }
}
