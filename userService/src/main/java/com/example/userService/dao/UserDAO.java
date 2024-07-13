package com.example.userService.dao;

import com.example.userService.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
