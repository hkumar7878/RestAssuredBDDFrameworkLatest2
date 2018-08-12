package getRequestValidation;


import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetData {
	
	@Test
	public void testResponseCode()
	{
		//Response responseObj=get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		//int statusCode=responseObj.getStatusCode();
		int statusCode=get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").getStatusCode();
		System.out.println("Status code is " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	public void testResponseBody()
	{
		Response responseObj=get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		String dataPart=responseObj.asString();
		System.out.println("Response body is " + dataPart);
		//Assert.assertEquals(statusCode, 200);
	}

}
