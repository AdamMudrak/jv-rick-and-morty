package mate.academy.rickandmorty.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.entity.LocalCharacter;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<CharacterDto> findLocalCharacters(Pageable pageable) {
        return characterRepository.findAll(pageable).stream()
                .map(characterMapper::toCharacterDto)
                .toList();
    }

    @Override
    public CharacterDto findLocalCharacterById(Long id) {
        LocalCharacter localCharacter = characterRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Some message"));
        return characterMapper.toCharacterDto(localCharacter);
    }

    @Override
    public void saveAllLocally(List<CharacterDto> characterDtos) {
        characterRepository.saveAll(characterDtos
                .stream()
                .map(characterMapper::toCharacter)
                .toList());
    }
}
