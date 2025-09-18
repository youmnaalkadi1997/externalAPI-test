package org.example.externalapitest.controller;


import org.example.externalapitest.model.Character;
import org.example.externalapitest.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/character")
public class CharacterController {

    private CharacterService characterService;
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public List<Character> getCharacters() {
        return characterService.getCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable int id) {
        return characterService.getCharacterById(id);
    }

    @GetMapping("/status")
    public List<Character> getCharactersByStatus(@RequestParam String status) {
        return characterService.getCharactersByStatus(status);
    }
}
