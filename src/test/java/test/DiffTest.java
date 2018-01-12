package test;

import base.TestBase;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Label;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import utils.PropertyLoader;
import javax.imageio.ImageIO;
import static com.codeborne.selenide.Selenide.$;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static utils.ScreenShotListener.attachScreenshot;
import static utils.ScreenShotListener.toByteArray;

public class DiffTest extends TestBase {


    @Test
    public void diffTest() throws IOException {
        $(By.id("lst-ib")).sendKeys("gggg");
        //saveScreenshot(getWebDriver().getTitle());
        //makeDif();

    }

    private void makeDif() throws IOException {
        //Загрузка актуального скриншота
        File file1 = new File("src/test/screenshots/actual/" +"Google.png");
        BufferedImage image1 = ImageIO.read(file1);
        Screenshot actual = new Screenshot(image1);
        //Загрузка ожидаемого скриншота
        File file2 = new File("src/test/screenshots/expected/" +"Google.png");
        BufferedImage image2 = ImageIO.read(file2);
        Screenshot expected = new Screenshot(image2);

        //Создание скриншота с дифом
        ImageDiff diff = new ImageDiffer().makeDiff(actual, expected);
        File diffFile = new File("src/test/screenshots/diff/" +"diff.png");
        ImageIO.write(diff.getMarkedImage(),"png",diffFile);
        BufferedImage diff1  = ImageIO.read(new File("src/test/screenshots/diff/"+ "diff.png"));
        //Маркировка скринов для алюра
        Allure.addLabels(new Label().withName("testType").withValue("screenshotDiff"));
        attachScreenshot("diff", (toByteArray(diff1)));
        attachScreenshot("actual", (toByteArray(actual.getImage())));
        attachScreenshot("expected", (toByteArray(expected.getImage())));
    }

    public void saveScreenshot(String name) {
        Screenshot screenshot;
        File file;
        switch (PropertyLoader.getInstance().getProperty("createNewScreenshots")){
            case "yes":

                file = new File("src/test/screenshots/expected/" + name + ".png");
                file.mkdir();
                System.out.println(file.getAbsolutePath());
                screenshot = new AShot().takeScreenshot(getWebDriver());
                try {
                    ImageIO.write(screenshot.getImage(),"png",file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "no":
                file = new File("src/test/screenshots/actual/" +name+".png");
                screenshot = new AShot().takeScreenshot(getWebDriver());
                try {
                    ImageIO.write(screenshot.getImage(),"png",file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
