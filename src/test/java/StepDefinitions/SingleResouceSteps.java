package StepDefinitions;

import Utils.reqresUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SingleResouceSteps {

    private URIBuilder uriBuilder;
    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpResponse response;
    private int actualResponse;

    @When("I request Single Resource with method get")
    public void i_request_Single_Resource_with_method_get() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();
        uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/unknown/2");
        httpGet = new HttpGet(uriBuilder.build());
        response = httpClient.execute(httpGet);
        actualResponse = response.getStatusLine().getStatusCode();
    }

    @Then("single resource status should be {int}")
    public void status_should_be(Integer statusCode) throws IOException {
        assertEquals((int) statusCode, actualResponse);
    }

    @Then("Single Resource is displayed")
    public void single_Resource_is_displayed() throws IOException {
        String reqBody = reqresUtils.generateStringResource("src/test/java/Utils/JsonFiles/SingleResource.json");

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializeObject = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });

        Map<String, Object> expectedPayload = objectMapper.readValue(reqBody, new TypeReference<Map<String, Object>>() {
        });

        assertEquals(deserializeObject, expectedPayload);
    }
}
