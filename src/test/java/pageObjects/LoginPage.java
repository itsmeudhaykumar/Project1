package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input#mat-input-0")
    WebElement txtUsername;
    @FindBy(css = "input#mat-input-1")
    WebElement txtPassword;
    @FindBy(css = "button.app-button")
    WebElement btnLogin;

    public void setTxtUsername(String username){
        txtUsername.clear();
        txtUsername.sendKeys(username);
    }

    public void setTxtPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickLogin(){
        btnLogin.click();

        //sol2
        //btnLogin.submit();

        //sol3
        //Actions act= new Actions(driver);
        //act.moveToElement(btnLogin).click().build().perform();

        //sol4
        //JavascriptExecutor jse= (JavascriptExecutor) driver;
        //jse.executeScript("arguments[0].click();", btnLogin);

        //sol5
        //btnLogin.sendKeys(Keys.RETURN);

        //sol6
        //WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
    }

}
