package baseSetUp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import utilLibrary.ExcelReader;

public class BaseSetUp_API {
	public static final Logger logger=Logger.getLogger(Test.class.getName());
	public static ExtentReports report;
	public static ExtentTest Extnt_logger;
	public static ExtentTest Extnt_loggerAllCities;
	//public static ExcelReader ExcelRd_Obj_App_All_URIs=null;
	public static ExcelReader excelReader = null;
	public static Properties prop;
	public static ExcelReader ExcelRd_Obj_Test_Suite_AllURIs=null;
	public static ExcelReader ExcelRd_Obj_Test_Suite_allCities=null;
	public static int suiteRow_AllURIs=0;
	public String XMLtestCaseName=null;
    public String XMLtestCaseID=null;
	 
	 public BaseSetUp_API() throws IOException
	 {
		 try
		 {
			 prop= new Properties();
			 FileInputStream fis= new FileInputStream(System.getProperty("user.dir" +"//src//main//java//config//config.properties"));
			 prop.load(fis);
			 
			 
		 }
		 
		 catch (Exception e)
		 {
			 
		 }	
	 } 
	 
	 public void scrtCtntinitAllCities()
	 {
		 System.out.println("Inside script controller initlization method");
		 String sctCtrlsuitePath= System.getProperty("user.dir")+"\\Test Excel Files\\CitiesData.xlsx";	
		 ExcelRd_Obj_Test_Suite_allCities= new ExcelReader(sctCtrlsuitePath);
	 }
	 
	 /*
	  * 
	  * Method usage: This method will be used to initialize script controller excel file
	  *  
	  */ 	
	/* 
	public void scrtCtntinit()
	 {
		 System.out.println("Inside initlization method of base class");
		 String sctCtrlsuitePath= System.getProperty("user.dir")+"\\Test Excel Files\\Script Controller.xlsx";
		 ExcelRd_Obj_Test_Suite_AllURIs= new ExcelReader(sctCtrlsuitePath);
	 }*/
	
	/*
	 * 
	 * Method usage:To initialize the script controller file
	 *  
	 */
	
	
	public void scrtCtntinit1()
	 {
		 System.out.println("Inside script controller initlization method");
		 String sctCtrlsuitePath= System.getProperty("user.dir")+"\\Test Excel Files\\All_URIs.xlsx";	
		 ExcelRd_Obj_Test_Suite_AllURIs= new ExcelReader(sctCtrlsuitePath);
	 }
	
	
	 
	/*
	 * 
	 * Method usage: This method will be called from Data provider repository class to get end points URIs
	 *  
	 */
	
	public static String [][] getExcelData1(String ExcelName,String SheetName)
	 {
	    	String path= System.getProperty("user.dir")+"\\Test Excel Files\\" + ExcelName;
	    	excelReader=new ExcelReader(path);
	    	String[][] excelData = excelReader.getDatafromExcel(SheetName, ExcelName);
	    	return excelData;
	 }	  
	 
	
	/*
	 * 
	 * Method usage:
	 *  
	 */
	
	
	// @BeforeTest	   
	 public void onBeforeTest(ITestContext testContext)
	    {
	
		 String log4jConfigPath=System.getProperty("user.dir")+"\\"+ "log4j.properties";
	     PropertyConfigurator.configure(log4jConfigPath);          
	        try
	        {	            
	                System.out.println("Inside Before Test class of BASE CLASS: FIREFOX");
	                String filePath=System.getProperty("user.dir")+"\\"+ "TestReportsAllURIsValidation.html";
	                report=new ExtentReports(filePath,true, DisplayOrder.OLDEST_FIRST);       		
	        }
	        catch(Exception e)
	        {
	            System.out.println(e.getMessage());
	        }
	    }
}
