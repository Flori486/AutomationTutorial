package tests;

import helpMethods.ElementHelper;
import helpMethods.PageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.SharedData;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PracticeFormTest extends SharedData {
//    public WebDriver driver;

    @Test
    public void metodaTest (){

//        driver=new ChromeDriver();
//        driver.get("https://demoqa.com/");
//        driver.manage().window().maximize();

        ElementHelper elementHelper= new ElementHelper(driver);

        PageHelper pageHelper= new PageHelper(driver);

        By formsMenu= By.xpath("//h5[text()='Forms']");
        elementHelper.clickJSLocator(formsMenu);

        By practiceFormSubMenu= By.xpath("//span[text()='Practice Form']");
        elementHelper.clickJSLocator(practiceFormSubMenu);

        List<WebElement> practiceFormRowsList=driver.findElements(By.xpath("//div[@class='mt-2 row']"));
        Assert.assertEquals(practiceFormRowsList.size(),10,"validare de dimensiune");

        //input[@placeholder='First Name']
        By firstNameElement= By.xpath("//input[@placeholder='First Name']");
        String firstNameValue= "Flori";
        elementHelper.fillLocator(firstNameElement, firstNameValue);

        //input[@placeholder='Last Name']
        By lastNameElement= By.xpath("//input[@placeholder='Last Name']");
        String lastNameValue= "Nitu";
        elementHelper.fillLocator(lastNameElement, lastNameValue);

        //input[@placeholder='name@example.com']
        By emailElement= By.xpath("//input[@placeholder='name@example.com']");
        String emailValue= "f.nitu@gmail.com";
        elementHelper.fillLocator(emailElement, emailValue);

        // //input[@name='gender']
        String genderValuet="Female";
        By genderOptionsElement= By.xpath("//input[@name='gender']");
        List<WebElement> genderOptionsList= driver.findElements(genderOptionsElement);
        if (genderValue.equals("Male")){
            elementHelper.clickJSLocator(genderOptionsList.get(0));
        }
        if(genderValue.equals("Female")){
            elementHelper.clickJSLocator(genderOptionsList.get(1));
        }
        if (genderValue.equals("Other")){
            elementHelper.clickJSLocator(genderOptionsList.get(2));
        }

        //input[@placeholder='Mobile Number']
        WebElement mobilePhoneElement= driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
        String mobilePhoneValue = "0123456789";
        mobilePhoneElement.sendKeys(mobilePhoneValue);
        pageHelper.scrollByPixel(0, 500);

        By subjectsElement = By.id("subjectsInput");
        elementHelper.clickJSLocator(subjectsElement);
        //String subjectsValue="Maths";
        //subjectsElement.sendKeys(subjectsValue);
        //subjectsElement.sendKeys(Keys.ENTER);
        //String subjects2Value="Arts";
        //subjectsElement.sendKeys(subjects2Value);
        //subjectsElement.sendKeys(Keys.ENTER);

        //Redam lista de stringuri si adaugam elementele pe care vrem sa le selectam
        //Parcurgem lista facuta cu un for
        //In interiorul for-ului trebuie sa completam pe rand fiecare valoare in subjectsElement si apoi sa apasam un enter

        List<String> subjects=new ArrayList<>();
        subjects.add("Maths");
        subjects.add("Arts");
        subjects.add("Biology");

        for (int index=0;index<subjects.size();index++){
//            subjectsElement.sendKeys(subjects.get(index));
//            subjectsElement.sendKeys(Keys.ENTER);
            elementHelper.fillPressLocator(subjectsElement, subjects.get(index), Keys.ENTER);
        }

        List<String> hobbies= new ArrayList<>();
        hobbies.add("Sports");
        hobbies.add("Reading");
        hobbies.add("Music");

        List<WebElement> hobbiesOptionList=driver.findElements(By.xpath("//div[@id='hobbiesWrapper']//label"));
        for (int index=0;index<hobbiesOptionList.size();index++){
            String currentText=hobbiesOptionList.get(index).getText();
            if (hobbies.contains(currentText)){
                executor.executeScript("arguments[0].click();", hobbiesOptionList.get(index));;
//                hobbiesOptionList.get(index).click();
            }
        }

        By pictureElement= By.id("uploadPicture");
        File file = new File("src/test/resources/Screenshot 2025-01-05 120323.png");
        elementHelper.fillLocator(pictureElement, file.getAbsolutePath());

        By addressElement= By.id("currentAddress");
        String addressValue="Dolj";
        elementHelper.fillLocator(addressElement, addressValue);

        By stateElement=By.xpath("//div[text()='Select State']");
        elementHelper.clickJSLocator(stateElement);

        By stateInputElement=By.id("react-select-3-input");
        String stateValue="NCR";
        elementHelper.fillPressLocator(stateInputElement, stateValue, Keys.ENTER);

        By cityElement= By.xpath("//div[text()='Select City']");
        elementHelper.clickJSLocator(cityElement);

        By cityInputElement= By.id("react-select-4-input");
        String cityValue="Delhi";
        elementHelper.fillPressLocator(cityInputElement, cityValue, Keys.ENTER);

        By submitElement= By.id("submit");
        elementHelper.clickLocator(submitElement);


        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        WebElement thankYouElement=driver.findElement(By.id("example-modal-sizes-title-lg"));
        String expectedMessage="Thanks for submitting the form";
        String actualMessage=thankYouElement.getText();
        Assert.assertEquals(actualMessage.trim(),expectedMessage);

        List<WebElement> tableRowsList=driver.findElements(By.xpath("//tbody/tr"));
        Assert.assertTrue(tableRowsList.get(0).getText().contains("Student Name"));
        Assert.assertTrue(tableRowsList.get(0).getText().contains(firstNameValue));
        Assert.assertTrue(tableRowsList.get(0).getText().contains(lastNameValue));

        Assert.assertTrue(tableRowsList.get(1).getText().contains("Student Email"));
        Assert.assertTrue(tableRowsList.get(1).getText().contains(emailValue));

        Assert.assertTrue(tableRowsList.get(2).getText().contains("Gender"));
        Assert.assertTrue(tableRowsList.get(2).getText().contains(genderValue));

        Assert.assertTrue(tableRowsList.get(3).getText().contains("Mobile"));
        Assert.assertTrue(tableRowsList.get(3).getText().contains(mobilePhoneValue));

        String subectsStringValue = String.join(", ", subjects);
        Assert.assertTrue(tableRowsList.get(5).getText().contains("Subjects"));
        Assert.assertTrue(tableRowsList.get(5).getText().contains(subectsStringValue));

        String hobbiesStringValue = String.join(", ", hobbies);
        Assert.assertTrue(tableRowsList.get(6).getText().contains("Hobbies"));
        Assert.assertTrue(tableRowsList.get(6).getText().contains(hobbiesStringValue));


        Assert.assertTrue(tableRowsList.get(7).getText().contains("Picture"));
        Assert.assertTrue(tableRowsList.get(7).getText().contains("Screenshot 2025-01-05 120323.png"));

        Assert.assertTrue(tableRowsList.get(8).getText().contains("Address"));
        Assert.assertTrue(tableRowsList.get(8).getText().contains(addressValue));

        Assert.assertTrue(tableRowsList.get(9).getText().contains("State and City"));
        Assert.assertTrue(tableRowsList.get(9).getText().contains(stateValue));
        Assert.assertTrue(tableRowsList.get(9).getText().contains(cityValue));
    }
}
