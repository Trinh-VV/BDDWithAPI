package stepsdefinition.GetCanadianUser;

import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import common.JsonUtils;
import common.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GettingCanadianUserValidationSteps {
	String url;
	String method;
	HttpResponse<String> response;

	@Given("User has url and method")
	public void user_has_url_and_method() {
		url = "https://randomuser.me/api?nat=CA";
		method = "GET";
	}

	@When("User send a request")
	public void user_send_a_request() {
		//response = Utils.sendRequestApi(url);
	}

	@Then("Response is returned correctly")
	public void response_is_returned_correctly() {
		assertEquals(response.statusCode(), 200);
		String responseBody = response.body();
		
		JSONObject jObject = new JSONObject(responseBody);
		JSONArray results = jObject.getJSONArray("results");
		int count = 0;
		for (Object result : results) {
			Object actualLocation = JsonUtils.getValueByJsonKey(result, "location");
			String actualCountry = JsonUtils.getValueByJsonKey(actualLocation, "country").toString();
			if (actualCountry.equalsIgnoreCase("Canada")) {
				count++;
			}
		}
		assertEquals(count, results.length());
	}
}
