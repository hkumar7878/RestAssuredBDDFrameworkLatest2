package utilLibrary;

public class SuiteUtility_API {
	
	public static String reteriveTestCaseID(ExcelReader xls, String sheetName, String testCaseID, String testSuite){

        String testCaseName=null; 
        testCaseName=xls.retrieveTestCaseName(sheetName, testCaseID, testSuite);
        return testCaseID;
    
    }

}
