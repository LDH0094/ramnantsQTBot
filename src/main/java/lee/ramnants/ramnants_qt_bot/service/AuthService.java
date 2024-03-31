package lee.ramnants.ramnants_qt_bot.service;

import lee.ramnants.ramnants_qt_bot.constants.EnvConstants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String login() {
        String username = EnvConstants.USERNAME;
        String password = EnvConstants.PASSWORD;
        String loginUrl = EnvConstants.LOGIN_ENDPOINT;

        // Prepare Basic Auth credentials
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity with the headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Make the POST request
        ResponseEntity<String> response = restTemplate.exchange(
                loginUrl,
                HttpMethod.POST,
                requestEntity,
                String.class);

        // Check the response status
        // wrong must be 200
        if (response.getStatusCode() == HttpStatus.CREATED) {
            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.getBody());

            // Extract the access token
            return jsonResponse.getString("accessToken");
        } else {
            // Authentication failed, handle accordingly
            throw new RuntimeException("Authentication failed with status code: " + response.getStatusCodeValue());
        }
    }
}
