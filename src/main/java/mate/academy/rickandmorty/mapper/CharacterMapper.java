package mate.academy.rickandmorty.mapper;

import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.internal.RemoteToLocalDto;
import mate.academy.rickandmorty.dto.internal.RestClientDto;
import mate.academy.rickandmorty.entity.LocalCharacter;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    LocalCharacter toLocalCharacterDto(RemoteToLocalDto remoteToLocalDto);

    RestClientDto toRestDto(LocalCharacter localCharacter);
}
