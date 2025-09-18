package org.example.externalapitest.service;


import org.example.externalapitest.model.Character;
import org.example.externalapitest.model.MultiCharacters;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class CharacterService {

    private final RestClient restClient;

    public CharacterService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://rickandmortyapi.com/api")
                .build();
    }

    public List<Character> getCharacters() {
       List <Character> allCharacters =
               restClient.get()
                       .uri("/character")
                       .retrieve()
                       .body(MultiCharacters.class)
                       .results();
       return allCharacters;
    }


    public Character getCharacterById(int id) {
        Character character = restClient.get()
                .uri("/character/{id}" , id)
                .retrieve()
                .body(Character.class);
        return character;
    }

    public List<Character> getCharactersByStatus(String status) {
        List<Character> characterList = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/character")
                        .queryParam("status" , status)
                        .build())
                .retrieve()
                .body(MultiCharacters.class)
                .results();
        return characterList;
    }
}
