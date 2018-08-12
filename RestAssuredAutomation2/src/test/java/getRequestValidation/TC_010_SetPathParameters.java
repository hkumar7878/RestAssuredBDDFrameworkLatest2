package getRequestValidation;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.path.json.JsonPath.*;

public class TC_010_SetPathParameters {
	
	@Test
	public void testSetPathParameters2()
	{
		given().
				pathParam("type","json").
				pathParam("section","Domains").
				when().
				post("http://api.fonts.com/rest/{type}/{section}/").
				then().statusCode(400);
	}

}
