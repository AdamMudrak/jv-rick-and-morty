package mate.academy.rickandmorty.dto.external;

import mate.academy.rickandmorty.dto.internal.RemoteToLocalDto;

public record CharacterResponseDataDto(
        CharacterInfoDto info, RemoteToLocalDto[] results) {
}
