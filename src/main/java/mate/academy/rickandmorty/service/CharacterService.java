package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.RemoteToLocalDto;
import mate.academy.rickandmorty.dto.internal.RestClientDto;

public interface CharacterService {
    List<RestClientDto> findAllWhereNameContains(String namePart);

    RestClientDto findLocalCharacterByRandomId();

    void saveAllLocally(List<RemoteToLocalDto> remoteToLocalDtos);
}
