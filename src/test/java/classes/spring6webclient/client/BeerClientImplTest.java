package classes.spring6webclient.client;

import classes.spring6webclient.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient client;

    @Test
    void testDelete(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDTOs()
                .next()
                .flatMap(dto -> client.deleteBeer(dto))
                .doOnSuccess(mt -> atomicBoolean.set(true))
                .subscribe();

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testPatchBeer(){
        final String NAME = "New Name";
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDTOs()
                .next()
                .map(beerDTO -> BeerDTO.builder().beerName(NAME).id(beerDTO.getId()).build())
                .flatMap(dto -> client.patchBeer(dto))
                .subscribe(byIdDTO -> {
                    System.out.println(byIdDTO.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testUpdateBeer(){
        final String NAME = "New Name";
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDTOs()
                .next()
                .doOnNext(beerDTO -> beerDTO.setBeerName(NAME))
                .flatMap(dto -> client.updateBeer(dto))
                .subscribe(byIdDTO -> {
                    System.out.println(byIdDTO.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testCreateBeer(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        BeerDTO newDTO = BeerDTO.builder()
                .price(new BigDecimal("10.99"))
                .beerName("new test beer")
                .upc("456123")
                .beerStyle("IPA")
                .quantityOnHand(42)
                .build();

        client.createBeer(newDTO)
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerByStyle(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.getBeerByBeerStyle("PALE ALE")
                .subscribe(dto -> {
                    System.out.println(dto.toString());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerById(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDTOs()
                .flatMap(dto -> client.getBeerById(dto.getId()))
                .subscribe(byIdDTO -> {
                    System.out.println(byIdDTO.getBeerName());
                    atomicBoolean.set(true);
                });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerDTO(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerDTOs().subscribe(dto -> {
            System.out.println(dto.getBeerName());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void testGetBeerJson(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeersJsonNode().subscribe(jsonNode -> {
            System.out.println(jsonNode.toPrettyString());
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void getMap(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeerMap().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }

    @Test
    void listBeer(){
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        client.listBeers().subscribe(response -> {
            System.out.println(response);
            atomicBoolean.set(true);
        });

        await().untilTrue(atomicBoolean);
    }
}