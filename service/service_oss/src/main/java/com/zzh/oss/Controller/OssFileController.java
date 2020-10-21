package com.zzh.oss.Controller;

import com.zzh.commonutils.R;
import com.zzh.oss.Service.OssFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags={"文件上传"})
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssFileController {

    @Autowired
    private OssFileService ossFileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("uploadFile")
    public R uploadOssFile(@ApiParam(name = "file", value = "文件", required = true)
                           @RequestParam("file") MultipartFile file){
        //返回上传到oss的url地址
        String url = ossFileService.uploadFile(file);
        return R.ok().message("文件上传成功").data("url",url);
    }

}
