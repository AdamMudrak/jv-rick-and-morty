package mate.academy.rickandmorty.dto.external;

import java.util.Map;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public record CharacterResponseDataDto(
        CharacterMetaDataDto meta, Map<String, CharacterDto> characters) {
}
