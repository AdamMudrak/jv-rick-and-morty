package mate.academy.rickandmorty.dto.external;

import mate.academy.rickandmorty.dto.internal.CharacterDto;

public record CharacterResponseDataDto(
        CharacterInfoDto info, CharacterDto[] results) {
}
