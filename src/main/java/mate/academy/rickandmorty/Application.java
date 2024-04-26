package mate.academy.rickandmorty;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterRemoteClient;
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
    private static final String URL_OF_API = "http://localhost:8080/swagger-ui/index.html";
    private static final String SUCCESS_MESSAGE = COLOUR_RED
            + "Local DB updated from server successfully!"
            + System.lineSeparator()
            + "You can now use the app!" + COLOUR_RESET
            + System.lineSeparator()
            + "The API is now available at: " + URL_OF_API;
    private final CharacterRemoteClient characterRemoteClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println(WAIT_MESSAGE);
            characterRemoteClient.fillLocalDbWithCharacters();
            System.out.println(SUCCESS_MESSAGE);
        };
    }
}
