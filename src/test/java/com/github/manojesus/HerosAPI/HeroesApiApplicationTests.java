package com.github.manojesus.HerosAPI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import repository.HeroesRepository;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	void getOneHeroById() {

		webTestClient.get().uri("/heroes/{id}",1)
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}
	@Test
	void getOneHeroByIdNotFound() {
		webTestClient.get().uri("/heroes/{id}",2)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);
	}

	@Test
	void deleteById(){
		webTestClient.get().uri("/heroes/{id}",1)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isFound()
				.expectBody(Void.class);
	}

}
