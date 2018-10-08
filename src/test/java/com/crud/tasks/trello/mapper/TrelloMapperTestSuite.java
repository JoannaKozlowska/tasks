package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    private static int testCounter = 0;

    @Autowired
    TrelloMapper trelloMapper;

    @Before
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
        System.out.println();
    }

    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "first List", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "second List", false);
        List<TrelloListDto> newList = new ArrayList<>();
        newList.add(trelloListDto1);
        newList.add(trelloListDto2);
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "new task", newList);
        List<TrelloBoardDto> newBoardDtoList = new ArrayList<>();
        newBoardDtoList.add(trelloBoardDto1);
        //When
        List<TrelloBoard> boardList = trelloMapper.mapToBoards(newBoardDtoList);
        //Then
        assertEquals(1, boardList.size());
        assertEquals("1", trelloListDto1.getId());
        assertEquals("new task", boardList.get(0).getName());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        TrelloList list1 = new TrelloList("1", "first list", true);
        TrelloList list2 = new TrelloList("2", "second list", false);
        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();
        lists1.add(list1);
        lists2.add(list2);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "new task", lists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "new task 2", lists2);
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(trelloBoard1);
        boardList.add(trelloBoard2);
        //When
        List<TrelloBoardDto> boardDtoList = trelloMapper.mapToBoardsDto(boardList);
        //Then
        assertEquals(2, boardDtoList.size());
        assertEquals(lists1, trelloBoard1.getLists());
    }

    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "first List", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "second List", false);
        TrelloListDto trelloListDto3 = new TrelloListDto("3", "third List", true);
        List<TrelloListDto> newList = new ArrayList<>();
        newList.add(trelloListDto1);
        newList.add(trelloListDto2);
        newList.add(trelloListDto3);
        //When
        List<TrelloList> list = trelloMapper.mapToList(newList);
        //Then
        assertEquals(3, list.size());
        assertTrue(trelloListDto3.isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList list1 = new TrelloList("1", "first list", true);
        TrelloList list2 = new TrelloList("2", "second list", false);
        List<TrelloList> lists1 = new ArrayList<>();
        lists1.add(list1);
        lists1.add(list2);
        //When
        List<TrelloListDto> dtoList = trelloMapper.mapToListDto(lists1);
        //Then
        assertEquals(2, dtoList.size());
        assertEquals("1", dtoList.get(0).getId());
        assertEquals("second list", dtoList.get(1).getName());
    }
    @Test
    public void mapToCardTest(){
        //Given
        TrelloCardDto cardDto = new TrelloCardDto("task", "to do", "top", "id 1");
        //When
        TrelloCard card = trelloMapper.mapToCard(cardDto);
        //Then
        assertEquals(cardDto.getName(), card.getName());
    }
    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard card = new TrelloCard("task", "to do", "top", "id 2");
        //When
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);
        //Then
        assertEquals("top", cardDto.getPos());
        assertEquals("id 2", cardDto.getListId());
        assertEquals("task", cardDto.getName());
        assertEquals(card.getDescription(), cardDto.getDescription());
    }
}
