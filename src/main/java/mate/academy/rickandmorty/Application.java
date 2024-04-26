package mate.academy.rickandmorty;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.RemoteToLocalDto;
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
            + "Local DB updated from server successfully!"
            + System.lineSeparator()
            + "You can now use the app!" + COLOUR_RESET;
    private static final String HEADLESS_PROPERTY = "java.awt.headless";
    private static final String HEADLESS_PROPERTY_VALUE = "false";
    private static final String URL_OF_API = "http://localhost:8080/swagger-ui/index.html";
    private final CharacterService characterService;
    private final CharacterRemoteClient characterRemoteClient;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return (args) -> {
            fetchDataFromServer();
            //openApiInBrowser();
        };
    }

    private void fetchDataFromServer() {
        System.out.println(WAIT_MESSAGE);
        for (Map.Entry<Integer, List<RemoteToLocalDto>> entry
                : characterRemoteClient.getCharactersWithPageKeyMap().entrySet()) {
            characterService.saveAllLocally(entry.getValue());
        }
        System.out.println(SUCCESS_MESSAGE);
    }

    /**
     * This code should be run only on local machine
     * as build fails on GitHub because there is
     * no browser to be opened
     */
    private void openApiInBrowser() {
        System.setProperty(HEADLESS_PROPERTY, HEADLESS_PROPERTY_VALUE);
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(URL_OF_API));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
