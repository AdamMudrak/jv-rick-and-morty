package mate.academy.rickandmorty;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.service.CharacterRemoteClient;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private static final String COLOUR_RED = "\u001B[31m";
    private static final String COLOUR_RESET = "\u001B[0m";
    private static final String WAIT_MESSAGE = COLOUR_RED + "Local DB is being updated from server."
            + " Please wait for it to finish..." + COLOUR_RESET;
    private static final String SUCCESS_MESSAGE = COLOUR_RED
            + "Local DB updated from server successfully!" + COLOUR_RESET;
    private final CharacterService characterService;
    private final CharacterRemoteClient characterRemoteClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println(WAIT_MESSAGE);
            for (Map.Entry<Integer, List<CharacterDto>> entry
                    : characterRemoteClient.getCharactersWithPageKeyMap().entrySet()) {
                characterService.saveAllLocally(entry.getValue());
            }
            System.out.println(SUCCESS_MESSAGE);
        };
    }
}
