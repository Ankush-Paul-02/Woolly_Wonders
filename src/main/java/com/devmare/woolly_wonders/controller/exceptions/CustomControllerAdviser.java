package com.devmare.woolly_wonders.controller.exceptions;

import com.devmare.woolly_wonders.business.domain.DefaultResponse;
import com.devmare.woolly_wonders.data.exception.UserInfoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.devmare.woolly_wonders.business.domain.DefaultResponse.Status.FAILED;

@RestControllerAdvice
public class CustomControllerAdviser {

    @ExceptionHandler(UserInfoException.class)
    public ResponseEntity<DefaultResponse> handleNewUserException(UserInfoException ex) {
        DefaultResponse response = new DefaultResponse(FAILED, ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
