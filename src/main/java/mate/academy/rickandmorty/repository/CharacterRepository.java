package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.entity.LocalCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<LocalCharacter, Long> {
}
