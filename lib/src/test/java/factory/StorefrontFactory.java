package factory;

import com.qcphotos.model.Storefront;

public class StorefrontFactory {
    // Default values for creating a storefront
    private static final String DEFAULT_NAME = "defaultStore";
    private static final String DEFAULT_DISPLAY_NAME = "Default Store";
    private static final String DEFAULT_URL_FORMAT = "http://example.com/{id}";

    // Create a storefront with default parameters
    public static Storefront createDefaultStorefront() {
        return new Storefront(DEFAULT_NAME, DEFAULT_DISPLAY_NAME, DEFAULT_URL_FORMAT);
    }

    // Create a storefront with specific attributes
    public static Storefront createStorefront(String name, String displayName, String urlFormat) {
        return new Storefront(name, displayName, urlFormat);
    }
}
