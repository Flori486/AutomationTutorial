package sharedData;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SharedData {
    // Aceasta clasa are rolul de a deschide/ inchide un browser
    // Acest lucru se realizeaza folosind adnotari de testNG

    public WebDriver driver;

    @BeforeMethod
    public void prepareEnvironment() {
        //deschidem un browser
        driver = new ChromeDriver();
        //browserul in fereastra maximized //h5[text()='Elements']
        driver.manage().window().maximize();
        //accesam o pagina web
        driver.get("https://demoqa.com/");

    }

    @AfterMethod
    public void clearEnvironment() {
        driver.quit();
     }
}
