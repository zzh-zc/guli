package com.zzh.vod.controller;

import com.zzh.commonutils.R;
import com.zzh.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by zzh on 2021/03/24
 */
@Api(tags = "阿里云视频点播微服务")
@CrossOrigin //跨域
@RestController
@RequestMapping("/vod/video")
public class VideoAdminController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "上传视频")
    @PostMapping(value = "upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {

        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }
    @ApiOperation("根据云端视频id删除视频")
    @GetMapping("deleteVodById/{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId){

        videoService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    @GetMapping(value = "deleteMoreVideo")
    public R deleteMoreVideo(@RequestParam("videoIdList") List<String> videoIdList){
        videoService.deleteMoreVideos(videoIdList);
        return R.ok();
    }
}
