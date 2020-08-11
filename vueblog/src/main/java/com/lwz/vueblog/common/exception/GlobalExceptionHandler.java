package com.lwz.vueblog.common.exception;


import com.lwz.vueblog.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author Lw中
 * @date 2020/6/20 15:48
 *
 * @Slf4j：用作日志输出的，一般会在项目每个类的开头加入该注解。
 * 添加了该注释之后，就可以在代码中直接饮用log.info( ) 打印日志了。
 * @RestControllerAdvice+@ExceptionHandler：定义全局异常捕获机制
 * @ResponseStatus：带有@ResponseStatus注解的异常类会被ResponseStatusExceptionResolver 解析。
 * 可以实现自定义的一些异常,同时在页面上进行显示。
 *
 */
@Slf4j
@RestControllerAdvice
/**
 * 自定义全局异常处理
 */
public class GlobalExceptionHandler {

    /** 没有权限、登录异常*/
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e) {
        /** 在控制台输出*/
        log.error("运行时异常：----------------{}", e);
        return Result.fail(401, e.getMessage(), null);
    }

    /** 实体校验异常*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常：----------------{}", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();

        return Result.fail(objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) {
        log.error("Assert（断言）异常：----------------{}", e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e);
        return Result.fail(e.getMessage());
    }
}
