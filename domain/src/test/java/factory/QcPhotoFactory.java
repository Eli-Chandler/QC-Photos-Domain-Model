package factory;

import com.qcphotos.model.QcPhoto;

import java.util.Random;

public class QcPhotoFactory {
    private static final Random random = new Random();

    // Create a QcPhoto with a specific photo URL
    public static QcPhoto createQcPhoto(String photoUrl) {
        return new QcPhoto(photoUrl);
    }

    // Create a QcPhoto with a random photo URL from predefined samples
    public static QcPhoto createRandomQcPhoto() {
        String randomPhotoUrl = "https://example.com/qcphoto/" + random.nextInt() + ".jpg";
        return createQcPhoto(randomPhotoUrl);
    }
}
