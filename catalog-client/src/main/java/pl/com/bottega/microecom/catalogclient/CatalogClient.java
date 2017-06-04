package pl.com.bottega.microecom.catalogclient;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class CatalogClient {

    private static final String DEFULT_URL = "http://localhost:8080";

    private String serviceUrl;
    private RestTemplate restTemplate;

    public CatalogClient(String url, RestTemplate restTemplate) {
        this.serviceUrl = url;
        this.restTemplate = restTemplate;
    }

    public CatalogClient(RestTemplate restTemplate) {
        this(DEFULT_URL, restTemplate);
    }

    public ProductInfo getProduct(Long id) {
        return restTemplate.getForObject(serviceUrl + "/products/" + id, ProductInfo.class);
    }

}
