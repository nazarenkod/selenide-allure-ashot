package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utils.PropertyLoader;
import java.io.IOException;
import static com.codeborne.selenide.Selenide.open;
import static utils.ScreenShotListener.createFolders;



public class TestBase {

    public static String browserSize;


    @BeforeSuite
    public void init(){
        browserSize = PropertyLoader.getInstance().getProperty("browserSize");
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://www.google.com.ua";
        Configuration.browserSize = browserSize;
        try {
            createFolders();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void start(){
        open("/");
    }

}
