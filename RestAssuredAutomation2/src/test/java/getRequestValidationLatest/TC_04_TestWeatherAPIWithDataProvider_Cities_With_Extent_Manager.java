package getRequestValidationLatest;


import java.io.IOException;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import baseSetUp.BaseSetUp_API;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import utilLibrary.ActionAfterTest;
import utilLibrary.ActionBeforeTest;
import utilLibrary.DataProviderRepository;
import utilLibrary.ExcelReader;

import utilLibrary.ExtentTestManager;

public class TC_04_TestWeatherAPIWithDataProvider_Cities_With_Extent_Manager extends BaseSetUp_API{
	
	public TC_04_TestWeatherAPIWithDataProvider_Cities_With_Extent_Manager() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

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
	
	@BeforeMethod()
	public void getMethodName(Method method) throws InterruptedException
	{
		 scrtCtntinitAllCities();
		 TEST_SUITE_All_Cities=ExcelRd_Obj_Test_Suite_allCities;
		 methodName1=TEST_SUITE_All_Cities.getCellData("PositiveTestCasesData", 0, ++i);
		 SheetName = "PositiveTestCasesData";
		 testCasesSheetName="PositiveTestCasesData";
		 ExtentTestManager.startTest(methodName1);
	}
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="allCitiesData")
	//@Test(dataProvider="allCitiesData")
	public void TestWeatherAPIWithDataProvider(String tcName,String cityNm,String appID,String statusCode,String countryCode,String status) throws InterruptedException
	{
		
		RestAssured.baseURI="http://services.groupkt.com/state/search/IND";
		//http://services.groupkt.com/state/search/IND?text=chandigarh
		String [] sp1=statusCode.split("\\.");
		ValidatableResponse response = RestAssured.given().param("text", cityNm).when().get().then();
		Reporter.log("Test case name is " + tcName, true);
		ExtentTestManager.getTest().log(LogStatus.INFO, "Test Case Name is " +tcName );
		ExtentTestManager.getTest().log(LogStatus.INFO, "Reponse is" + response.extract().asString());
		int statusCode1=Integer.parseInt(sp1[0]);
		response.statusCode(statusCode1);
		Reporter.log("Verified the status code successfully" + statusCode, true);	
		ExtentTestManager.getTest().log(LogStatus.INFO, "Status code is " +statusCode1 );
	}
	
	@AfterMethod
    public void afterEachTest(ITestResult result) throws InterruptedException {
        try
        {

            System.out.println("Inside After Method of test case");
            actionAfterTest.testCasesSheetUpd1(result, testCasesSheetName, methodName1);
        }

        catch (Exception e)
        {
            System.out.println("Excpetion is " + e.getMessage());
        }
	}

}
