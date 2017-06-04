package pl.com.bottega.microecom.userclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class UserClient {

    private static final String DEFULT_URL = "http://localhost:8181";

    private String serviceUrl;
    private RestTemplate restTemplate;

    public UserClient(String url, RestTemplate restTemplate) {
        this.serviceUrl = url;
        this.restTemplate = restTemplate;
    }

    public UserClient(RestTemplate restTemplate) {
        this(DEFULT_URL, restTemplate);
    }

    public UserInfo getUser(String authToken) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("X-Auth-Token", authToken);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestHeaders);
        return restTemplate.exchange(serviceUrl + "/users/current", HttpMethod.GET, requestEntity, UserInfo.class).getBody();
    }

}
