package com.tomtre.shoppinglist.backend.dao;

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
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findProductsOrderByCreateDateTime(long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product p WHERE p.user.id = :userId ORDER BY p.createDateTime", Product.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public Optional<Product> findProduct(UUID productId, long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("FROM Product p WHERE p.id = :productId AND p.user.id = :userId", Product.class);
        query.setParameter("productId", productId);
        query.setParameter("userId", userId);
        return query.uniqueResultOptional();
    }

    @Override
    public void removeProduct(UUID productId, long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Product p WHERE p.id = :productId AND p.user.id = :userId");
        query.setParameter("productId", productId);
        query.setParameter("userId", userId);
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
        //todo We have to get the Product from db first, otherwise @CreationTimestamp won't work (will be set as null). Check more optimized solutions!
        Product persistedProduct = session.get(Product.class, product.getId());
        persistedProduct.setChecked(product.isChecked());
        persistedProduct.setDescription(product.getDescription());
        persistedProduct.setQuantity(product.getQuantity());
        persistedProduct.setUnit(product.getUnit());
        persistedProduct.setTitle(product.getTitle());
        session.update(persistedProduct);
    }

    @Override
    public void checkProduct(UUID productId, long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Product p SET p.checked = true WHERE p.id = :productId AND p.user.id = :userId");
        query.setParameter("productId", productId);
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public void uncheckProduct(UUID productId, long userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Product p SET p.checked = false WHERE p.id = :productId AND p.user.id = :userId");
        query.setParameter("productId", productId);
        query.setParameter("userId", userId);

        query.executeUpdate();
    }

    @Override
    public boolean checkIfProductExists(UUID productId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<UUID> criteriaQuery = criteriaBuilder.createQuery(UUID.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root.get(Product.ID_COLUMN_NAME));
        criteriaQuery.where(criteriaBuilder.equal(root.get(Product.ID_COLUMN_NAME), productId));
        Query<UUID> query = session.createQuery(criteriaQuery);
        UUID uuid = query.uniqueResult();
        return uuid != null;
    }
}
