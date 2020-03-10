package stepdefiniations;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks 
{
@Before("@deletePlace")
public void beforeScenario() throws IOException
{
	if(stepDef.place_id==null) 
	{
	stepDef sd= new stepDef();
	sd.addplace_payload("viji","tamil","mangalore");
	sd.user_calls_http_request("AddPlaceAPI", "post");
	sd.verify_the_PlaceID_is_maps_with_using("viji","GetPlaceAPI");
	}
}
}
