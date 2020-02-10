package com.whu.lysl.base.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.utils.ArgumentInvalidResult;
import com.whu.lysl.web.LYSLResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author Caspar
 * @CreateTime 2020/2/10 16:04
 * @Description:
 */

@ControllerAdvice
//如果返回的为json数据或其它对象，添加该注解
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public Object MethodArgumentNotValidHandler(HttpServletRequest request,
                                                MethodArgumentNotValidException exception) throws Exception
    {
        //按需重新封装需要返回的错误信息
        List<ArgumentInvalidResult> invalidArguments = new ArrayList<>();
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
            invalidArgument.setDefaultMessage(error.getDefaultMessage());
            invalidArgument.setField(error.getField());
            invalidArgument.setRejectedValue(error.getRejectedValue());
            invalidArguments.add(invalidArgument);
        }

        LYSLResult<Object> result = new LYSLResult<>();
        result.setSuccess(false);
        result.setResultCode(LYSLResultCodeEnum.DATA_INVALID.getCode());
        result.setResultDesc(LYSLResultCodeEnum.DATA_INVALID.getDescription());
        result.setResultObj(invalidArguments);
        return result;
    }
}
