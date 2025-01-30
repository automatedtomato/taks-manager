package com.automatedtomato.tasks.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.automatedtomato.tasks.domain.entities.TaskList;

@Service
public interface TaskListService {

    public List<TaskList> listTaskLists();
    TaskList createTaskList(TaskList taskList);
}
