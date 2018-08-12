package getRequestValidationLatest;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseSetUp.BaseSetUp_API;
import utilLibrary.ActionAfterTest;
import utilLibrary.ActionBeforeTest;
import utilLibrary.DataProviderRepository;
import utilLibrary.ExcelReader;
import utilLibrary.SuiteUtility;

public class TC_001_All_EndPoint_URI_Validation extends BaseSetUp_API{
	String tcDesc=null;
	String TestCaseName=null;
	String methodName=null;
	public static ExcelReader TEST_SUITE_AllURIsResponse = null;
	String SheetName=null;
	String testCasesSheetName=null;
	ActionBeforeTest actionBeforeTest=new ActionBeforeTest();
	ActionAfterTest actionAfterTest=new ActionAfterTest();
	String methodName1;
	static int i=0;
	
	public TC_001_All_EndPoint_URI_Validation() throws IOException {
		super();
	
	}
	
	@BeforeMethod()
	public void getMethodName(Method method) throws InterruptedException
	{
		 tcDesc="Verify response for all end points urls";
		 scrtCtntinit1();
		 TEST_SUITE_AllURIsResponse=ExcelRd_Obj_Test_Suite_AllURIs;
		 methodName1=TEST_SUITE_AllURIsResponse.getCellData("FullURI", 0, ++i);
		 SheetName = "AllURIResponseValidation";
		 testCasesSheetName="FullURI";	     
	}
	
	//@Test(dataProvider="allURIsData")
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="allURIsData")
	public void TC_01_TestAllURIs(String resourName,String a,String appURI,String b,String c) throws InterruptedException
	{
		// Below line of code is called for skipping the tests
		actionBeforeTest.beforeTestAction1(methodName1, testCasesSheetName, "CaseToRun");	
		given().get(appURI).
		then().assertThat().statusCode(200);
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	// Below beforeMethod is working fine
	//@BeforeMethod()
	/*public void getMethodName(Method method) throws InterruptedException
	{
		 tcDesc="Verify response for all end points urls";
		 scrtCtntinit1();
		 TEST_SUITE_AllURIsResponse=ExcelRd_Obj_Test_Suite_AllURIs;
		 TestCaseName = this.getClass().getSimpleName();
		 System.out.println("Inside Before Method & TestCaseName Is : "+TestCaseName);
		 methodName=method.getName();
		 SheetName = "AllURIResponseValidation";
		 testCasesSheetName="Full URI";
	     String ToRunColumnNameTestCase = "CaseToRun";
	     actionBeforeTest.beforeTestAction(TestCaseName, methodName, SheetName, ToRunColumnNameTestCase);
	     
	}*/
	
	/*@DataProvider(name="allURIsData")
    public String[][] getTestData()
    {
    	System.out.println("Inside data provider");
		String[][] uRIs = getExcelData1("URI_Response Validation.xlsx", "Full URI");
    	return uRIs;
    }*/
}
