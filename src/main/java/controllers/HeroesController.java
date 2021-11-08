package controllers;

import documents.Heroes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import services.HeroesService;

import java.util.Map;

@RestController
@Slf4j //The SLF4J used here to the log creation
public class HeroesController {

    private final HeroesService heroesService;

    //private static final  Logger log = LoggerFactory.getLogger(HeroesController.class);

    public HeroesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping("/heroes")
    public Flux<Heroes> findAll(){
        log.info("Requesting a list of all heroes ");
        return heroesService.findAll();
    }

    @GetMapping("/heroes/{id}")
    public Mono<ResponseEntity<Heroes>> findById(@PathVariable String id){
        log.info("Requesting a single hero using this id {}", id);
        return heroesService.findById(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/heroes")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody  Heroes hero){
        log.info("A new  hero was created on the DB");
        return heroesService.save(hero);
    }

    @PutMapping("/heroes/{id}")
    public Mono<Heroes> updateMovies(@PathVariable String id,
                                   @RequestParam(required = true)String movieName,
                                     @RequestParam(required = true)Integer releaseDate){
        log.info("Trying to updating the hero by adding movie");
        return heroesService.updateMovies(id, movieName,releaseDate);
    }

    @DeleteMapping("/heroes/{id}")
    @ResponseStatus(code = HttpStatus.FOUND)
    public Mono<HttpStatus> deleteById(@PathVariable String id){
        heroesService.deleteByID(id);
        log.info("Deleting hero with the id {}", id);
        return Mono.just(HttpStatus.FOUND);
    }
}
