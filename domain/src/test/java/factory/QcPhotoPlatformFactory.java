package factory;

import com.qcphotos.model.QcPhotoPlatform;

public class QcPhotoPlatformFactory {

    // Default values for creating a photo platform
    private static final String DEFAULT_NAME = "defaultPlatform";
    private static final String DEFAULT_DISPLAY_NAME = "Default Photo Platform";

    // Create a QcPhotoPlatform with default parameters
    public static QcPhotoPlatform createDefaultPhotoPlatform() {
        return new QcPhotoPlatform(DEFAULT_NAME, DEFAULT_DISPLAY_NAME);
    }

    // Create a QcPhotoPlatform with specific attributes
    public static QcPhotoPlatform createPhotoPlatform(String name, String displayName) {
        return new QcPhotoPlatform(name, displayName);
    }
}