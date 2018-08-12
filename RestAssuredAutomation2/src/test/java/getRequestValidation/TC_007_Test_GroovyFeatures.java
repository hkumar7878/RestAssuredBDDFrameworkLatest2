package getRequestValidation;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.path.json.JsonPath.*;

import java.util.List;

public class TC_007_Test_GroovyFeatures {

	/*****
	 * 
	 * To get all attributes as list
	 * 
	 * 
	 */

	@Test
	public void testGetResponseAsList() {
		String response = get("http://services.groupkt.com/country/search?text=lands").asString();
		int statusCode = get("http://services.groupkt.com/country/search?text=lands").statusCode();
		List<String> ls = from(response).getList("RestResponse.result.name");
		System.out.println("List size is " + ls.size());
		if (statusCode == 200) {
			for (String country : ls) {
				if (country.equals("Solomon Islands")) {
					System.out.println("Found my place");
				}
			}
		}
	}
	//@Test
	public void testConditiononList() {
		String response = get("http://services.groupkt.com/country/search?text=lands").asString();
		int statusCode = get("http://services.groupkt.com/country/search?text=lands").statusCode();
		List<String> ls = from(response).getList("RestResponse.result.findAll {it.name.length() >40}.name");
		System.out.println("List size is " + ls);
		
	}
}
