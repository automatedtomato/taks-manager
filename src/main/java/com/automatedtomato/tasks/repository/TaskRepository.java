package com.automatedtomato.tasks.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.automatedtomato.tasks.domain.entities.Task;

/*
 * リポジトリは、データストレージを抽象化するためのインターフェイス。
 * データベース上の操作を実行する。
 * Spring Data JPAによって自動的に実装される。
 */
@Repository
// JpaRepositoryは、CRUD操作、ページ化、ソート、クエリメソッドを実装するためのインターフェイス
public interface TaskRepository extends JpaRepository<Task, UUID> {
    // パラメータ：このリポジトリが管理するEntityの型と、そのEntityの主キーの型
    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListLd, UUID id);
    // Springは下記のようなクエリを自動的に生成する
    // `SELECT t FROM Task t WHERE task_list_id = ?1 AND t.id = ?2`
}
