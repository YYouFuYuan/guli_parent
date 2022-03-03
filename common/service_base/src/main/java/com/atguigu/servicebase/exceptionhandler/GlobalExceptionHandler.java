package com.atguigu.servicebase.exceptionhandler;






import com.atguigu.commonutils.ExceptionUtil;
import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message("全局异常处理");
    }


    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public R error(GuliException e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
