package com.tomtre.shoppinglist.backend.dao;

import com.tomtre.shoppinglist.backend.entity.BaseEntity;
import com.tomtre.shoppinglist.backend.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
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
    public Optional<Product> getProduct(UUID productId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, productId);
        return Optional.ofNullable(product);
    }

    @Override
    public void removeProduct(UUID productId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Product p where p.id = :productId");
        query.setParameter("productId", productId);
        query.executeUpdate();
    }

    @Override
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Override
    public void checkProduct(UUID productId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Product p set p.checked = true where p.id = :productId");
        query.setParameter("productId", productId);
        query.executeUpdate();
    }

    @Override
    public void uncheckProduct(UUID productId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update Product p set p.checked = false where p.id = :productId");
        query.setParameter("productId", productId);
        query.executeUpdate();
    }

    @Override
    public boolean checkIfProductExists(UUID productId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UUID> criteriaQuery = criteriaBuilder.createQuery(UUID.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root.get(BaseEntity.ID_NAME));
        criteriaQuery.where(criteriaBuilder.equal(root.get(BaseEntity.ID_NAME), productId));
        Query<UUID> query = session.createQuery(criteriaQuery);
        UUID uuid = query.uniqueResult();
        return uuid != null;
    }
}
