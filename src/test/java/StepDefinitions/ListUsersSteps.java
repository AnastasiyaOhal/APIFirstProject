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

public class ListUsersSteps {

    private URIBuilder uriBuilder;
    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpResponse response;
    private  int actualResponse;

    @When("I request List User with method get")
    public void i_request_List_User_with_method_get() throws URISyntaxException, IOException {
        httpClient = HttpClientBuilder.create().build();
        uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users").setCustomQuery("page=2");
        httpGet = new HttpGet(uriBuilder.build());
        response = httpClient.execute(httpGet);
        actualResponse=response.getStatusLine().getStatusCode();
    }

    @Then("list user status should be {int}")
    public void status_should_be(Integer statusCode) throws IOException {
        assertEquals((int) statusCode, actualResponse);
    }

    @Then("List of Users is displayed")
    public void list_of_Users_is_displayed() throws IOException {
        String reqBody = reqresUtils.generateStringResource("src/test/java/Utils/JsonFiles/ListUsers.json");

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializeObject = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });

        Map<String, Object> expectedPayload = objectMapper.readValue(reqBody, new TypeReference<Map<String, Object>>() {
        });

        assertEquals(deserializeObject, expectedPayload);
    }
}
