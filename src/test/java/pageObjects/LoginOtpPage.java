package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.internal.EclipseInterface;

public class LoginOtpPage extends BasePage{

    public LoginOtpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[text()='OTP sent successfully']")
    WebElement msgOtpSentSuccessfully;

    @FindBy(xpath= "//span[text()='Enter OTP Code']")
    WebElement pageHeader;


    public String getMessageOtpSentSuccessfully(){
        try{
           return msgOtpSentSuccessfully.getText();
        }catch(Exception e){
            return e.getMessage();
        }
    }

    public boolean isPageHeaderDisplayed(){
        try{
            return pageHeader.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}
