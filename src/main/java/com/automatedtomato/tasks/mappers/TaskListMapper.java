package com.automatedtomato.tasks.mappers;

import com.automatedtomato.tasks.domain.dto.TaskListDto;
import com.automatedtomato.tasks.domain.entities.TaskList;

// Mapperインターフェイスの説明は、TaskMapper.javaを参照
public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskList taskList);

}
