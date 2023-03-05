package org.server.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public final class ImageUtils {

    public static byte[] getBytes(BufferedImage bufferedImage, String format) {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, format, bos);
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* try {
            File imageFile = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                    .getResource("image.png")).getFile());
            bytes = Files.readAllBytes(imageFile.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    private ImageUtils() {

    }
}
