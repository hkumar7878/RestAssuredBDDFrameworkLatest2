package putRequestValidation;

import org.testng.annotations.Test;

import postRequestValidation.PostClass;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TC_01_PutTest {
	
	@Test
	public void testPutRequest()
	{
		PostClass pstObj= new PostClass();
		pstObj.setAuthor("Updated author name");
		pstObj.setId("3");
		pstObj.setTitle("Updated Title");
		
		Response res=given().when().contentType(ContentType.JSON).body(pstObj)
		.put("http://localhost:3000/posts/3");
		
		System.out.println("Updated response is" + res);
		
	}

}
