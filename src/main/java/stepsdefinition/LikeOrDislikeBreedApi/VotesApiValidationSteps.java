package stepsdefinition.LikeOrDislikeBreedApi;

import static org.testng.Assert.assertEquals;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import common.JsonUtils;
import common.RequestUtils;
import common.Resource;
import common.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class VotesApiValidationSteps {
	Pair<Integer, String> response = new Pair<Integer, String>(0, "");
	String url, method, requestBodyName, requestBody;
	Dictionary<String, String> headers =  new Hashtable();

	@Given("Send Api request with data")
	public void send_Api_request_with_data(DataTable votesData) {
		List<Map<String, String>> rows = votesData.asMaps(String.class, String.class);
		url = rows.get(0).get("URL");
		method = rows.get(0).get("Method");

		String apiKey = rows.get(0).get("ApiKey");
		String apiValue = rows.get(0).get("ApiValue");
		headers.put("Content-Type", "text/plain");
		headers.put("charset","UTF-8");
		headers.put(apiKey, apiValue);
		requestBodyName = rows.get(0).get("RequestBodyName");
		String requestBodyPath = Utils.getConfigValueByKey("Config.properties", Resource.LikeOrDislikeBreedApiPath.toString()) + requestBodyName;
		
		requestBody = JsonUtils.readJsonFile(requestBodyPath);
		System.out.println("requestBodyrequestBodyrequestBody"+requestBody);
	}

	@When("Send API")
	public void send_api() {
		response = RequestUtils.sendRequest(url, method, headers, requestBody);
		System.out.println("statuscode: ===="+response.getValue0());
		System.out.println("mess: ===="+response.getValue1());
	}

	@Then("Response returns {int} and {string}")
	public void response_returns_and_success(int expectedStatusCode, String expectedMessage) {
		assertEquals(response.getValue0(), expectedStatusCode);
		assertEquals(response.getValue1(), expectedMessage);
	}

	@Then("SubId is Exactly")
	public void sub_id_is_exactly() {

	}

}
