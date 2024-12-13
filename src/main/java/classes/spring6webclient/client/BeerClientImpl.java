package classes.spring6webclient.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class BeerClientImpl implements BeerClient {

    private final WebClient webClient;
    public static final String BEER_PATH = "/api/v3/beer";

    public BeerClientImpl(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @Override
    public Flux<Map> listBeerMap(){
        return webClient.get().uri(BEER_PATH, Map.class).retrieve().bodyToFlux(Map.class);
    }

    @Override
    public Flux<String> listBeers() {
        return webClient.get().uri(BEER_PATH, String.class)
                .retrieve().bodyToFlux(String.class);
    }
}
