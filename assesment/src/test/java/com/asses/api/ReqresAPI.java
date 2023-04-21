package com.asses.api;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class ReqresAPI {

	private static final String BaseURL = "https://reqres.in/api/users";
	Response response;
	RequestSpecification request = RestAssured.given();
	SoftAssert sAssert = new SoftAssert();

	@Test(priority = 1, description = "Get user by Id")
	public void getUserById() {
		String URI = BaseURL + "/2";
		response = request.get(URI);
		response.prettyPrint();
		sAssert.assertEquals(response.getStatusCode(), 200, "Get status code not matched");
		JSONObject outputresponse = new JSONObject(response.asString());
		sAssert.assertTrue((outputresponse.getJSONObject("data").get("id")).equals(2),
				"expected vs actual Id not matched");
		sAssert.assertTrue((outputresponse.getJSONObject("data").getString("email")).equals("janet.weaver@reqres.in"),
				"expected vs actual email not matched");
		sAssert.assertTrue((outputresponse.getJSONObject("data").getString("first_name")).equals("Janet"),
				"expected vs actual firstname not matched ");
		sAssert.assertTrue((outputresponse.getJSONObject("data").getString("last_name")).equals("Weaver"),
				"expected vs actual lastname not matched");
		sAssert.assertTrue((outputresponse.getJSONObject("data").getString("avatar"))
				.equals("https://reqres.in/img/faces/2-image.jpg"), "expected vs actual avatar link not matched");
		sAssert.assertTrue(
				(outputresponse.getJSONObject("support").getString("url")).equals("https://reqres.in/#support-heading"),
				"expected vs actual url not matched");
		sAssert.assertTrue(
				(outputresponse.getJSONObject("support").getString("text"))
						.contains("To keep ReqRes free, contributions towards server costs are appreciated!"),
				"expected vs actual text not matched");
		sAssert.assertAll();
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
		response.prettyPrint();
		sAssert.assertEquals(response.getStatusCode(), 201, "Post status code not matched");
		JSONObject outputresponse = new JSONObject(response.asString());
		sAssert.assertTrue((outputresponse.getString("name")).contains("morpheus"),
				"expected vs actual name not matched");
		sAssert.assertTrue((outputresponse.getString("job")).contains("leader"), "expected vs actual job not matched");
		sAssert.assertTrue((outputresponse.getString("id")) != null, "id not updated");
		sAssert.assertTrue((outputresponse.getString("createdAt")) != null, "created at not updated");
		sAssert.assertAll();
	}

}
