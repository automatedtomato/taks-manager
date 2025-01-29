package com.automatedtomato.tasks.domain.dto;

import java.util.List;
import java.util.UUID;


 // 格項目については、TaskDtoを参照
public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks
) {

}
