package getRequestValidationLatest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.ValidatableResponse;
//test pull request

public class TC_05 {
	
	@Test
	public void validTest()
	{
		given().get("http://services.groupkt.com/state/search/IND?text=delhi").then().statusCode(200).log().all();
	}

}
