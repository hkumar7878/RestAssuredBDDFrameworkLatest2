package utilLibrary;

import java.io.IOException;

import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import baseSetUp.BaseSetUp_API;

public class ActionAfterTest extends BaseSetUp_API {
	public ActionAfterTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	BaseSetUp_API baseSetUp;
	
public void testCasesSheetUpd(ITestResult result, String SheetName, String methodName) throws InterruptedException
	{
		
			System.out.println("After Method Execution");
    		int status = result.getStatus();
    		int tcRowNum = SuiteUtility.readRowUtility(BaseSetUp_API.ExcelRd_Obj_Test_Suite_allCities, SheetName, "TestCaseID", methodName);
    		switch (status) 
    		{
    			case ITestResult.SUCCESS:  			
    				SuiteUtility.WriteResultUtility_Grid(BaseSetUp_API.ExcelRd_Obj_Test_Suite_allCities, SheetName, "Pass/Fail", tcRowNum, "Passed");
    				Extnt_logger=report.startTest(methodName, methodName);      
    				Extnt_loggerAllCities.log(LogStatus.PASS, methodName + "is passed" );	
    	            BaseSetUp_API.report.endTest(BaseSetUp_API.Extnt_loggerAllCities);
    				BaseSetUp_API.report.flush();
    				System.out.println("After method: Inside success method");
    				break;
    		  	  		
    			case ITestResult.FAILURE:	
    				SuiteUtility.WriteResultUtility_Grid(BaseSetUp_API.ExcelRd_Obj_Test_Suite_allCities, SheetName, "Pass/Fail", tcRowNum, "Failed");
    				Extnt_logger=report.startTest(methodName, methodName);      
    				Extnt_loggerAllCities.log(LogStatus.FAIL, methodName + "is failed" );	
    	            BaseSetUp_API.report.endTest(BaseSetUp_API.Extnt_loggerAllCities);
    				BaseSetUp_API.report.flush();	
    				System.out.println("After method: Inside failure method");
    			
    		}
	
	}
	
	public void testCasesSheetUpd1(ITestResult result, String SheetName, String methodName) throws InterruptedException
	{
		
			System.out.println("After Method Execution");
    		int status = result.getStatus();
    		int tcRowNum = SuiteUtility.readRowUtility(BaseSetUp_API.ExcelRd_Obj_Test_Suite_allCities, SheetName, "TestCaseID", methodName);
    		switch (status) 
    		{
    			case ITestResult.SUCCESS:  			
    				SuiteUtility.WriteResultUtility_Grid(BaseSetUp_API.ExcelRd_Obj_Test_Suite_allCities, SheetName, "Pass/Fail", tcRowNum, "Passed");
    				System.out.println("After method: Inside success method");
    				break;
    		  	  		
    			case ITestResult.FAILURE:	
    				SuiteUtility.WriteResultUtility_Grid(BaseSetUp_API.ExcelRd_Obj_Test_Suite_allCities, SheetName, "Pass/Fail", tcRowNum, "Failed");			
    				System.out.println("After method: Inside failure method");
    			
    		}
    		
    		 if (result.getStatus() == ITestResult.FAILURE) {
    	            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
    	        } else if (result.getStatus() == ITestResult.SKIP) {
    	            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
    	        } else {
    	            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
    	        }
    	        
    	        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());        
    	        ExtentManager.getReporter().flush();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void scrptCtrlUpd(ITestResult result, String SheetName, String methodName) throws InterruptedException
	{
		
			System.out.println("After Method Execution");
    		int status = result.getStatus();
    		int tcRowNum = SuiteUtility.readRowUtility(BaseSetUp_API.ExcelRd_Obj_Test_Suite_AllURIs, SheetName, "TestCaseName", methodName);
    		switch (status) 
    		{
    			case ITestResult.SUCCESS:  			
    				SuiteUtility.WriteResultUtility_Grid(BaseSetUp_API.ExcelRd_Obj_Test_Suite_AllURIs, SheetName, "Pass/Fail/Skip", tcRowNum, "Passed");
    				Extnt_logger.log(LogStatus.PASS, methodName + "is passed" );
    				BaseSetUp_API.report.endTest(baseSetUp.Extnt_logger);   			
    				BaseSetUp_API.report.flush();
    				System.out.println("Extent report is flushed for Firefox");
    				break;
    		  	  		
    			case ITestResult.FAILURE:	
    				SuiteUtility.WriteResultUtility_Grid(BaseSetUp_API.ExcelRd_Obj_Test_Suite_AllURIs, SheetName, "Pass/Fail/Skip", tcRowNum, "Failed");
    				Extnt_logger.log(LogStatus.FAIL, methodName + "is passed" );
    				baseSetUp.report.endTest(baseSetUp.Extnt_logger);
    				Thread.sleep(1000);
    				baseSetUp.report.flush();		
    				System.out.println("Extent report is flushed for Chorme");
    			
    		}
	
	}*/
}
