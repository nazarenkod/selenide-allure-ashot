package utils;

import io.qameta.allure.Attachment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Configuration.browserSize;


public class ScreenShotListener {



    @Attachment(value = "{name}", type = "image/png")
    public static byte[] attachScreenshot(final String name, final byte[] data) {
        return data;
    }

    public static byte[] toByteArray(final BufferedImage image) {
        try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", out);
            return out.toByteArray();
        } catch (IOException ignored) {
            return new byte[0];
        }
    }

    public static void createFolders(String rootPath) throws IOException {
        File resourcesImagesDir = new File("src/test/screenshots");
        File expectedDir = new File(resourcesImagesDir+"/expected/"+browserSize +"/");
        File actualDir = new File(resourcesImagesDir+"/actual/"+browserSize +"/");
        File diffDir = new File(resourcesImagesDir+"/diff/"+browserSize +"/");
        if (!resourcesImagesDir.exists() || !expectedDir.exists() || !actualDir.exists() || !diffDir.exists()){
            resourcesImagesDir.mkdir();
            expectedDir.mkdir();
            actualDir.mkdir();
            diffDir.mkdir();
        }

    }
}
