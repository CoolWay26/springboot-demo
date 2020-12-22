package com.coolway.controller.test;


import io.swagger.annotations.ApiOperation;
import javafx.collections.ObservableMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/global")
public class GlobalController {

    @ApiOperation(value = "测试全局数据绑定")
    @GetMapping("/getGlobalModel")
    public void getGlobalModel(Model model) {
        Map map = (Map<String, Object>) model.getAttribute("global_var1");
        System.out.println(map.get("name"));
    }


}
