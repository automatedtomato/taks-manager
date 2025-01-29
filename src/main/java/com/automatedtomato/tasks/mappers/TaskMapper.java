package com.automatedtomato.tasks.mappers;

import com.automatedtomato.tasks.domain.dto.TaskDto;
import com.automatedtomato.tasks.domain.entities.Task;

/*
 * TaskMapperは、TaskとTaskDtoを相互に変換するためのインターフェイス。
 * ドメイン（Entity）と表象（DTO）を相互に変換するために使用される。
 * なぜMapperが必要か：
 *  1. ロジックの分離と再利用性の向上
 *  2. アプリ内で一貫した変換方式を維持
 *  3. マッピングのロジックの維持と修正を簡易化
 *  4. コードの見やすさ
 */
public interface TaskMapper {

    Task fromDto(TaskDto taskDto);  // TaskDto（抽象）からTask（実体）へ変換

    TaskDto toDto(Task task);  // Task（実体）からTaskDto（抽象）へ変換
}
