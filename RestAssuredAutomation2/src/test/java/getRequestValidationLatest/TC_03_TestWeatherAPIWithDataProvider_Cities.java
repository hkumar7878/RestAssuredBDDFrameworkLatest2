package getRequestValidationLatest;

import java.lang.*;
import java.util.*;
import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseSetUp.BaseSetUp_API;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import utilLibrary.ActionAfterTest;
import utilLibrary.ActionBeforeTest;
import utilLibrary.DataProviderRepository;
import utilLibrary.ExcelReader;

public class TC_03_TestWeatherAPIWithDataProvider_Cities extends BaseSetUp_API{
	
	String tcDesc=null;
	String TestCaseName=null;
	String methodName=null;
	public static ExcelReader TEST_SUITE_AllURIsResponse = null;
	public static ExcelReader TEST_SUITE_All_Cities = null;
	String SheetName=null;
	String testCasesSheetName=null;
	ActionBeforeTest actionBeforeTest=new ActionBeforeTest();
	ActionAfterTest actionAfterTest=new ActionAfterTest();
	String methodName1;
	static int i=0;
	
	public TC_03_TestWeatherAPIWithDataProvider_Cities() throws IOException {
		super();
	}
	
	@BeforeMethod()
	public void getMethodName(Method method) throws InterruptedException
	{
		 tcDesc="Verify response for all end points urls";
		 scrtCtntinitAllCities();
		 TEST_SUITE_All_Cities=ExcelRd_Obj_Test_Suite_allCities;
		 methodName1=TEST_SUITE_All_Cities.getCellData("PositiveTestCasesData", 0, ++i);
		 SheetName = "PositiveTestCasesData";
		 testCasesSheetName="PositiveTestCasesData";	     
	}
	
	
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="allCitiesData")
	//@Test(dataProvider="allCitiesData")
	public void TestWeatherAPIWithDataProvider(String tcName,String cityNm,String appID,String statusCode,String countryCode,String status) throws InterruptedException
	{
		actionBeforeTest.beforeTestAction(methodName1, testCasesSheetName, "Verify all cities respose");
		RestAssured.baseURI="http://services.groupkt.com/state/search/IND";
		String [] sp1=statusCode.split("\\.");
		ValidatableResponse response = RestAssured.given().param("text", cityNm).when().get().then();
		Reporter.log("Test case name is " + tcName, true);
		int statusCode1=Integer.parseInt(sp1[0]);
		response.statusCode(statusCode1);
		Reporter.log("Verified the status code successfully" + statusCode, true);			
	}
	
	
	@DataProvider(name="allCitiesData")
    public String[][] getTestData()
    {
    	System.out.println("Inside data provider");
    	String[][] uRIs = getExcelData1("CitiesData.xlsx", "PositiveTestCasesData");
    	return uRIs;
    }
	
	
	@AfterMethod
    public void afterEachTest(ITestResult result) throws InterruptedException {
        try
        {

            System.out.println("Inside After Method of test case");
            actionAfterTest.testCasesSheetUpd(result, testCasesSheetName, methodName1);
        }

        catch (Exception e)
        {
            System.out.println("Excpetion is " + e.getMessage());
        }
	}

}
