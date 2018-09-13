package postRequestAdvanceMultiArrayObjects;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class TC_03_PostMutliArrayObjects {
	
	@Test
	public void testPostRequest()
	{
		// Note: Data for Info object can be fetched from excel sheet as well
		// by using Data Provider
		
		Info info1= new Info();
		info1.setAddress("test1");
		info1.setEmail("test email1");
		info1.setNumber("number1");
		
		System.out.println(info1.getAddress());
		
		
		Info info2= new Info();
		info2.setAddress("test2");
		info2.setEmail("test email2");
		info2.setNumber("number2");
		
		Post post=new Post();
		post.setAuthor("Test Author");
		post.setId("Test id");
		post.setTitle("Test title");
		post.setInfo(new Info[]{info1,info2});
		
		Response res= given().when().contentType(ContentType.JSON).body(post).post("URL");
		
		System.out.println("Response is" + res.asString());
		
	}

}
