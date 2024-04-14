import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DomainModelTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("model");
    }

    @After
    public void tearDown() {
        entityManagerFactory.close();
    }

    public Product createProduct(String id) {
        Storefront storefront = new Storefront("weidian", "Weidian", "https://weidian.com/item.html?itemID={}");
        Product product = new Product(id, storefront);

        QcPhotoProvider superbuy = new QcPhotoProvider("superbuy", "Superbuy");

        QcPhotoSet qcPhotoSet = new QcPhotoSet(superbuy);
        QcPhoto qcPhoto = new QcPhoto("https://superbuy.com/image");
        qcPhotoSet.addQcPhoto(qcPhoto);

        product.addQcPhotoSet(qcPhotoSet);

        return product;
    }

    @Test
    public void testPersistingProduct() {
        EntityManager em = entityManagerFactory.createEntityManager();

        Product product = createProduct("123");

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();

        em.close();
    }

    @Test
    public void testPersistingProductTwice() {
        EntityManager em = entityManagerFactory.createEntityManager();

        Product product1 = createProduct("123");

        em.getTransaction().begin();
        em.persist(product1);
        em.getTransaction().commit();

        Product product2 = createProduct("123");

        em.getTransaction().begin();
        em.persist(product2);
        em.getTransaction().commit();

        em.close();
    }

    @Test
    public void testRemovingProduct() {
        EntityManager em = entityManagerFactory.createEntityManager();

        Product product = createProduct("123");

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();

        TypedQuery<Product> productTypedQuery = em.createQuery(
                "select p from Product p where p.productId.listingId = '123'",
                Product.class
        );

        Product productResult = productTypedQuery.getSingleResult();
        assert productResult.getListingId().equals("123");

        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();

        assert productTypedQuery.getResultList().isEmpty();
    }

    @Test
    public void testRemovingQcImageSet() {
        EntityManager em = entityManagerFactory.createEntityManager();

        Product product = createProduct("123");

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();

        for (QcPhotoSet qcPhotoSet : product.getQcPhotoSets()) {
            product.removeQcPhotoSet(qcPhotoSet);
        }

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();

        TypedQuery<QcPhotoSet> qcPhotoSetTypedQuery = em.createQuery(
                "select q from QcPhotoSet q",
                QcPhotoSet.class
        );

        assert qcPhotoSetTypedQuery.getResultList().isEmpty();
    }
}
