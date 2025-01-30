package com.automatedtomato.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.automatedtomato.tasks.domain.entities.TaskList;
import com.automatedtomato.tasks.repository.TaskListRepository;
import com.automatedtomato.tasks.services.TaskListService;

@Service  // このクラスをサービスコンポーネントとしてSpringに登録
public class TaskListServiceImpl implements TaskListService{

    // コンストラクタ注入
    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists(){
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("Task List already has an ID");
        }
        if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw  new IllegalArgumentException("Task List must have a title");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
            null,
            taskList.getTitle(),
            taskList.getDescription(),
            null,
            now,
            now
        ));
    }

    
}
