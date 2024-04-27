package com.qcphotos.repository;

import com.qcphotos.model.Product;
import com.qcphotos.model.ProductIdentifier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProductRepository {
    private final EntityManagerFactory entityManagerFactory;

    public ProductRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Product findProductByListingIdAndStorefrontId(String listingId, long storefrontId) {
       ProductIdentifier productIdentifier = new ProductIdentifier(listingId, storefrontId);

       return findProductByProductIdentifier(productIdentifier);
    }

    public Product findProductByProductIdentifier(ProductIdentifier productIdentifier) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.find(Product.class, productIdentifier);
        }
        finally {
            em.close();
        }
    }

    public void saveProduct(Product product) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Product product) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Product mergedProduct = em.merge(product);
            em.remove(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}