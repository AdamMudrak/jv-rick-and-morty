package mate.academy.rickandmorty.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RestClientDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService service;

    @GetMapping("/random")
    public RestClientDto getRandomCharacter() {
        return service.findLocalCharacterByRandomId();
    }

    @GetMapping("by-name-part")
    public List<RestClientDto> getCharacters(String partOfName) {
        return service.findAllWhereNameContains(partOfName);
    }
}
