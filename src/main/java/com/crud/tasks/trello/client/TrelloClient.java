package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {


    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("joannakozlowska3")
    private String trelloUserName;

    @Autowired
    private RestTemplate restTemplate;

    private URI url;

    public List<TrelloBoardDto> getTrelloBoards() {
        url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/"+ trelloUserName+ "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("username", trelloUserName)
                .queryParam("fields", "name,id")
                .build().encode().toUri();
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        Optional.ofNullable(Arrays.asList(boardsResponse)).orElse(new ArrayList<>());

        return new ArrayList<>();
    }
}
