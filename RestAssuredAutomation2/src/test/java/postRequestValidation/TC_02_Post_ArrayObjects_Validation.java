package postRequestValidation;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TC_02_Post_ArrayObjects_Validation {
	
	// This test case is designed to post the data into multiple objects in JSON object
	
	@Test	
	public void testPost_Array()
	{
		PostClass_Info infoObj= new PostClass_Info();
		infoObj.setAddress("Test");
		infoObj.setEmail("test@gmail.com");
		infoObj.setNumber("test123");
		
		PostClass_WithArrays postobj= new PostClass_WithArrays();
		postobj.setAuthor("Test Author");
		postobj.setId("Test1234");
		postobj.setTitle("Test Title");
		postobj.setInfoObj(infoObj);
		
		Response res=given().when().contentType(ContentType.JSON).body(postobj)
				.post("http://localhost:3000/posts");
		
		System.out.println("Response is " + res.asString() );
	}

}
