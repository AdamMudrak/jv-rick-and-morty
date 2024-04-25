package mate.academy.rickandmorty.dto.internal;

public record CharacterDto(Long externalId,
        String name,
        String status,
        String gender) {
}
