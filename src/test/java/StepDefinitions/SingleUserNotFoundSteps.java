package StepDefinitions;

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
import static org.junit.Assert.assertTrue;

public class SingleUserNotFoundSteps {

    private URIBuilder uriBuilder;
    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpResponse response;
    private int actualResponse;

    @When("I request Single User Not Found with method get")
    public void i_request_Single_User_Not_Found_with_method_get() throws IOException, URISyntaxException {
        httpClient = HttpClientBuilder.create().build();
        uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users/23");
        httpGet = new HttpGet(uriBuilder.build());
        response = httpClient.execute(httpGet);
        actualResponse = response.getStatusLine().getStatusCode();
    }

    @Then("list user not found status should be {int}")
    public void status_should_be(Integer statusCode) throws IOException {
        assertEquals((int) statusCode, actualResponse);
    }

    @Then("list user body is empty")
    public void list_user_body_is_empty() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> deserializeObject = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });
        assertTrue(deserializeObject.isEmpty());
    }
}
