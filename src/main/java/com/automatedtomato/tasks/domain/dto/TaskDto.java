package com.automatedtomato.tasks.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.automatedtomato.tasks.domain.entities.TaskPriority;
import com.automatedtomato.tasks.domain.entities.TaskStatus;


/*
 * DTO(Data Transfer Object)とは、データを移動するためのオブジェクト。
 * REST APIのEntityをrepresentするために用いられる。
 * DTOによって、APIコントラクトをドメインのモデルから分離し、
 * どのデータがクライアントに送信されるかを制御できる。
 * 簡単に言うと、クライアントに送信されるデータを制限するためのオブジェクト。
 */

 // recordを用いることで、コンストラクタ、getterやequals()、hashCode()、toString()を自動生成。
 // すべてのフィールドはfinalとなる。
public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
        // createdはサーバ側で生成
        // updatedはサーバ側で更新
        // taskListはREST APIのURLで指定
        // JPAは不要：DTOは単純なデータのみを格納
) {
/* 上記のDTOによって、
 *  - APIのリクエストに応じたtaskの情報の受け渡しが可能
 *  - taskのデータをさまざまなレイヤー間で共有可能
 */

}
