package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = Constants.RICK_AND_MORTY,
        description = Constants.RICK_AND_MORTY_DESCRIPTION)
public class CharacterController {
    private final CharacterService service;

    @Operation(summary = Constants.GET_RANDOM_CHARACTER_SUMMARY,
            description = Constants.GET_RANDOM_CHARACTER_DESCRIPTION)
    @ApiResponse(responseCode = Constants.CODE_200, description = Constants.SUCCESSFULLY_RETRIEVED)
    @GetMapping("/random")
    public RestClientDto getRandomCharacter() {
        return service.findLocalCharacterByRandomId();
    }

    @Operation(summary = Constants.GET_CHARACTER_BY_NAME_PART_SUMMARY,
            description = Constants.GET_CHARACTER_BY_NAME_PART_DESCRIPTION)
    @ApiResponse(responseCode = Constants.CODE_200, description = Constants.SUCCESSFULLY_RETRIEVED)
    @GetMapping("by-name-part")
    public List<RestClientDto> getCharactersByNamePart(
            @Parameter(example = Constants.NAME_EXAMPLE) String namePart) {
        return service.findAllWhereNameContains(namePart);
    }
}
