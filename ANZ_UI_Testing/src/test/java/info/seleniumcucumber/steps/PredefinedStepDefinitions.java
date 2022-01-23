package info.seleniumcucumber.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import jdk.jfr.events.ExceptionThrownEvent;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;


public class PredefinedStepDefinitions {

    WebDriver driver = null;


    @Given("Launch the url as \"([^\"]*)\"$")
    public void launchingUrl(String link) {
        try {
          //  System.setProperty("webdriver.chrome.driver", "C:\\Users\\dayanand.konnur\\chromedriver.exe");
            String localPath="\\src\\test\\resources\\drivers\\";
            String userDir=(System.getProperty("user.dir"));
            System.setProperty("webdriver.chrome.driver", userDir+localPath+"chromedriver.exe");
            driver = new ChromeDriver();
            WebDriverWait wait=new WebDriverWait(driver, 20);
            driver.get(link);
            driver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while loading the browser/details :"+e);

        }

    }


    @When("User enters details as below")
    public void entersDetails(DataTable userTable) throws InterruptedException {

        System.out.println("Enter the Details");

        List < Map < String, String >> user = userTable.asMaps(String.class, String.class);
        for (Map < String, String > form: user) {
            try {

                String application_type_single = form.get("Application type");
                System.out.println("Application type :" + application_type_single);
                driver.findElement(By.xpath("(//input[@id='application_type_single'])/ancestor::label")).click();

                String monthlyLivingExpenses = form.get("Monthly living expenses");
                System.out.println("Monthly living expenses :" + monthlyLivingExpenses);
                driver.findElement(By.xpath("(((//label[text()='Monthly living expenses '])/ancestor::div[1])/div)/input")).sendKeys(monthlyLivingExpenses);

                String Number_of_dependants = form.get("Number of dependants");
                System.out.println("Number of dependants :" + Number_of_dependants);
                driver.findElement(By.xpath("(//select[@title='Number of dependants'])/option[1]")).click();

                String annualIncomeBeforeTax = form.get("Your annual income (before tax)");
                System.out.println("Your annual income (before tax) :" + annualIncomeBeforeTax);
                driver.findElement(By.xpath("(((//label[text()='Your annual income (before tax)'])/ancestor::div[1])/div)/input")).sendKeys(annualIncomeBeforeTax);

                String annualOtherIncome = form.get("Your annual other income (optional)");
                System.out.println("Your annual other income (optional) :" + annualOtherIncome);
                driver.findElement(By.xpath("(((//label[text()='Your annual other income (optional)'])/ancestor::div[1])/div)/input")).sendKeys(annualOtherIncome);

                String CurrentHomeLoanRepayments = form.get("Current home loan monthly repayments");
                System.out.println("Current home loan monthly repayments :" + CurrentHomeLoanRepayments);
                driver.findElement(By.id("homeloans")).sendKeys(CurrentHomeLoanRepayments);

                String OtherLoanRepayments = form.get("Other loan monthly repayments");
                System.out.println("Other loan monthly repayments :" + OtherLoanRepayments);
                driver.findElement(By.xpath("(((//label[text()='Other loan monthly repayments'])/ancestor::div[1])/div)/input")).sendKeys(OtherLoanRepayments);

                String Othercommitments = form.get("Other monthly commitments");
                System.out.println("Other monthly commitments :" + Othercommitments);
                driver.findElement(By.xpath("(//*[@id='otherloans'])[2]")).sendKeys(Othercommitments);

                String TotalCreditCardLimit = form.get("Total credit card limits");
                System.out.println("Total credit card limits :" + TotalCreditCardLimit);
                driver.findElement(By.xpath("(//*[@id='otherloans'])[2]")).sendKeys(TotalCreditCardLimit);
                Thread.sleep(4000);

            } catch (Exception e) {
                System.out.println("Exception :" + e.getMessage());
            }
        }
    }

    @When("click on Work out how much I could borrow button")
    public void btnBorrowCalculater() throws InterruptedException {
        try {
            driver.findElement(By.id("btnBorrowCalculater")).click();
            Thread.sleep(4000);
        } catch (Exception e) {
            System.out.println("Exception : " + e);

        }

    }
    @When("click on Start Over button")
    public void clickOnStartOver() throws Exception {
        try {
            driver.findElement(By.xpath("//button[contains(text(),'Start over')]/span")).click();
            Thread.sleep(6000);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Then("Error message is showing as")
    public void errorMessageDetail(DataTable errorTable) {
        List < Map < String, String >> user = errorTable.asMaps(String.class, String.class);
        for (Map < String, String > Errormsg: user) {
            try {
                String errorMsg = Errormsg.get("ErrorMessage");
                System.out.println("errorMsg :" + errorMsg);
                String actualErrorMessage = driver.findElement(By.xpath("//div[@class='borrow__error__text']")).getText();
                System.out.println("Actual Error Message :" + actualErrorMessage);
                Assert.assertEquals("Error Messages are matching", errorMsg, actualErrorMessage);
            } catch (Exception e) {
                System.out.println("Exception- " + e);
                e.printStackTrace();
            } finally {
                driver.close();
            }

        }
    }


}