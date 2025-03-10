package tests;

import helpMethods.ElementHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import sharedData.SharedData;

import java.util.List;

public class WebtableTest extends SharedData {

//    public WebDriver driver;

    @Test
    public void metodaTest (){
//        //deschidem un browser
//        driver=new ChromeDriver();

//        //accesam o pagina web
//        driver.get("https://demoqa.com/");

//        //browserul in fereastra maximized //h5[text()='Elements']
//        driver.manage().window().maximize();

        ElementHelper elementHelper= new ElementHelper(driver);

        By elementsMenu= By.xpath("//h5[text()='Elements']");
        elementHelper.clickJSLocator(elementsMenu);

        //span[text()='Web Tables']

        By webTableSubMenu= By.xpath("//span[text()='Web Tables']");
        elementHelper.clickJSLocator(webTableSubMenu);

        //validare de dimensiune
        List<WebElement> tableRowsList= driver.findElements(By.xpath("//div[@class='rt-tbody']/div/div[@class='rt-tr -even' or @class='rt-tr -odd']"));
        Assert.assertEquals(tableRowsList.size(), 3, "Valoarea initiala a tabelului nu este 3");

        //identificam un element
        By addElement= By.id("addNewRecordButton");
        elementHelper.clickJSLocator(addElement);


        By firstNameElement= By.id("firstName");
        String firstNameValue="Nitu";
        //firstNameElement.sendKeys(firstNameValue);
        elementHelper.fillLocator(firstNameElement, firstNameValue);


        By lastNameElement= By.id("lastName");
        String lastNameValue="Florentina";
//        lastNameElement.sendKeys(lastNameValue);
        elementHelper.fillLocator(lastNameElement, lastNameValue);



        By userEmailElement= By.id("userEmail");
        String userEmailValue="user@email.com";
//        userEmailElement.sendKeys(userEmailValue);
        elementHelper.fillLocator(userEmailElement, userEmailValue);


        By ageElement= By.id("age");
        String ageValue="37";
//        ageElement.sendKeys(ageValue);
        elementHelper.fillLocator(ageElement, ageValue);


        By salaryElement= By.id("salary");
        String salaryValue="9999999999";
//        salaryElement.sendKeys(salaryValue);
        elementHelper.fillLocator(salaryElement, salaryValue);


        By departmentElement= By.id("department");
        String departmentValue="qa";
//        departmentElement.sendKeys(departmentValue);
        elementHelper.fillLocator(departmentElement, departmentValue);


        By submitElement= By.id("submit");
        elementHelper.clickJSLocator(submitElement);

        //validare de continut
        tableRowsList= driver.findElements(By.xpath("//div[@class='rt-tbody']/div/div[@class='rt-tr -even' or @class='rt-tr -odd']"));
        Assert.assertEquals(tableRowsList.size(), 4, "Valoarea actuala a tabelului nu este 4");
        String expectedRow= tableRowsList.get(3).getText();
        Assert.assertTrue(expectedRow.contains(firstNameValue));
        Assert.assertTrue(expectedRow.contains(lastNameValue));
        Assert.assertTrue(expectedRow.contains(ageValue));
        Assert.assertTrue(expectedRow.contains(userEmailValue));
        Assert.assertTrue(expectedRow.contains(salaryValue));
        Assert.assertTrue(expectedRow.contains(departmentValue));


        //edit functionality
        By editElement= By.id("edit-record-4");
        elementHelper.clickLocator(editElement);

        WebElement editFirstNameElement=driver.findElement(By.id("firstName"));
        String editFirstNameValue="Florentina";
        editFirstNameElement.clear();
        editFirstNameElement.sendKeys(editFirstNameValue);

        WebElement editLastNameElement=driver.findElement(By.id("lastName"));
        String editLastNameValue="Nitu";
        editLastNameElement.clear();
        editLastNameElement.sendKeys(editLastNameValue);

        WebElement editUserEmailElement=driver.findElement(By.id("userEmail"));
        String editUserEmailValue="f.nitu8@gmail.com";
        editUserEmailElement.clear();
        editUserEmailElement.sendKeys(editUserEmailValue);

        WebElement editAgeElement=driver.findElement(By.id("age"));
        String editAgeValue="36";
        editAgeElement.clear();
        editAgeElement.sendKeys(editAgeValue);

        WebElement editSalaryElement=driver.findElement(By.id("salary"));
        String editSalaryValue="8888888888";
        editSalaryElement.clear();
        editSalaryElement.sendKeys(editSalaryValue);

        WebElement editDepartmentElement=driver.findElement(By.id("department"));
        String editDepartmentValue="Automation";
        editDepartmentElement.clear();
        editDepartmentElement.sendKeys(editDepartmentValue);

        By submitEditedElement= By.id("submit");
        elementHelper.clickJSLocator(submitEditedElement);

        //validare de continut
        tableRowsList= driver.findElements(By.xpath("//div[@class='rt-tbody']/div/div[@class='rt-tr -even' or @class='rt-tr -odd']"));
        Assert.assertEquals(tableRowsList.size(), 4, "Valoarea actuala a tabelului nu este 4");
        expectedRow= tableRowsList.get(3).getText();
        Assert.assertTrue(expectedRow.contains(editFirstNameValue));
        Assert.assertTrue(expectedRow.contains(editLastNameValue));
        Assert.assertTrue(expectedRow.contains(editAgeValue));
        Assert.assertTrue(expectedRow.contains(editUserEmailValue));
        Assert.assertTrue(expectedRow.contains(editSalaryValue));
        Assert.assertTrue(expectedRow.contains(editDepartmentValue));

        By deleteElement= By.id("delete-record-4");
        elementHelper.clickLocator(deleteElement);

        //validare de dimensiune
        tableRowsList=driver.findElements(By.xpath("//div[@class='rt-tbody']/div/div[@class='rt-tr -even' or @class='rt-tr -odd']"));
        Assert.assertEquals(tableRowsList.size(),3,"Valoare dimenisune");
    }
}
