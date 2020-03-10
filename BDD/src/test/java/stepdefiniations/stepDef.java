package stepdefiniations;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;

import resources.APIresources;
import resources.TestBuild;
import resources.utils;

public class stepDef extends utils
{
	RequestSpecification res; 
	ResponseSpecification Responsespec;
	Response response;
	static String  place_id;
	TestBuild tb=new TestBuild();
	@Given("AddPlace payload {string} {string} {string}")
	public void addplace_payload(String name, String language, String address) throws IOException
	{ 
res=given().spec(Requestspecification())
		.body(tb.addPlacePayload(name, language, address));
	}

	@When("user calls {string} http {string} request")
	public void user_calls_http_request(String resource, String method) 
	{
		APIresources resourceAPI=APIresources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		if(method.equalsIgnoreCase("post"))
		response=res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("get"))	
		response=res.when().get(resourceAPI.getResource());	
	}

	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer int1)
	{
		assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void response_body_is(String key, String value)
	{
		
		assertEquals(getJsonPath(response,key),value);
	}
	@Then("Verify the PlaceID is maps with {string} using {string}")
	public void verify_the_PlaceID_is_maps_with_using(String ExpectedName, String resource) throws IOException 
	{
	 place_id=getJsonPath(response,"place_id");
		res=given().spec(Requestspecification().queryParam("place_id",place_id));
		user_calls_http_request(resource,"GET");
		String ActualName=getJsonPath(response,"name");
		assertEquals(ActualName,ExpectedName);
		
	}
	@Given("DeletePlace Paylaod")
	public void deleteplace_Paylaod() throws IOException
	{
	
		res=given().spec(Requestspecification().body(tb.deletePayload(place_id))); 
		
	}


}
