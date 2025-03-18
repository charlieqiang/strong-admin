package com.strong.biz.controller;

import com.strong.biz.vo.TableVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.strong.common.entity.result.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charlie
 * @date 2025/3/17 11:05
 **/
@RestController
@RequestMapping("/table")
public class TableController {

    @GetMapping("/list")
    public Result<List<TableVo>> table() {
        List<TableVo> list = new ArrayList<>();
        TableVo tableVo = new TableVo();
        tableVo.setId("1");
        tableVo.setTitle("Lbmhypys wvfer efyiycqbtr vdhhulbq kkhxzhn qqilvb zrydrpno vfqjtmwvlg jyupbsjqw nmdmbkyb xgqs altchsvzc.");
        tableVo.setAuthor("tony");
        tableVo.setPageviews("9526");
        tableVo.setStatus("published");
        tableVo.setDisplay_time("2020-10-15 12:56:58");
        list.add(tableVo);
        return Result.success(list);
    }
}
