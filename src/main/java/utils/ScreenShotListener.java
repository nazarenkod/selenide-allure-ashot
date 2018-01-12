package utils;

import io.qameta.allure.Attachment;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Configuration.browserSize;


public class ScreenShotListener {

    public static String baseDir = "src/test/screenshots/"+ browserSize;
    public static String expectedDir = baseDir +  "/expected/" ;
    public static String actualDir = baseDir +  "/actual/";
    public static String diffDir = baseDir +  "/diff/";


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

    public static void createFolders() throws IOException {
        File resourcesImagesDir = new File(baseDir);
        File expectedFolder = new File(expectedDir);
        System.out.println(expectedFolder.getAbsolutePath() );
        File actualFolder = new File(actualDir);
        File diffFolder = new File(diffDir);
        if (!resourcesImagesDir.exists() || !expectedFolder.exists() || !actualFolder.exists() || !diffFolder.exists()){
            resourcesImagesDir.mkdirs();
            expectedFolder.mkdirs();
            actualFolder.mkdirs();
            diffFolder.mkdirs();
        }

    }


}

