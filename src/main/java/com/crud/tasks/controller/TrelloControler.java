package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/trello")
public class TrelloControler {

    @Autowired
    TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        System.out.println(trelloBoards.size());

        trelloBoards.stream()
                .map(tb -> { System.out.println(tb);
                return tb;
                })
                .filter(trelloBoardDto -> trelloBoardDto.getId() != null
                        && trelloBoardDto.getName() !=null && trelloBoardDto.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId()+" "+ trelloBoardDto.getName()));

    }

}
