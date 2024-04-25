package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.entity.LocalCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<LocalCharacter, Long> {
    @Query(value = "SELECT MAX(localcharacter.id) FROM LocalCharacter localcharacter")
    Long getMaxId();

    @Query(value = "SELECT localcharacter "
            + "FROM LocalCharacter localcharacter "
            + "WHERE localcharacter.name LIKE %:name%")
    List<LocalCharacter> findAllWhereNameContains(String name);
}
