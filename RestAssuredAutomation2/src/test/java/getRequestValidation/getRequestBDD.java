package getRequestValidation;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;                                 

public class getRequestBDD {
	
	
	/*
	 * To verify the status code
	 */
	@Test
	public void testStatusCode()
	{
		
		
		try
		{
		given().get("http://jsonplaceholder.typicode.com/posts/2").
		then().assertThat().statusCode(201);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//@Test
	public void testLogging()
	{
		given().get("http://jsonplaceholder.typicode.com/posts/2").
		then().statusCode(200).log().all();
	}
	
	/*
	 * To verify single content using org.hamcrest.matches lib's equal to method
	 */

	
	//@Test
	public void testEqualsToFunction()
	{
		try
		{
			given().get("http://services.groupkt.com/country/get/iso2code/IN").
			then().body("RestResponse.result.name", equalTo("India"));
		}
		catch(Exception e)
		{
			e.getMessage();
			System.out.println("Error");
		}
	}
	
	//@Test
	public void testHasItems()
	{
		given().get("http://services.groupkt.com/country/get/all").
		then().body("RestResponse.result.name", hasItems("India","Australia"));
	}
	
	//@Test
	public void testHeader()
	{
		given().param("Key1","value1").header("key2","value2").when().get("http://services.groupkt.com/country/get/iso2code/CN").then().statusCode(200).and().
		body("Response.result.alpha3_code",equalTo("CHN"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
