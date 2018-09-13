package getRequestValidationLatest;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC_08_BackbaseSampleApp {
	
	@Test
	public void tc_08_TestGetReq()
	
	{
		Response res=given().param("f", "test1").
				 when().get("http://computer-database.herokuapp.com/computers/501");
		System.out.println(res.asString());
	}

}
