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

public class TC_009_Test_Connect_Get_And_Query_Parameters {
	
	
	/*
	 * Generally connect used with https request
	 */
	
	//@Test
	public void testConnectRequest()
	{
		when().request("CONNECT" ,"https://api.fonts.com/rest/json/Accounts/").
		then().statusCode(400);
	}
	/***
	 * 
	 * In get request, query parameters can be passed
	 * 
	 */
	
	//@Test
	public void testFromQueryParameters()
	{
		given().
			queryParam("A","Value1").
			queryParam("B","Value2").
			when().
			get("https://api.fonts.com/rest/json/Accounts/").
			then().statusCode(400);
	}
	@Test
	public void testpostQueryParameters()
	{
		given().
		queryParam("A","Value1").
		queryParam("B","Value2").
			when().
			post("https://api.fonts.com/rest/json/Accounts/").
			then().statusCode(400);
	}
	
	
	/*
	 * 
	 * Recommended way
	 * if request is get then param will be treated as query parameter
	 * if request is response then param will be treated as Form parameter
	 * 
	 */
	
	//@Test
	public void testpostQueryanotherwayParameters()
	{
		given().
		param("A","Value1").
		param("B","Value2").
			when().
			post("https://api.fonts.com/rest/json/Accounts/").
			then().statusCode(400);
	}
	/*
	 * To set multiple value parameters
	 * We can pass list, multiple values or no values in param
	 * 
	 * 
	 * 
	 */
	
	@Test
	public void testSetMultiValParameters()
	{
		List<String> list= new ArrayList<String>();
		list.add("one");
		list.add("two");
		given().
		param("A","Value1","Value2").
		param("B","Value2").
		param("C",list).
			when().
			post("https://api.fonts.com/rest/json/Accounts/").
			then().statusCode(400);
	}
}
