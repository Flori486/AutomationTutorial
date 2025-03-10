package helpMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHelper {
    public WebDriver driver;

    public AlertHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForAlertVisible() {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert(){
        waitForAlertVisible();
        Alert alertOk=driver.switchTo().alert();
        System.out.println(alertOk.getText());
        alertOk.accept();
    }

    public void cancelAlert(){
        waitForAlertVisible();
        Alert alertOk=driver.switchTo().alert();
        System.out.println(alertOk.getText());
        alertOk.dismiss();
    }

    public void fillAlert(String value){
        waitForAlertVisible();
        Alert alertOk=driver.switchTo().alert();
        System.out.println(alertOk.getText());
        alertOk.sendKeys(value);
        alertOk.accept();
    }
}
