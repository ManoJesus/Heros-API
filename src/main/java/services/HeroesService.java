package services;

import documents.Heroes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.HeroesRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class HeroesService {

    private final HeroesRepository heroesRepository;

    public HeroesService(HeroesRepository heroesRepository) {
        this.heroesRepository = heroesRepository;
    }


    public Flux<Heroes> findAll(){
        return Flux.fromIterable(heroesRepository.findAll());
    }
    public Mono<Heroes> findById(String id){
        Optional<Heroes> optionalHero = heroesRepository.findById(id);
        if(optionalHero.isPresent()){
            return Mono.justOrEmpty(heroesRepository.findById(id));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This hero does not  exists");
        }
    }
    public Mono<Heroes> save(Heroes hero){
        Optional<Heroes> optionalHero = heroesRepository.findById(hero.getId());
        if(!optionalHero.isPresent()){
            return Mono.justOrEmpty(this.heroesRepository.save(hero));
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT,"This hero already  exists");
        }

    }
    public Mono<Boolean> deleteByID(String id) {
        heroesRepository.deleteById(id);
        return Mono.just(true);
    }

    @Transactional
    public Mono<Heroes> updateMovies(String id, String movieName,
                                    Integer releaseDate) {
        Heroes hero = heroesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hero does not exists"));
        boolean isValid = true;
        for(Map.Entry<String,Integer> test : hero.getMovie_year().entrySet()){
                if(test.getKey().equals(movieName) && test.getValue().equals(releaseDate)){
                    isValid = false;
                }
        }
        if(isValid){
            hero.getMovie_year().put(movieName, releaseDate);
            return Mono.justOrEmpty(hero);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The movie you tried to add already exists for the hero "+hero.getName());
        }
    }
}
