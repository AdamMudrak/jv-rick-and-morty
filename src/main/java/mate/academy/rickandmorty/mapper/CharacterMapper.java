package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.entity.LocalCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toCharacterDto(LocalCharacter localCharacter);

    LocalCharacter toCharacter(CharacterDto characterDto);

    List<CharacterDto> toCharacterDtos(List<LocalCharacter> localCharacters);

    List<LocalCharacter> toCharacters(List<CharacterDto> charactersDto);
}
