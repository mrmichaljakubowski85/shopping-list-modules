package com.tomtre.shoppinglist.backend.util;

import com.tomtre.shoppinglist.backend.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DbUtils {
//    public static boolean checkIfValueExists(Session session, Class kla String columnName, String value) {
//        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
//        Root<User> root = criteriaQuery.from(User.class);
//        criteriaQuery.select(root.get(User.ID_COLUMN_NAME));
//        criteriaQuery.where(criteriaBuilder.equal(root.get(columnName), value));
//        Query<Long> query = session.createQuery(criteriaQuery);
//        Long id = query.uniqueResult();
//        return id != null;
//    }
}
