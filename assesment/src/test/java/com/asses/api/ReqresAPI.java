package com.asses.api;
import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class ReqresAPI {

	private static final String BaseURL = "https://reqres.in/api/users";
	Response response;
	RequestSpecification request = RestAssured.given(); 
	
	@Test(priority = 1, description = "Get user by Id")
	public void getUserById() {
		String URI = BaseURL + "/2";
		response = request.get(URI);
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 200,"Get status code not matched");
		JSONObject outputresponse = new JSONObject(response.asString());
		Assert.assertTrue((outputresponse.getJSONObject("data").getString("email")).contains("janet.weaver@reqres.in"));
	}
	
	@Test(priority = 2, description = "Create User")
	public void createUser() {
		String URI = BaseURL;
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put("Content-type", "application/json");
		JSONObject payload = new JSONObject();
		payload.put("name", "morpheus");
		payload.put("job", "leader");
		request.body(payload.toString());
		response = request.headers(header).post(URI);
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 201,"Post status code not matched");
		JSONObject outputresponse = new JSONObject(response.asString());
		Assert.assertTrue((outputresponse.getString("name")).contains("morpheus"));
		Assert.assertTrue((outputresponse.getString("job")).contains("leader"));
		
	}
	
}
