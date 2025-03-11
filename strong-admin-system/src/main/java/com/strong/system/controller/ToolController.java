package com.strong.system.controller;

import com.strong.common.entity.result.Result;
import com.strong.common.util.snowflakeid.SnowflakeIdWorker;
import com.strong.system.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charlie
 * @date 2025/3/7 16:57
 **/
@RestController
@RequestMapping("/tool")
public class ToolController {

    @GetMapping("/id/{num}")
    public Result<List<String>> getUserById(@PathVariable Integer num) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            list.add(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
        }
        return Result.success(list);
    }
}
