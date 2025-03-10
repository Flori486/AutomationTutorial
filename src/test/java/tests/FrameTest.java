package tests;

import helpMethods.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import sharedData.SharedData;

public class FrameTest extends SharedData {
//    public WebDriver driver;

    @Test
    public void metodaTest () {
//        //deschidem un browser
//        driver = new ChromeDriver();
//
//        //accesam o pagina web
//        driver.get("https://demoqa.com/");
//
//        //browserul in fereastra maximized //h5[text()='Elements']
//        driver.manage().window().maximize();

        ElementHelper elementHelper= new ElementHelper(driver);

        By elementsMenu = By.xpath("//h5[text()='Elements']");
        elementHelper.clickJSLocator(elementsMenu);

        By framesMenu = By.xpath("//span[text()='Frames']");
        elementHelper.clickJSLocator(framesMenu);

       driver.switchTo().frame("frame1");

        WebElement textElement = driver.findElement(By.id("sampleHeading"));
        System.out.println(textElement.getText());

        //frame2
        driver.switchTo().parentFrame();

        driver.switchTo().frame("frame2");

        WebElement textElement2 = driver.findElement(By.id("sampleHeading"));
        System.out.println(textElement2.getText());
    }
}
