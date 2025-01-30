package com.automatedtomato.tasks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.automatedtomato.tasks.domain.dto.ErrorResponse;

@ControllerAdvice  // 当クラスが全コントローラの例外処理をすることを宣言
public class GlobalExceptionHandler {

    // このメソッドがどの例外を処理するかを宣言
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleExceptions(
        RuntimeException ex, WebRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);      
    } 
    /*
     * 1. Springが@ControllerAdviceを検索
     * 2. 該当するメソッドを呼び出し
     * 3. handlerがErrorResponseを生成
     * 4. SpringはErrorResponseをJSONに変換しクライアントに送信
     * 
     * 利点：
     *  - 情報の露出量を調整可能に
     *  - 内部での例外の詳細はクライアント側には伝わらない
     *  - エラー・例外処理ロジックを一つのクラスに集積
     */
}
