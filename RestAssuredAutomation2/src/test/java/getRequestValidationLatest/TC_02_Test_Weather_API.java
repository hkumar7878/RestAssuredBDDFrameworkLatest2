package getRequestValidationLatest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class TC_02_Test_Weather_API {
	
	@Test
	public void testWeatherAPI()
	{
		RestAssured.baseURI="https://samples.openweathermap.org/data/2.5/weather";
		String response = RestAssured.given().param("q", "London,uk").param("appid", "b6907d289e10d714a6e88b30761fae22").
		when().get().then().extract().asString();
		System.out.println("Response is " + response);
		
		JsonPath path=new JsonPath(response);
		Object countryNm = path.get("sys.country");
		System.out.println("Country name is " + countryNm);
		
		Reporter.log("Response is " + response,true);
	
		ValidatableResponse res = RestAssured.given().param("q", "London,uk").param("appid", "b6907d289e10d714a6e88b30761fae22").
		when().get().then();
		
		res.statusCode(200);
		
		Reporter.log("Verified the response code successfully ",true);
		
		
		res.body("sys.country", Matchers.notNullValue());
		res.body("sys.country", Matchers.equalToIgnoringCase("GB"));
		
		Object countryName = res.extract().response().path("sys.country");
		
		System.out.println("Country name is " + countryName);
		
		
		
		Reporter.log("Country code has verified successfully ",true);
		
		res.body("name", Matchers.notNullValue());
		res.body("name", Matchers.equalToIgnoringCase("London"));
		Reporter.log("City name has verified successfully ",true);
				
	}
	
	//@Test
	public void getWeatherInfoOfLondonWithInvalidData()
	{
		RestAssured.baseURI="https://samples.openweathermap.org/data/2.5/weather?zip=940401,us";
		ValidatableResponse res = RestAssured.given().param("q", "London,uk").param("appid", "2340761fae22").
				when().get().then();
		
		Reporter.log("Response is : " + res.extract().asString(),true);
		res.statusCode(200);
		res.body("message", Matchers.notNullValue());
		res.body("message", Matchers.equalToIgnoringCase("city not found"));
		Reporter.log("Verified the error message for incorrect city code",true);
	}

	
	//@Test
	public void getWeatherInfoOfLondonWithInvalidKey()
	{
		RestAssured.baseURI="https://samples.openweathermap.org/data/2.5/weather";
		ValidatableResponse res = RestAssured.given().param("q", "London,uk").param("appid", "").
				when().get().then();
		
		Reporter.log("Response is : " + res.extract().asString(),true);
		res.statusCode(200);
		Reporter.log("Verified the response code successfully ",true);
		res.body("message", Matchers.notNullValue());
		res.body("message", Matchers.containsString("Invalid API key"));
		Reporter.log("Verified the error message for invalid key",true);
		
		//Assert.assertEquals(true, false);
	}

}
