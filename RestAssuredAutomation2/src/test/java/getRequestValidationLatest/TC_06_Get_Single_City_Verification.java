package getRequestValidationLatest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
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
	
	@Test
	public void testBodyVerification()
	{
		given().get("http://services.groupkt.com/state/search/IND?text=chandigarh").
		then().body("result.name", equalTo("Chandigarh"));
		
	}

}
