package getRequestValidation;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class Test3_PostRequest {
	
	@Test
	public void testPostRequest()
	{
		given().
		headers("AppKey","Key-Value").
		param("FirstName","Hitesh").
		param("LastName","Ghai").
		param("Age","37").
		when().
		post("http://api.fonts.com/rest/json/Accounts/").
		then().
		statusCode(401).log().all();
	}

}
