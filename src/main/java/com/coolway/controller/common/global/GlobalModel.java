package com.coolway.controller.common.global;

import com.coolway.controller.common.ResponseResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/global")
@ControllerAdvice
public class GlobalModel {

    @ExceptionHandler(RuntimeException.class)
    public ResponseResult runtimeExceptionHandler() {
        return new ResponseResult().resultFlag(false).message("RuntimeException!");
    }

    @ModelAttribute(name = "global_var1")
    @ResponseBody
    public Map<String, Object> dateVar1() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","zs");
        map.put("age",20);
        return map;
    }


}
