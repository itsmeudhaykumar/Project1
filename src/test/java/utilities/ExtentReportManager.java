package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String reportName;

    public void onStart(ITestContext testContext){

        String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName= "Test-Report-"+timeStamp+".html";
        extentSparkReporter= new ExtentSparkReporter(".\\reports\\"+reportName);

        extentSparkReporter.config().setDocumentTitle("InsureLiv Project");
        extentSparkReporter.config().setReportName("Regression Report");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extent= new ExtentReports();
        extent.attachReporter(extentSparkReporter);
        extent.setSystemInfo("Application", "InsureLiv");
        extent.setSystemInfo("Reporter", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Module", "Agent");
        extent.setSystemInfo("SubModule", "Login");

        String os= testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser= testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result){
        test= extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName()+ " got successfully executed");
    }

    public void onTestFailure(ITestResult result){
        test= extent.createTest(result.getTestClass().getName());
        test.assignCategory((result.getMethod().getGroups()));

        test.log(Status.FAIL, result.getName()+ " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try{
            String imagePath= new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imagePath);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result){
        test= extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.SKIP, result.getName()+" execution skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext){
        extent.flush();

        String reportPath= System.getProperty("user.dir")+"\\reports\\"+reportName;
        File extentReport= new File(reportPath);
        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
