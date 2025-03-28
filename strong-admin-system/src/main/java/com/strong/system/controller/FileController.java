package com.strong.system.controller;

import com.strong.common.entity.result.Result;
import com.strong.system.service.FileService;
import com.strong.system.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public Result<FileVo> upload(@RequestParam(value = "file") MultipartFile file) {
        String filePath = fileService.uploadToAliyun(file);
        String fileUrl = fileService.convertPathToUrl(filePath);
        FileVo fileVo = new FileVo();
        fileVo.setUrl(fileUrl);
        fileVo.setPath(filePath);
        return Result.success(fileVo);
    }
}
