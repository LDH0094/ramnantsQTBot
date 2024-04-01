package lee.ramnants.ramnants_qt_bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lee.ramnants.ramnants_qt_bot.constants.EnvConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QTPostService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public QTPostService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void postQT(String accessToken, String content) {
        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request body using Jackson ObjectMapper
        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("content", content);
        requestBodyMap.put("imgUrls", null);
        requestBodyMap.put("threadScope", "GENERAL");

        try {
            String requestBody = objectMapper.writeValueAsString(requestBodyMap);

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
        } catch (JsonProcessingException e) {
            System.out.println("Error converting request body to JSON: " + e.getMessage());
        }
    }
}
