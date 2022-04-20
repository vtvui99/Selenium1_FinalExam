package Common;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtils {
    public static void initDriver(){
        switch (Constant.BROWSER.toLowerCase()){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "Executables/chromedriver.exe");
                Constant.DRIVER = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "Executables/geckodriver.exe");
                Constant.DRIVER = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "Executables/msedgedriver.exe");
                Constant.DRIVER = new EdgeDriver();
        }
        Constant.DRIVER.manage().timeouts().implicitlyWait(Constant.LONG_WAIT, TimeUnit.SECONDS);
        Constant.DRIVER.manage().window().maximize();
    }

    public static void navigate(String URL){
        Constant.DRIVER.get(URL);
    }

    public static void quitBrowser(){
        Constant.DRIVER.quit();
    }
}
