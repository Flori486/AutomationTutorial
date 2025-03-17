package tests;
import helpMethods.ElementHelper;
import helpMethods.PageHelper;
import org.openqa.selenium.*;
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


    @Test
    public void metodaTest() {
        ElementHelper elementHelper=new ElementHelper(driver);
        PageHelper pageHelper=new PageHelper(driver);

        By formsMenu = By.xpath("//h5[text()='Forms']");
        elementHelper.clickJSLocator(formsMenu);

        By practiceFormSubmenu = By.xpath("//span[text()='Practice Form']");
        elementHelper.clickLocator(practiceFormSubmenu);

        List<WebElement> practiceFormRowsList=driver.findElements(By.xpath("//div[@class='mt-2 row']"));
        Assert.assertEquals(practiceFormRowsList.size(),10,"validare de dimensiune");

        By firstNameElement =By.xpath("//input[@placeholder='First Name']");
        String firstNameValue = "Florentina";
        elementHelper.fillLocator(firstNameElement,firstNameValue);

        By lastNameElement = By.xpath("//input[@placeholder='Last Name']");
        String lastNameValue = "Nitu";
        elementHelper.fillLocator(lastNameElement,lastNameValue);

        By emailElement = By.xpath("//input[@placeholder='name@example.com']");
        String emailValue = "f.nitu@gmail.com";
        elementHelper.fillLocator(emailElement,emailValue);

        String genderValue="Female";
        By genderOptionElement=By.xpath("//input[@name='gender']");
        List<WebElement> genderOptionList=driver.findElements(genderOptionElement);
        if (genderValue.equals("Male")){
            elementHelper.clickJSLocator(genderOptionList.get(0));
        }
        if (genderValue.equals("Female")){
            elementHelper.clickJSLocator(genderOptionList.get(1));
        }
        if (genderValue.equals("Other")){
            elementHelper.clickJSLocator(genderOptionList.get(2));
        }

        By mobilePhoneElement = By.xpath("//input[@placeholder='Mobile Number']");
        String mobilePhoneValue = "0123456789";
        elementHelper.fillLocator(mobilePhoneElement,mobilePhoneValue);
//        pageHelper.scrollByPixel(0,500);

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
            elementHelper.fillPressLocator(subjectsElement,subjects.get(index),Keys.ENTER);
        }

        List<String> hobbies= new ArrayList<>();
        hobbies.add("Sports");
        hobbies.add("Reading");
        hobbies.add("Music");

        List<WebElement> hobbiesOptionList=driver.findElements(By.xpath("//div[@id='hobbiesWrapper']//label"));
        for (int index=0;index<hobbiesOptionList.size();index++){
            String currentText=hobbiesOptionList.get(index).getText();
            if (hobbies.contains(currentText)){
                elementHelper.clickJSLocator(hobbiesOptionList.get(index));
//                hobbiesOptionList.get(index).click();
            }
        }

        By pictureElement= By.id("uploadPicture");
        File file = new File("C:\\Users\\fnitu\\IdeaProjects\\AutomationTutorial\\src\\test\\resources\\Screenshot 2025-01-05 120323.png");
        elementHelper.fillLocator(pictureElement,file.getAbsolutePath());

        By addressElement=By.id("currentAddress");
        String addressValue="Dolj";
        elementHelper.fillLocator(addressElement,addressValue);

        By stateElement=By.xpath("//div[text()='Select State']");
        elementHelper.clickJSLocator(stateElement);

        By stateInputElement=By.id("react-select-3-input");
        String stateInputValue="NCR";
//        stateInputElement.sendKeys(stateValue);
//        stateInputElement.sendKeys(Keys.ENTER);
        elementHelper.fillPressLocator(stateInputElement,stateInputValue,Keys.ENTER);

        By cityElement=By.xpath("//div[text()='Select City']");
        elementHelper.clickJSLocator(cityElement);

        By cityInputElement= By.id("react-select-4-input");
        String cityInputValue="Delhi";
//        cityInputElement.sendKeys(cityValue);
//        cityInputElement.sendKeys(Keys.ENTER);
        elementHelper.fillPressLocator(cityInputElement, cityInputValue,Keys.ENTER);

        By submitElement=By.id("submit");
        elementHelper.clickLocator(submitElement);

        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

        By thankYouElement=By.id("example-modal-sizes-title-lg");
        String expectedMessage="Thanks for submitting the form";
        elementHelper.validateTextLocator(thankYouElement, expectedMessage);
//        String actualMessage=thankYouElement.getText();
//        Assert.assertEquals(actualMessage.trim(),expectedMessage);

        By tableRowsListElement=By.xpath("//tbody/tr");
        List<WebElement> tableRowsList=driver.findElements(tableRowsListElement);
        elementHelper.validateTextContainsElement(tableRowsList.get(0), "Student Name");
        elementHelper.validateTextContainsElement(tableRowsList.get(0), firstNameValue);
        elementHelper.validateTextContainsElement(tableRowsList.get(0), lastNameValue);

//        Assert.assertTrue(tableRowsList.get(0).getText().contains("Student Name"));
//        Assert.assertTrue(tableRowsList.get(0).getText().contains(firstNameValue));
//        Assert.assertTrue(tableRowsList.get(0).getText().contains(lastNameValue));

        elementHelper.validateTextContainsElement(tableRowsList.get(1), "Student Email");
        elementHelper.validateTextContainsElement(tableRowsList.get(1), emailValue);
//        Assert.assertTrue(tableRowsList.get(1).getText().contains("Student Email"));
//        Assert.assertTrue(tableRowsList.get(1).getText().contains(emailValue));

        elementHelper.validateTextContainsElement(tableRowsList.get(2), "Gender");
        elementHelper.validateTextContainsElement(tableRowsList.get(2), genderValue);
//        Assert.assertTrue(tableRowsList.get(2).getText().contains("Gender"));
//        Assert.assertTrue(tableRowsList.get(2).getText().contains(genderValue));

        elementHelper.validateTextContainsElement(tableRowsList.get(3), "Mobile");
        elementHelper.validateTextContainsElement(tableRowsList.get(3), mobilePhoneValue);
//        Assert.assertTrue(tableRowsList.get(3).getText().contains("Mobile"));
//        Assert.assertTrue(tableRowsList.get(3).getText().contains(mobilePhoneValue));

        String subjectsStringValue = String.join(", ", subjects);
        elementHelper.validateTextContainsElement(tableRowsList.get(5), "Subjects");
        elementHelper.validateTextContainsElement(tableRowsList.get(5), subjectsStringValue);
//        Assert.assertTrue(tableRowsList.get(5).getText().contains("Subjects"));
//        Assert.assertTrue(tableRowsList.get(5).getText().contains(subjectsStringValue));

        String hobbiesStringValue = String.join(", ", hobbies);
        elementHelper.validateTextContainsElement(tableRowsList.get(6), "Hobbies");
        elementHelper.validateTextContainsElement(tableRowsList.get(6), hobbiesStringValue);
//        Assert.assertTrue(tableRowsList.get(6).getText().contains("Hobbies"));
//        Assert.assertTrue(tableRowsList.get(6).getText().contains(hobbiesStringValue));

        elementHelper.validateTextContainsElement(tableRowsList.get(7), "Picture");
        elementHelper.validateTextContainsElement(tableRowsList.get(7), "Screenshot 2025-01-05 120323.png");
//       Assert.assertTrue(tableRowsList.get(7).getText().contains("Picture"));
//       Assert.assertTrue(tableRowsList.get(7).getText().contains("Screenshot 2025-02-03 at 13.50.49.png"));

        elementHelper.validateTextContainsElement(tableRowsList.get(8),"Address");
        elementHelper.validateTextContainsElement(tableRowsList.get(8), addressValue);

//        Assert.assertTrue(tableRowsList.get(8).getText().contains("Address"));
//          System.out.println(tableRowsList.get(8).getText());
//          System.out.println(addressValue);
//        Assert.assertTrue(tableRowsList.get(8).getText().contains(addressValue));

        elementHelper.validateTextContainsElement(tableRowsList.get(9),"State and City");
        elementHelper.validateTextContainsElement(tableRowsList.get(9), stateInputValue);
        elementHelper.validateTextContainsElement(tableRowsList.get(9), cityInputValue);
    }
}