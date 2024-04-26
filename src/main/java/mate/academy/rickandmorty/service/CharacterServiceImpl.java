package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RemoteToLocalDto;
import mate.academy.rickandmorty.dto.internal.RestClientDto;
import mate.academy.rickandmorty.entity.LocalCharacter;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public List<RestClientDto> findAllWhereNameContains(String namePart) {
        return characterRepository.findAllWhereNameContains(namePart.trim()).stream()
                .map(characterMapper::toRestDto)
                .toList();
    }

    @Override
    public RestClientDto findLocalCharacterByRandomId() {
        Long randomId = new Random().nextLong(characterRepository.getMaxId()) + 1;
        LocalCharacter localCharacter = characterRepository
                .findById(randomId).orElseThrow(() -> new EntityNotFoundException(
                        "Can't find a character by id " + randomId));
        return characterMapper.toRestDto(localCharacter);
    }

    @Override
    public void saveAllLocally(List<RemoteToLocalDto> remoteToLocalDtos) {
        characterRepository.saveAll(remoteToLocalDtos
                .stream()
                .map(characterMapper::toLocalCharacterDto)
                .toList());
    }
}
