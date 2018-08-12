package utilLibrary;

import java.io.IOException;

import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import baseSetUp.BaseSetUp_API;
import baseSetUp.BaseSetUp_API;

public class ActionBeforeTest extends BaseSetUp_API{

	public static ExcelReader TEST_SUITE_ALLURIsResponse = null;
	public static ExcelReader TEST_SUITE_ALLCities = null;
	String TestCaseName = null;
	String mtdName = null;
	String sheetName = null;
	String ToRunColumnNameTestCase = null;
	public ActionBeforeTest() throws IOException {
		super();
		
	}

	
	public void beforeTestAction1(String testCaseName,String shName,String tcDesc) throws InterruptedException
	{
		
		TEST_SUITE_ALLURIsResponse=ExcelRd_Obj_Test_Suite_AllURIs;
		TestCaseName = testCaseName;
		System.out.println("Inside Before Method & TestCaseName Is : "+TestCaseName);
        sheetName = shName;
        ToRunColumnNameTestCase = "CaseToRun";
        //Extnt_logger=report.startTest(TestCaseName, TestCaseName); 
        
        if(!SuiteUtility.checkToRunUtility(TEST_SUITE_ALLURIsResponse, sheetName,ToRunColumnNameTestCase,TestCaseName))
        {
            SuiteUtility.WriteResultUtility(BaseSetUp_API.ExcelRd_Obj_Test_Suite_AllURIs, sheetName,"Pass/Fail/Skip", ++suiteRow_AllURIs, "Skipped");
            Extnt_logger=report.startTest(TestCaseName, TestCaseName);      
            Extnt_logger.log(LogStatus.SKIP, TestCaseName + "is skipped" );
            BaseSetUp_API.report.endTest(BaseSetUp_API.Extnt_logger);
            Thread.sleep(1000);
            BaseSetUp_API.report.flush();
            System.out.println("Extent report is flushed for Firefox");          
            throw new SkipException(TestCaseName+"'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of "+TestCaseName);
        }
        
        else
        {
        	Extnt_logger=report.startTest(TestCaseName, TestCaseName); 
        }
		
	}
	
	public void beforeTestAction(String testCaseName,String shName,String tcDesc) throws InterruptedException
	{
		TestCaseName = testCaseName;
		System.out.println("Inside Before Method & TestCaseName Is : " + TestCaseName);
		Extnt_loggerAllCities = report.startTest(TestCaseName, TestCaseName);
        
		
	}

}
