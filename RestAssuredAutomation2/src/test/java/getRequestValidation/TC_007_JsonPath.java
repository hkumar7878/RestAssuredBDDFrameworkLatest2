package getRequestValidation;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import java.util.List;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.path.json.JsonPath.*;
public class TC_007_JsonPath {

	/*
	 * 
	 * Extract details as string and then further details w/o using json path
	 * 
	 * 
	 */
	
	
	//@Test
	public void testJsonPath()
	{
		// Used to get API response into string
		String responseAsString=
				when().get("http://jsonplaceholder.typicode.com/photos/").
				then().extract().
				asString();
		//System.out.println("Complete resppnse string is " + responseAsString);
		int statusCode=get("http://jsonplaceholder.typicode.com/photos/").statusCode();
		if(statusCode==200)
		{
			List<Integer> albumIDs=from(responseAsString).get("id");
			System.out.println("Status code is " + statusCode);
			System.out.println("Photo album ids are " + albumIDs);
			System.out.println("Size of album is " + albumIDs.size());
			
		}
		
		else
		{
			System.out.println("WebServices are not reachable");
		}
		
	}
	
	@Test
	public void testJsonWithPath()
	{
		String json=when().get("http://services.groupkt.com/country/get/all").
				then().extract().asString();
		when().get("http://services.groupkt.com/country/get/all").then().time(lessThan(5000L));
		//String filter=json.f
		JsonPath jsonPath= new JsonPath(json).setRoot("RestResponse.result");
		List<String> list=jsonPath.get("name");
		System.out.println("Size of json list is " + list.size());
		System.out.println("Values in json list is " + list);
	}
	
}
