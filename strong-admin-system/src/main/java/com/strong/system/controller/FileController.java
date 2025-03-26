package com.strong.system.controller;

import com.strong.common.entity.result.Result;
import com.strong.system.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author charlie
 * @date 2025/3/7 16:57
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam(value = "file") MultipartFile file) {
        final String fileUrl = fileService.upload(file);
        return Result.success(fileUrl);
    }

    @GetMapping("/{date}/{filename}")
    public void download(@PathVariable String date, @PathVariable String filename, HttpServletResponse response) {
        fileService.download(date, filename, response);
    }
}
