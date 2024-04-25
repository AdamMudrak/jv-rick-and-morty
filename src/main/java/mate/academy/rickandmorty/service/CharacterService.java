package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.RestClientDto;
import mate.academy.rickandmorty.dto.internal.RemoteToLocalDto;

public interface CharacterService {
    List<RestClientDto> findAllWhereNameContains(String partOfName);

    RestClientDto findLocalCharacterByRandomId();

    void saveAllLocally(List<RemoteToLocalDto> remoteToLocalDtos);
}
