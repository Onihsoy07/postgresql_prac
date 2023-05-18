package com.test.postgresql_test.handler;

import com.test.postgresql_test.domain.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
//@Controller
public class GlobalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<String> globalIllegalArgumentExceptionHandler(IllegalArgumentException e) {
        return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseDto<String> globalExceptionHandler(Exception e) {
       LOGGER.warn(e.getMessage());
        return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

//    private String exceptionHandler(ResponseDto<String> responseDto,
//                                    Model model) {
//        model.addAttribute("responseDto", responseDto);
//        return "exception/globalException";
//    }

}
