package patchRequestValidation;
import postRequestValidation.PostClass;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TC_01_PatchRequest {
	
	/*Difference between Patch and Put Request
	 * 
	 * Both perform the similar operation as both update the data in DB
	 * But in case of put request , it is mandatory to send data for all the fields/parameters/columns
	 * but if we only want to update only one field and keep the other fields as blank 
	 * then in that case , the already existing fields (which was kept blank in put request)
	 *  will be saved with null value
	 * So, in order to not to update blank fields as null then we can patch command where
	 * only one field will be update and rest of existing fields keep the same existing data.Patch requires 
	 * that only send the data (or column value) which the user wants to update hence not impacting 
	 * the other fields.
	 * 
	 * 
	 * 
	 * 
	 * 
*/	
	
	
	@Test
	public void testPatchReq()
	{
		Response res=given()
		.body("{ \"title\":\"dummytitle\"}").when().contentType(ContentType.JSON)
		.patch("http://localhost:3000/posts/3");
	}

}
