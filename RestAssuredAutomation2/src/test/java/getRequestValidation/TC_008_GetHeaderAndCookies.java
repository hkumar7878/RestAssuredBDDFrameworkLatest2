package getRequestValidation;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.path.json.JsonPath.*;

public class TC_008_GetHeaderAndCookies {
	
	
	//@Test
	public void testRestHeaders()
	{
		Response response=get("http://jsonplaceholder.typicode.com/photos/");
		
		// to get single header
		String headerCFRAY=response.getHeader("CF-RAY");
		System.out.println(">>>>>>>> header is " + headerCFRAY);
		System.out.println("");
		Headers headers=response.getHeaders();
		for(Header h : headers)
		{
			System.out.println(h.getName()+ "" + h.getValue());
		}
	}
	
	/*
	 * To get and test cookies
	 */
	
	//@Test
	public void getAndTestCookies()
	{
		Response response=get("http://jsonplaceholder.typicode.com/photos/");
		Map<String,String> cookies=response.getCookies();
		for(Map.Entry<String, String> entry : cookies.entrySet())
		{
			System.out.println(entry.getKey() + ":" +entry.getValue());
		}
	}
	
	/*
	 * To get details about the cookies
	 * 
	 * 
	 */
	
	@Test
	public void testDetailCookies()
	{
		Response response=get("http://jsonplaceholder.typicode.com/photos/");
		Cookie a = response.getDetailedCookie("__cfduid");
		System.out.println("Detailed : " + a.hasExpiryDate());
		System.out.println("Detailed : " + a.getExpiryDate());
		System.out.println("Detailed : " + a.hasValue());
		
	}
}
