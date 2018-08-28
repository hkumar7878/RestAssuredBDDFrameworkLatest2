package getRequestValidationLatest;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC_06_Get_Single_City_Verification {
	
	//@Test	
	public void testSingleCity_GetRequest()
	{
		
		// If static import is not done then we cannot use get() method
		
		Response res = get("http://services.groupkt.com/state/search/IND?text=chandigarh");
		
		System.out.println("Status code is " + res.getStatusCode());
		
		assertEquals(200,res.getStatusCode());
		
		String json=res.asString();
		System.out.println(json);
		JsonPath jp= new JsonPath(json);
		jp.setRoot("result");
		System.out.println("Name is " + jp.get("name"));
		assertEquals("Chandigarh",jp.get("name"));
		
	}
	
	//@Test
	public void testSiteIsUp()
	{
		given().when().get("http://services.groupkt.com/state/search/IND?text=chandigarh")
		.then().statusCode(200);
	}
	
	//@Test
	public void testBodyVerification()
	{
		//given().when().get("http://services.groupkt.com/state/search/IND?text=chandigarh").
		//then().body("RestResponse.result.country", equalTo("IND"));
		
		given().param("text","chandigarh").then().expect().statusCode(200);
		
	}
	
	//@Test
	public void test4()
	{
		Response res = get("http://services.groupkt.com/state/search/IND?text=chandigarh");
		int resCode=res.getStatusCode();
		System.out.println("Full response code is" + res);
		System.out.println("Status code is " + resCode);
		assertEquals(res.statusCode(), 200);
	}
	
//	@Test
	public void testWithParameters()
	{
		Response res=given().param("text", "chandigarh").
				 when().get("http://services.groupkt.com/state/search/IND");
				
		if(res.getStatusCode()==200)	
			System.out.println("API is working fine");		
		else
			System.out.println("API is not working fine");
		
	}
	
	//@Test
	public void testWithAssert()
	{
		given().param("text", "chandigarh").
		 when().get("http://services.groupkt.com/state/search/IND").then().assertThat().statusCode(200);
	}
	
	// Checking all the contents of the Response
	
	//@Test
	public void checkResponseContents()
	{
		Response res=given().param("text", "chandigarh").
				 when().get("http://services.groupkt.com/state/search/IND");
		System.out.println(res.asString());
	}
	
	@Test
	public void testWeather()
	{
		
		String expWeaRprt="scattered clouds";
		
		// First of all get the response string use the following sites
	
		// http://jsonviewer.stack.hu/
	
		
		Response weaReprt= given().param("id", "2172797")
				.param("appid", "b6907d289e10d714a6e88b30761fae22").when()
				.get("https://samples.openweathermap.org/data/2.5/weather");
		
		// Now get the path from the following site
		// https://jsonpath.curiousconcept.com/
		
		String actWeaRprt=weaReprt.then().contentType(ContentType.JSON)
				.extract().path("weather[0].description");
		
		System.out.println("Weather Report is " + actWeaRprt);
		
		if(actWeaRprt.equalsIgnoreCase(expWeaRprt))
			System.out.println("Weather report is matching");
		else
			System.out.println("Weather report is not matching");
	}
	
	

}
