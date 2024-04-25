package mate.academy.rickandmorty.dto.internal;

public record RestClientDto(Long id,
                            String externalId,
                            String name,
                            String status,
                            String gender) {
}
