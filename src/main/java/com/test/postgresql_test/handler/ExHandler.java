package com.test.postgresql_test.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExHandler {

    @ExceptionHandler
    public String illegalExHandler(IllegalArgumentException e) {
        log.info("[IllegalArgumentException handler]", e);
        return "error/404";
    }

    @ExceptionHandler
    public String accessExHandler(AccessDeniedException e, Model model) {
        log.info("[AccessDeniedException handler]", e);
        model.addAttribute("errorMessage", e.getMessage());
        return "error/4xx";
    }

    @ExceptionHandler
    public String exceptionExHandler(Exception e, Model model, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        log.info("Referer url = {}", referer);
        log.info("[Exception handler]", e);
        model.addAttribute("errorMessage", "에러?");
        return "error/4xx";
    }

    @ExceptionHandler
    public String requestRejectedExceptionHandler(RequestRejectedException e, HttpServletRequest request) {
        log.info("Referer url = {}", request.getHeader("Referer"));
        return "error/500";
    }

}
