package br.ufrn.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenDataService {

    private final Logger logger = LoggerFactory.getLogger(OpenDataService.class);

    private final RestTemplate restTemplate;

    public OpenDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void callApi(String url) throws ApiCallException {
        try {
            ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST,null, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                logger.info("Error calling {} {}", response.getStatusCode(), url);
                throw new ApiCallException("Error calling " + url);
            }

            logger.info("Successful call to {}",  url);
        } catch (Exception e) {
            logger.info("Error calling {}", url, e);
            throw new ApiCallException("Error calling " + url, e);
        }
    }
}
