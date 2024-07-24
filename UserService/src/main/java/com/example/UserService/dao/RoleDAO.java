package com.example.UserService.dao;

import com.example.UserService.entity.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.time.Instant;

@Repository
public class RoleDAO {
    private SessionFactory sessionFactory;
    @Autowired
    public RoleDAO(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}
    public int create(String role_name, String description){
        try {
            Session session = sessionFactory.getCurrentSession();
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            Role role = Role.builder()
                    .role_name(role_name)
                    .description(description)
                    .create_date(currentTimestamp)
                    .build();
            return (int)session.save(role);
        }
        catch (HibernateException e){
            e.printStackTrace();
            return -1;
        }
    }
    public Role getById(int id){
        try {
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> root = criteriaQuery.from(Role.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"),id));
            return session.createQuery(criteriaQuery).uniqueResult();
        }
        catch (HibernateException e){
            e.printStackTrace();
            return null;
        }
    }
}
