package com.automatedtomato.tasks.mappers.impl;

import org.springframework.stereotype.Component;

import com.automatedtomato.tasks.domain.dto.TaskDto;
import com.automatedtomato.tasks.domain.entities.Task;
import com.automatedtomato.tasks.mappers.TaskMapper;

// fromDtoとtoDtoの説明は、TaskMapper.javaを参照
@Component
public class TaskMapperImpl implements TaskMapper{

    // fromDtoとtoDtoを再定義
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,  // タイムスタンプとtaskListの設定はservice層に任せる
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
            task.getId(), 
            task.getTitle(), 
            task.getDescription(), 
            task.getDueDate(),
            task.getStatus(), 
            task.getPriority()
        );
    }
}
