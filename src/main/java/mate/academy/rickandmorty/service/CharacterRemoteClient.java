package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CharacterRemoteClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=%d";
    private static final Integer DEFAULT_PAGE = 1;
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private final ObjectMapper mapper;
    private final Map<Integer, List<CharacterDto>> charactersWithPageKeyMap = new HashMap<>();

    public Map<Integer, List<CharacterDto>> getCharactersWithPageKeyMap() {
        int page = DEFAULT_PAGE;
        HttpRequest httpRequest = formHttpRequest(page);
        HttpResponse<String> response = formHttpResponse(httpRequest);
        CharacterResponseDataDto dataDto = formListFromResponse(response);
        List<CharacterDto> charactersDto = Arrays.stream(dataDto.results()).toList();
        charactersWithPageKeyMap.put(page, charactersDto);
        while (dataDto.info().next() != null) {
            httpRequest = formHttpRequest(++page);
            response = formHttpResponse(httpRequest);
            dataDto = formListFromResponse(response);
            charactersWithPageKeyMap.put(page, charactersDto);
        }
        return charactersWithPageKeyMap;
    }

    private HttpRequest formHttpRequest(int page) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL.formatted(page)))
                .build();
    }

    private HttpResponse<String> formHttpResponse(HttpRequest httpRequest) {
        try {
            return HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private CharacterResponseDataDto formListFromResponse(HttpResponse<String> response) {
        try {
            return mapper.readValue(response.body(), CharacterResponseDataDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
