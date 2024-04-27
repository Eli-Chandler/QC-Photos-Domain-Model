package testModel;

import com.qcphotos.model.Product;
import com.qcphotos.model.QcPhotoSet;
import factory.ProductFactory;
import factory.QcPhotoFactory;
import factory.QcPhotoPlatformFactory;
import factory.StorefrontFactory;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

public class TestProduct {

    @Test
    public void testInstantiateProduct() {
        Product product = new Product("12345678", StorefrontFactory.createDefaultStorefront());

        Product product2 = new Product("1234567", StorefrontFactory.createDefaultStorefront(), 100.0F, 10.0F, 15.0F, 15.0F, 15.0F, "https://example.com/thumbnail.jpg");
    }

    @Test
    public void testAddQcPhotoSet() {
        Product product = ProductFactory.createProduct("12345678", StorefrontFactory.createDefaultStorefront());

        QcPhotoSet qcPhotoSet1 = new QcPhotoSet(QcPhotoPlatformFactory.createDefaultPhotoPlatform());
        QcPhotoSet identicalQcPhotoSet1 = new QcPhotoSet(QcPhotoPlatformFactory.createDefaultPhotoPlatform());

        product.addQcPhotoSet(qcPhotoSet1);
        assert product.getQcPhotoSets().size() == 1;

        product.addQcPhotoSet(qcPhotoSet1);
        assert product.getQcPhotoSets().size() == 1;

        product.addQcPhotoSet(identicalQcPhotoSet1);
        assert product.getQcPhotoSets().size() == 1;

        QcPhotoSet qcPhotoSet2 = new QcPhotoSet(QcPhotoPlatformFactory.createDefaultPhotoPlatform());
        qcPhotoSet2.addQcPhoto(QcPhotoFactory.createQcPhoto("https://example.com/qcphoto.jpg"));

        product.removeQcPhotoSet(qcPhotoSet2);
        assert product.getQcPhotoSets().size() == 1; // If it's not inside, do nothing

        product.addQcPhotoSet(qcPhotoSet2);
        assert product.getQcPhotoSets().size() == 2;

        product.addQcPhotoSet(qcPhotoSet2);
        assert product.getQcPhotoSets().size() == 2;

        for (QcPhotoSet qcPhotoSet: product.getQcPhotoSets()) {
            assert qcPhotoSet.getProduct().equals(product);
        }

        product.removeQcPhotoSet(identicalQcPhotoSet1);
        assert product.getQcPhotoSets().size() == 1;

        product.removeQcPhotoSet(qcPhotoSet2);
        assert product.getQcPhotoSets().isEmpty();
    }

    @Test
    public void testUpdateFetchedTime() {
        Product product = ProductFactory.createProduct("12345678", StorefrontFactory.createDefaultStorefront());
        product.setFetchedTime(Date.from(Instant.now().minusSeconds(1))); // Directly sets the time
        Date originalTime = product.getFetchedTime();

        product.updateFetchedTime(); // Sets to the current time
        Date newTime = product.getFetchedTime();

        assert originalTime.before(newTime);
    }
}
