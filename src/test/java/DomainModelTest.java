import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    @Test
    public void testJPA() {
        Storefront storefront = new Storefront("weidian", "Weidian", "https://weidian.com/item.html?itemID={}");
        Product product = new Product(storefront, "1234678");

        QCPhotoProvider superbuy = new QCPhotoProvider("superbuy", "Superbuy");

        QcPhotoSet qcPhotoSet = new QcPhotoSet(superbuy);
        QcPhoto qcPhoto = new QcPhoto("https://superbuy.com/image");
        qcPhotoSet.addQcPhoto(qcPhoto);

        product.addQCPhotoSet(qcPhotoSet);

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();

        em.close();
    }
}
