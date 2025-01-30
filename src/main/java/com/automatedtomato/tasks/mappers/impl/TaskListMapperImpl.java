package com.automatedtomato.tasks.mappers.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.automatedtomato.tasks.domain.dto.TaskListDto;
import com.automatedtomato.tasks.domain.entities.Task;
import com.automatedtomato.tasks.domain.entities.TaskList;
import com.automatedtomato.tasks.domain.entities.TaskStatus;
import com.automatedtomato.tasks.mappers.TaskListMapper;
import com.automatedtomato.tasks.mappers.TaskMapper;

// 詳しくは、TaskMapper.javaを参照
@Component
public class TaskListMapperImpl implements TaskListMapper {

    // TaskMapperを注入（依存性の注入: Dependency Injection）
    //          <- リスト内のTaskを変換するために、TaskMapperを使用
    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    // fromDtoとtoDtoを再定義
    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
            taskListDto.id(),
            taskListDto.title(),
            taskListDto.description(),  // 基本的な情報を設定
            // taskListはNullの可能性があるため、Optionalを使用
            Optional.ofNullable(taskListDto.tasks())
                // fromDtoを使用してTaskを変換し、リストに格納
                .map(tasks -> tasks.stream().map(taskMapper::fromDto).toList()
                ).orElse(null),
            null,
            null  // タイムスタンプの設定はservice層に任せる
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {

        final List<Task> tasks = taskList.getTasks();

        return new TaskListDto(
            taskList.getId(),
            taskList.getTitle(),
            taskList.getDescription(),  // 基本的な情報を設定
            // taskListはNullの可能性があるため、Optionalを使用
            Optional.ofNullable(tasks)
                .map(List::size)  // taskの数を取得
                .orElse(0),
            // taskListの進捗率を、補助メソッドを用いて計算
            calculateTaskListProgress(tasks),
            Optional.ofNullable(tasks)
                    .map(t -> 
                            t.stream()
                            .map(taskMapper::toDto)
                            .toList())
                            .orElse(null)
        );
    }

    // taskListの進捗率を測定するための補助メソッド
    private Double calculateTaskListProgress(List<Task> tasks) {
        // TaskListがnullの場合はnullを返す
        if (null == tasks) {
            return null;
        }

        // 全タスクのうち、closeしたタスクの比率を0〜1で算定
        long closedTaskCount = tasks.stream().filter(task -> 
                TaskStatus.CLOSED == task.getStatus()
        ).count();

        return (double) closedTaskCount / tasks.size();
    }

}
