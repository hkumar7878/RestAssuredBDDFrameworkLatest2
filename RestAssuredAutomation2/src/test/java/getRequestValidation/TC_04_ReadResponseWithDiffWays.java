package getRequestValidation;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class TC_04_ReadResponseWithDiffWays {
	
	//@Test
	public void testExtractpathinoneLine()
	{
		String href=get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
		System.out.println("href path is " + href );
		when().get(href).then().statusCode(200);
	}
	
	
	/*
	 * Extract response to validation
	 */
	
	@Test
	public void testExtractResponse()
	{
		Response response=when().get("http://jsonplaceholder.typicode.com/photos/1")
				.then().extract().response();
		System.out.println("Response content type is " + response.contentType());
		System.out.println("Href is " + response.path("url"));
		System.out.println("Status code is " + response.statusCode());
	}
}
