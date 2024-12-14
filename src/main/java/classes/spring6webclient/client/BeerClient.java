package classes.spring6webclient.client;

import classes.spring6webclient.model.BeerDTO;
import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface BeerClient {

    Flux<String> listBeers();

    Flux<Map> listBeerMap();

    Flux<JsonNode> listBeersJsonNode();

    Flux<BeerDTO> listBeerDTOs();

    Mono<BeerDTO> getBeerById(String id);

    Flux<BeerDTO> getBeerByBeerStyle(String beerStyle);

    Mono<BeerDTO> createBeer(BeerDTO beerDTO);

    Mono<BeerDTO> updateBeer(BeerDTO beerDTO);

    Mono<BeerDTO> patchBeer(BeerDTO beerDTO);

    Mono<Void> deleteBeer(BeerDTO beerDTO);
}
