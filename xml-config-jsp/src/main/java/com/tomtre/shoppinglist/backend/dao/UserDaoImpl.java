package com.tomtre.shoppinglist.backend.dao;

import com.tomtre.shoppinglist.backend.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean checkIfUserNameExists(String userName) {
        Session session = sessionFactory.getCurrentSession();
        return checkIfValueExists(session, User.USER_NAME_COLUMN_NAME, userName);
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        Session session = sessionFactory.getCurrentSession();
        return checkIfValueExists(session, User.EMAIL_COLUMN_NAME, email);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u WHERE u.userName = :userName", User.class);
        query.setParameter("userName", userName);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<User> findWithRolesByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u JOIN FETCH u.roles r WHERE u.userName = :userName", User.class);
        query.setParameter("userName", userName);
        return query.uniqueResultOptional();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }


    private boolean checkIfValueExists(Session session, String columnName, String value) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root.get(User.ID_COLUMN_NAME));
        criteriaQuery.where(criteriaBuilder.equal(root.get(columnName), value));
        Query<Long> query = session.createQuery(criteriaQuery);
        Long id = query.uniqueResult();
        return id != null;
    }
}
