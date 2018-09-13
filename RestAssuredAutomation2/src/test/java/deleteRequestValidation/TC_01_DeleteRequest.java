package deleteRequestValidation;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TC_01_DeleteRequest {
	
	@Test
	public void deleteRequest()
	{
		Response res=given().when().delete("http://localhost:3000/posts/3");
		System.out.println("After deletion " + res.asString());
	}
	

}
