package getRequestValidation;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.path.json.JsonPath.*;
public class TC_011_GetRequestVerification {
	
	
	/**
	 * Status code verification
	 * 
	 */

	//@Test
	public void testStatusInResponse()
	{
		//given().get("http://jsonplaceholder.typicode.com/photos/").
		//then().assertThat().statusCode(200).log().all();
		
		given().get("http://jsonplaceholder.typicode.com/photos/").
		then().assertThat().statusLine("HTTP/1.1 200 OK");
		
		given().get("http://jsonplaceholder.typicode.com/photos/").
		then().assertThat().statusLine(containsString("OK"));
		
	}
	
	/**
	 * 
	 * Headers verification
	 * 
	 */
	// @Test
	public void testHeadersInResponse()
	{
		given().get("http://jsonplaceholder.typicode.com/photos/").
		then().assertThat().header("X-Powered-By", "Express");		
	}
	
	/*
	 * Body text verification
	 * 
	 * 
	 */
	
	@Test
	public void testBodyResponse()
	{
		String responseString= get("http://jsonplaceholder.typicode.com/photos/").asString();
		
	}
}
