package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.LoginOtpPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_LoginValidationTest extends BaseClass {

    @Test(dataProvider = "LoginValidation", dataProviderClass = DataProviders.class, groups={"validation","master","dataDriven"})
    public void verifyLoginTestwithDDT(String username, String password, String response){
        try {
            logger.info("Testcase Execution Started TC002_AgentLoginTest");
            SoftAssert softAssert= new SoftAssert();
            LoginPage loginPage= new LoginPage( driver);
            logger.info("Pass username and password");
            loginPage.setTxtUsername(username);
            loginPage.setTxtPassword(password);
            logger.info("Click Login button");
            loginPage.clickLogin();
            LoginOtpPage loginOtpPage= new LoginOtpPage(driver);
            logger.info("Verify OTP Page Header is Displayed");
            boolean pageHeaderDisplayed= loginOtpPage.isPageHeaderDisplayed();

            if(response.equalsIgnoreCase("valid")){
                if(pageHeaderDisplayed){
                    driver.get(prop.getProperty("agentUrl"));
                    Assert.assertTrue(true);
                }else{
                    Assert.fail();
                }
            }
            if(response.equalsIgnoreCase("invalid")){
                if(pageHeaderDisplayed){
                    driver.get(prop.getProperty("agentUrl"));
                    Assert.fail();
                }else{
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("Test execution completed TC002_LoginValidationTest");
    }
}
