package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders{

    @DataProvider (name="LoginValidation")
    public String[][] getData() throws IOException {
        String filePath= ".\\testData\\loginCredentials.xlsx";
        ExcelUtility excelUtils= new ExcelUtility(filePath);
        int rowNum= excelUtils.getRowCount("Sheet1");
        int colNum= excelUtils.getCellCount("Sheet1", 1);
        String loginData[][]= new String[rowNum][colNum];

        for(int i=1; i<=rowNum; i++){
            for(int j=0; j<colNum; j++){
                loginData[i-1][j]= excelUtils.getCellData("Sheet1", i, j);
            }
        }
        return loginData;
    }

}
