package com.automatedtomato.tasks.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.automatedtomato.tasks.domain.entities.TaskList;
import com.automatedtomato.tasks.repository.TaskListRepository;
import com.automatedtomato.tasks.services.TaskListService;

@Service  // このクラスをサービスコンポーネントとしてSpringに登録
public class TaskListServiceImpl implements TaskListService{

    // リポジトリクラスのコンストラクタ注入
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
        // TaskListがすでにIDを持ってないかチェック
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("Task List already has an ID");
        }
        // タイトルがnull／空白かチェック
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

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (null == taskList.getId()) {
            throw new IllegalArgumentException("Task List must have an ID.");
        }

        if (!Objects.equals(taskList.getId().toString(), taskListId.toString())) {
            throw new IllegalArgumentException("Attempting to change task list ID, this is not permitted");
        }
        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() ->
            new IllegalArgumentException("Task list not found"));

       existingTaskList.setTitle(taskList.getTitle());
       existingTaskList.setDescription((taskList.getDescription()));
       existingTaskList.setUpdated(LocalDateTime.now());
       return taskListRepository.save(existingTaskList); 
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
        
    }
}
