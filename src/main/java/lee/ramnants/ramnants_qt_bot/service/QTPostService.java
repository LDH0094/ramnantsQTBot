package lee.ramnants.ramnants_qt_bot.service;

import lee.ramnants.ramnants_qt_bot.constants.EnvConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QTPostService {

    private final RestTemplate restTemplate;

    @Autowired
    public QTPostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void postQT(String accessToken, String content) {
        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request body
        String requestBody = "{\"content\": \"" + content + "\", \"imgUrls\": null, \"threadScope\": \"GENERAL\"}";

        // Create the request entity with the headers and body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make the POST request
        ResponseEntity<String> response = restTemplate.exchange(
                EnvConstants.POST_ENDPOINT,
                HttpMethod.POST,
                requestEntity,
                String.class);

        // Check the response status
        if (response.getStatusCode() == HttpStatus.CREATED) {
            // Post successful
            System.out.println("QT successfully posted.");
        } else {
            // Post failed
            System.out.println("Failed to post QT. Status code: " + response.getStatusCodeValue());
        }
    }
}
