package mate.academy.rickandmorty.service;

import java.awt.print.Pageable;
import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    List<CharacterDto> findLocalCharacters(Pageable pageable);

    CharacterDto findCharacterById(int id);

    void saveAllLocally(List<CharacterDto> characterDtos);
}
