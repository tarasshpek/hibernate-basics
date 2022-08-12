package com.example.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class JpaContainerProductDao implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Async
    public void create(Product product) {
        entityManager.persist(product);
        entityManager.detach(product);
        product.setName("UPDATED");
    }

    @Override
    public List<Product> readAll() {
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }
}
