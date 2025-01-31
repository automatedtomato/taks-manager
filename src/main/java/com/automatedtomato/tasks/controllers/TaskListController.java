package com.automatedtomato.tasks.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.automatedtomato.tasks.domain.dto.TaskListDto;
import com.automatedtomato.tasks.domain.entities.TaskList;
import com.automatedtomato.tasks.mappers.TaskListMapper;
import com.automatedtomato.tasks.services.TaskListService;

// クラスレベルアノテーション
@RestController  // RESTコントローラであることを示す
@RequestMapping(path = "/task-lists")  // リクエストの基本パスを指定
public class TaskListController {

    // 依存性の注入
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
            .stream()
            .map(taskListMapper::toDto)
            .toList();
    }
    /*
     * 1. GETリクエストが/task-listsに送られると、listTaskListsメソッドが呼び出される
     * 2. コントローラがtaskListService.listTaskLists()メソッドを呼び出す
     * 3. サービスがtaskListRepository.findAll()メソッドを呼び出す
     * 4. リポジトリがTaskListを取得する
     * 5. コントローラはtaskListMapper.toDto(taskList)を使用して、TaskListをTaskListDtoに変換する
     * 6. TaskListDtoをクライエントに返す
     */

    @PostMapping
    // RequestBodyアノテーションは、SpringにリクエストボディをDTOにデシリアライズするよう指示
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskList createdTaskList = taskListService.createTaskList(
            taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }
    /*
     * 1. SpringがJSONリクエストボディをDTOにデシリアライズする
     * 2. コントローラがDTOをTaskListエンティティに変換
     * 3. サービスが新しいTaskListを作成
     * 4. リポジトリがTaskListをDBに維持
     * 5. コントローラが保存されたエンティティをDTOに再変換
     * 6. SpringがDTOをJSONにシリアライズしてレスポンス
     */

     @GetMapping(path = "/{task_list_id}")
     public Optional<TaskListDto> getTaskList(
                @PathVariable("task_list_id") UUID taskListId) {
            return taskListService.getTaskList(taskListId)
                    .map(taskListMapper::toDto);
     }
    
     @PutMapping(path = "/{task_list_id}")
     public TaskListDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestParam TaskListDto taskListDto
     ) {
        TaskList updatedTaskList = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );

        return taskListMapper.toDto(updatedTaskList);
     }

     @DeleteMapping(path = "/task_list_id") 
     public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
     }

}
