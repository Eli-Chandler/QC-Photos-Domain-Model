package factory;

import com.qcphotos.model.Product;
import com.qcphotos.model.Storefront;

import java.util.Random;

public class ProductFactory {
    private static final Random random = new Random();

    // Default values for creating a product
    private static final float DEFAULT_PRICE = 100.0f;
    private static final float DEFAULT_FREIGHT = 10.0f;
    private static final float DEFAULT_WIDTH = 5.0f;
    private static final float DEFAULT_LENGTH = 10.0f;
    private static final float DEFAULT_HEIGHT = 3.0f;
    private static final String DEFAULT_THUMBNAIL_URL = "http://example.com/default.jpg";

    // Create a basic product with minimal parameters
    public static Product createProduct(String listingId, Storefront storefront) {
        return new Product(listingId, storefront, DEFAULT_PRICE, DEFAULT_FREIGHT, DEFAULT_WIDTH, DEFAULT_LENGTH, DEFAULT_HEIGHT, DEFAULT_THUMBNAIL_URL);
    }

    // Create a product with specific attributes
    public static Product createProduct(String listingId, Storefront storefront, float price, float domesticFreight, float width, float length, float height, String thumbnailUrl) {
        return new Product(listingId, storefront, price, domesticFreight, width, length, height, thumbnailUrl);
    }

    // Create a random product
    public static Product createRandomProduct(Storefront storefront) {
        String listingId = "p" + random.nextInt(1000000);
        float price = 50 + 150 * random.nextFloat();
        float freight = 5 + 20 * random.nextFloat();
        float width = 1 + 10 * random.nextFloat();
        float length = 1 + 15 * random.nextFloat();
        float height = 1 + 8 * random.nextFloat();
        String thumbnailUrl = "http://example.com/image" + listingId + ".jpg";
        return createProduct(listingId, storefront, price, freight, width, length, height, thumbnailUrl);
    }
}
