package getRequestValidation;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
public class Test2_SettingRoot {
	
	/*
	 * 
	 * Basic way to test all the parameters
	 * 
	 */
	
	@Test
	public void testWithoutRoot()
	{
		given().get("http://services.groupkt.com/country/get/iso3code/ITA").
		then().statusCode(200).
		body("RestResponse.result.name",equalTo("Italy")).
		body("RestResponse.result.alpha2_code",is("IT")).
		body("RestResponse.result.alpha3_code", is("ITA"));
		
		int statusCode=get("http://services.groupkt.com/country/get/iso3code/ITA").getStatusCode();
		System.out.println("Status code is " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
	}
	
	/*
	 * Recommended way to test the parameters using root feature
	 */
	
	//@Test
	public void testWithRoots()
	{
		given().get("http://services.groupkt.com/country/get/iso3code/ITA").
		then().
		root("RestResponse.result").
		body("name",is("Italy")).
		body("alpha2_code",is("IT")).
		body("alpha3_code",is("ITA"));
	}

}
