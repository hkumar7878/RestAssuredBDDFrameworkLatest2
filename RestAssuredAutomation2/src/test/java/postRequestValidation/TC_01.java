package postRequestValidation;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TC_01 {
	
	//@Test	
	public void TC_Get()
	{
		Response weaReprt= given().param("id", "2172797")
				.param("appid", "b6907d289e10d714a6e88b30761fae22").when()
				.get("https://samples.openweathermap.org/data/2.5/weather");
		
		System.out.println("Respones is " + weaReprt.asString());
	}
	
	
	// -----------------First method to post the request----------------------------------
	
	// Fetch the data with the help of dataprovider and verify the get response from data
	// data provider data...Excel data sheet should also contain the edge cases as well.
	@Test	
	public void postRequestEx1()
	{
		
		// Step 1: Post the contents
		
		Response res= given().body(" {\"id\":\"2\", "
				+ " \"title\":\"dummytitle\"," 
				+ " \"author\":\"Hitesh\" } ")
				.when()
				.contentType(ContentType.JSON)
				.post("http://localhost:3000/posts");
		
		System.out.println("Posted code is " + res.asString());
		if(res.statusCode()==200)
		{
			System.out.println("Data posted successfully");
		}
		
		// Step 2: Read the above posted contents on the basis of ID 2
		
		Response res1=given().when().get("http://localhost:3000/posts/2");
		
		String actWeaRprt=res1.then().contentType(ContentType.JSON)
				.extract().path("id[1]");
				
	}
	
	// ------------------------Second method to post the request by using class object-----------------------
	// Automate set parameters to fetch data from data providers.
	@Test
	public void postRequestEx2()
	{
		PostClass pstObj= new PostClass();
		pstObj.setAuthor("Poonam");
		pstObj.setId("3");
		pstObj.setTitle("Selenium");
		
		Response res= given().when().contentType(ContentType.JSON)
				.body(pstObj).post("http://localhost:3000/posts");
				//.body(new PostClass()).post("http://localhost:3000/posts");
		System.out.println("Posted request is" + res.asString());
	}

}
