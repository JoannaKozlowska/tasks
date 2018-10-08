package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "to do", "in progress");

        //When
        Task task = taskMapper.mapToTask(taskDto1);
        //Then
        assertEquals("to do", task.getTitle());
        assertEquals(taskDto1.getId(), task.getId());
    }

    @Test
    public void mapToTaskDtoTest(){
        //Given
        Task task = new Task(2L, "to do", "planning");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals("planning", taskDto.getContent());
        assertEquals(task.getId(), taskDto.getId());
    }

    @Test
    public void mapToTaskDtoListTest(){
        //Given
        Task task1 = new Task(1L, "to do", "in progress");
        Task task2 = new Task(2L, "to do", "planning");
        Task task3 = new Task(3L, "done", "task finished");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(3, taskList.size());
        assertEquals(3, taskDtoList.size());
        assertEquals("task finished", taskDtoList.get(2).getContent());
    }
}
