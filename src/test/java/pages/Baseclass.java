package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Baseclass {

    static WebDriver driver;

    public static void init(){
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("autofill.profile_enabled",false);
        options.setExperimentalOption("prefs",prefs);

        driver = new ChromeDriver(options);
    }
    public static void navigateToURL(){
        init();
        driver.get("https://automationexercise.com/");

    }

    public void teardown(){
        driver.close();
        driver.quit();
    }

    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "example.com"};
        String[] characters = {"abcdefghijklmnopqrstuvwxyz1234567890"};
        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generating a random username with a length between 5 and 10 characters
        int usernameLength = random.nextInt(6) + 5;
        for (int i = 0; i < usernameLength; i++) {
            email.append(characters[0].charAt(random.nextInt(characters[0].length())));
        }

        // Appending "@" symbol and a random domain from the list
        email.append("@").append(domains[random.nextInt(domains.length)]);

        return email.toString();
    }

}
