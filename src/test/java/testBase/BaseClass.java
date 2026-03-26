package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger;     //Log4j
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class BaseClass {

    public static WebDriver driver;
    Random myRandom= new Random();
    public Logger logger;
    public Properties prop;

    @BeforeClass(groups = {"regression", "master", "validation"})
    @Parameters({"os", "browser"})
    public void setup(String os, String browser) throws IOException {

        logger= LogManager.getLogger(this.getClass());
        //Loading config.properties file
        FileReader file= new FileReader("./src//test//resources//config.properties");
        prop= new Properties();
        prop.load(file);
        switch (browser.toLowerCase()){
            case "chrome": driver= new ChromeDriver(); break;
            case "edge": driver= new EdgeDriver(); break;
            case "firefox": driver= new FirefoxDriver(); break;
            default: System.out.println("Invalid browser"); return;
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(prop.getProperty("agentUrl"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"master", "sanity", "validation"})
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        return RandomStringUtils.random(10, 48, 91, true, false, null, myRandom);
    }

    public String randomNumber(){
        return RandomStringUtils.random(10, 48, 91, false, true, null, myRandom);
    }

    public String randomAlphaNumeric(){
        return RandomStringUtils.random(10, 48, 91, true, true, null, myRandom);
    }

    public String captureScreen(String testName) throws IOException {
        String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
        File srcFile= takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+testName+"_"+timeStamp+".png";
        File destFile= new File(targetFilePath);

        //srcFile.renameTo(destFile);
        FileUtils.copyFile(srcFile, destFile);
        return targetFilePath;
    }
}
