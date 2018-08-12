package getRequestValidation;
import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;   
public class StandardCodeLines {
	
	/**
	 * 
	 * 
	 * 
	 * Below are the various standard codes and functions in
	 * RestAssured API's
	 * 
	 */
	
	@Test
	public void variousRestAPIMethods()
	{
		//******----------------------------------- GET REQUEST -------------------------------------------------------------****************
		//  1 : To Get Status Code of Get Request 
		
		int statusCode= get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").getStatusCode();
		
		// 2 :  To Get the whole response body of while requesting
		
		Response response = get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		String responsebodyData=response.asString();
		
		
		// 3 : To assert/Verify the status code and log all the details of response
		String str=null;
		given().get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").
		then().statusCode(200).assertThat().log().all();
		
		given().get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").
		then().assertThat().statusCode(200);
		
		
		// 4 : To verify single content using org.hamcrest.matches lib's 'equalTo' method
		
		given().get("").
		then().body("RestResponse.result.name", equalTo("India"));
		
		// 5 : To verify multiple contents using org.hamcrest.matches lib's 'hasItems' method
				
		given().get("").
		then().body("RestResponse.result.name", hasItems("India","Australia"));
		
		// 6 : To test headers in response
		
		given().param("Key1","value1").header("key2","value2").when().get("http://services.groupkt.com/country/get/iso2code/CN").then().statusCode(200).and().
		body("Response.result.alpha3_code",equalTo("CHN"));
		
		
		// 7 : To Test all response body parameters
		
		given().get("http://services.groupkt.com/country/get/iso3code/ITA").
		then().statusCode(200).
		body("RestResponse.result.name",equalTo("Italy")).
		body("RestResponse.result.alpha2_code",is("IT")).
		body("RestResponse.result.alpha3_code", is("ITA"));
		
		int statusCode1=get("http://services.groupkt.com/country/get/iso3code/ITA").getStatusCode();
		
		
		// 8 : To test body parameters by setting the root
		
		given().get("").
		then().
		root("RestResponse.result").
		body("name",is("Italy")).
		body("alpha2_code",is("IT")).
		body("alpha3_code",is("ITA"));
		
		// 9 : Differnt way to get the response
		
		Response responseq=when().get("http://jsonplaceholder.typicode.com/photos/1")
		.then().extract().response();
		
		// 10 : 
		
		String responseAsString=
				when().get("http://jsonplaceholder.typicode.com/photos/").
				then().extract().
				asString();
		//System.out.println("Complete resppnse string is " + responseAsString);
		int statusCode2=get("http://jsonplaceholder.typicode.com/photos/").statusCode();
		if(statusCode==200)
		{
			List<Integer> albumIDs=from(responseAsString).get("id");
			System.out.println("Status code is " + statusCode);
			System.out.println("Photo album ids are " + albumIDs);
			System.out.println("Size of album is " + albumIDs.size());
			
		}
		
		// 11 :  With json Path
		

		String json=when().get("http://services.groupkt.com/country/get/all").
				then().extract().asString();
		when().get("http://services.groupkt.com/country/get/all").then().time(lessThan(5000L));
		//String filter=json.f
		JsonPath jsonPath= new JsonPath(json).setRoot("RestResponse.result");
		List<String> list=jsonPath.get("name");
		System.out.println("Size of json list is " + list.size());
		System.out.println("Values in json list is " + list);
		
		// 12 : To test without groovy feature
		
		String response3 = get("http://services.groupkt.com/country/search?text=lands").asString();
		int statusCode3 = get("http://services.groupkt.com/country/search?text=lands").statusCode();
		List<String> ls = from(response3).getList("RestResponse.result.name");
		System.out.println("List size is " + ls.size());
		if (statusCode == 200) {
			for (String country : ls) {
				if (country.equals("Solomon Islands")) {
					System.out.println("Found my place");
				}
			}
		}
		
		// 13: To test response with groovy features
		
		String response4 = get("http://services.groupkt.com/country/search?text=lands").asString();
		int statusCode4 = get("http://services.groupkt.com/country/search?text=lands").statusCode();
		List<String> ls1 = from(response4).getList("RestResponse.result.findAll {it.name.length() >40}.name");
		System.out.println("List size is " + ls);
		
		// 14: To test response headers
		
		Response response5=get("http://jsonplaceholder.typicode.com/photos/");
		
		// to get single header
		String headerCFRAY=response.getHeader("CF-RAY");
		System.out.println(">>>>>>>> header is " + headerCFRAY);
		System.out.println("");
		Headers headers=response5.getHeaders();
		for(Header h : headers)
		{
			System.out.println(h.getName()+ "" + h.getValue());
		}
		
		// 15 : To test cookies
		
		Response response6=get("http://jsonplaceholder.typicode.com/photos/");
		Cookie a = response.getDetailedCookie("__cfduid");
		System.out.println("Detailed : " + a.hasExpiryDate());
		System.out.println("Detailed : " + a.getExpiryDate());
		System.out.println("Detailed : " + a.hasValue());
		
		
		// 16 : 
		
		when().request("CONNECT" ,"https://api.fonts.com/rest/json/Accounts/").
		then().statusCode(400);
		
		// 17 : 
		

		given().
		queryParam("A","Value1").
		queryParam("B","Value2").
		when().
		get("https://api.fonts.com/rest/json/Accounts/").
		then().statusCode(400);
		
		// 18 : 
		
		given().
		pathParam("type","json").
		pathParam("section","Domains").
		when().
		post("http://api.fonts.com/rest/{type}/{section}/").
		then().statusCode(400);
		
		// 19 :
		
		given().get("http://jsonplaceholder.typicode.com/photos/").
		then().assertThat().statusLine("HTTP/1.1 200 OK");
		
		given().get("http://jsonplaceholder.typicode.com/photos/").
		then().assertThat().statusLine(containsString("OK"));
		
		// 20 : 
		
		given().get("http://jsonplaceholder.typicode.com/photos/").
		then().assertThat().header("X-Powered-By", "Express");	
		
		
		// 21 : 
		
		
		/*given().get("https://jsonplaceholder.typicode.com/users/1").then().
		//body("username" , response1 -> endsWith("et"));
		
	
		//******----------------------------------- POST REQUEST -------------------------------------------------------------****************
		// 1 : To test the post request 
		given().
		headers("AppKey","Key-Value").
		param("FirstName","Hitesh").
		param("LastName","Ghai").
		param("Age","37").
		when().
		post("http://api.fonts.com/rest/json/Accounts/").
		then().
		statusCode(401).log().all();*/
		
		
		// 2 : 
		
		
		given().
		queryParam("A","Value1").
		queryParam("B","Value2").
		when().
		post("https://api.fonts.com/rest/json/Accounts/").
		then().statusCode(400);
		
		
		// 3 :
		
		given().
		param("A","Value1").
		param("B","Value2").
		when().
		post("https://api.fonts.com/rest/json/Accounts/").
		then().statusCode(400);
		
		
		// 4: 
		
		List<String> list6= new ArrayList<String>();
		list.add("one");
		list.add("two");
		given().
		param("A","Value1","Value2").
		param("B","Value2").
		param("C",list).
		when().
		post("https://api.fonts.com/rest/json/Accounts/").
		then().statusCode(400);
		 
	
	}

}
