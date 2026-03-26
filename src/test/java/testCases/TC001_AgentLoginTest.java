package testCases;
import org.apache.logging.log4j.Level;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.LoginOtpPage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC001_AgentLoginTest extends BaseClass {

  @Test(groups = {"regression", "master"})
    public void verify_Agent_Can_Login() throws InterruptedException {
      SoftAssert softAssert= new SoftAssert();
      try{
          logger.info("Testcase Execution Started TC001_AgentLoginTest");
          LoginPage loginPage= new LoginPage( driver);
          logger.info("Pass username and password");
          loginPage.setTxtUsername(prop.getProperty("username"));
          loginPage.setTxtPassword(prop.getProperty("password"));
          logger.info("Click Login button");
          loginPage.clickLogin();
          LoginOtpPage loginOtpPage= new LoginOtpPage(driver);
          logger.info("Verify OTP Sent Successfully message");
          softAssert.assertEquals(loginOtpPage.getMessageOtpSentSuccessfully(), "OTP sent successfully", "OTP Message Mismatch");
          //Assert.assertEquals(loginOtpPage.getMessageOtpSentSuccessfully(), "OTP sent successfully");
      }catch (Exception e){
          logger.error("Test Failed");
          logger.debug("Debug Logs");
      }
        logger.info("TestCase Execution Completed");
        softAssert.assertAll();
    }
}
