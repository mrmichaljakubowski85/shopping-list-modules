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
    public boolean checkIfUsernameExists(String username) {
        Session session = sessionFactory.getCurrentSession();
        return checkIfValueExists(session, User.USER_NAME_COLUMN_NAME, username);
    }

    @Override
    public boolean checkIfEmailExists(String email) {
        Session session = sessionFactory.getCurrentSession();
        return checkIfValueExists(session, User.EMAIL_COLUMN_NAME, email);
    }

    @Override
    public Optional<User> findByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.uniqueResultOptional();
    }

    @Override
    public Optional<User> findWithRolesByUserName(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u JOIN FETCH u.roles r WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query.uniqueResultOptional();
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    //todo check
    private boolean checkIfValueExists(Session session, String columnName, String value) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root root = criteriaQuery.from(User.class);
        criteriaQuery.select(root.get(columnName));
        criteriaQuery.where(criteriaBuilder.equal(root.get(columnName), value));
        Query query = session.createQuery(criteriaQuery);
        Object result = query.uniqueResult();
        return result != null;
    }
}
