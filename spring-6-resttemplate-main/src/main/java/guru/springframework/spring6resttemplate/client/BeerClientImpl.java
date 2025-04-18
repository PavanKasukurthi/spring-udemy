package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {
    private final RestTemplateBuilder restTemplateBuilder;

    private static final String BASE_URL = "http://localhost:8080";
    private static final String GET_BEER_PATH = "/api/v1/beer";
    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<PageImpl> stringResponse =
                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, PageImpl.class);

//        ResponseEntity<Map> mapResponse =
//                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, Map.class);
//
//        ResponseEntity<JsonNode> jsonResponse =
//                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, JsonNode.class);
//
//        jsonResponse.getBody().findPath("_embedded").elements()
//            .forEachRemaining(node -> {
//                System.out.println(node.get("beerName").asText());
//            });
//
//        System.out.println(stringResponse.getBody());
        return null;
    }
}
