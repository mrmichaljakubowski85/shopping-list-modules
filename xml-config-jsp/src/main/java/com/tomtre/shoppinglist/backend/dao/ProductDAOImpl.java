package com.tomtre.shoppinglist.backend.dao;

import com.tomtre.shoppinglist.backend.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("from Product", Product.class);
        return query.getResultList();
    }

    @Override
    public Product getProduct(UUID productId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Product.class, productId);
    }

    @Override
    public void removeProduct(UUID productId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Product where id = :productId");
        query.setParameter("productId", productId);
        query.executeUpdate();
    }

    @Override
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(product);
    }
}
