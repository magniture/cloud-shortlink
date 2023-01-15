package com.zq.exception;

import com.zq.utils.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handle(Exception e) {

        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            log.info("[业务异常]{}", e);
            return JsonData.buildCodeAndMsg(bizException.getCode(),bizException.getMsg());

        } else {
            log.info("[系统异常]{}", e);
            return JsonData.buildError("全局异常，未知错误");
        }

    }
}
