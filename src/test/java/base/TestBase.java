package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utils.PropertyLoader;
import utils.ScreenShotListener;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static utils.ScreenShotListener.createFolders;



public class TestBase {

    public static String browserSize = PropertyLoader.getInstance().getProperty("browserSize");


    @BeforeSuite
    public void init(){

        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://www.google.com.ua";
        Configuration.browserSize = browserSize;
        try {
            createFolders("src/test/screenshots");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void start(){
        open("/");
    }

}
