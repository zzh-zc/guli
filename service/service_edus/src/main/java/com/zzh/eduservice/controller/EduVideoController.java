package com.zzh.eduservice.controller;


import com.zzh.commonutils.R;
import com.zzh.eduservice.client.VodClient;
import com.zzh.eduservice.entity.EduVideo;
import com.zzh.eduservice.entity.form.VideoInfoForm;
import com.zzh.eduservice.service.EduVideoService;
import com.zzh.servicebase.exceptionHandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2021-03-15
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/video")
@Api(tags="课时管理")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private VodClient vodClient;


    @ApiOperation(value = "新增课时")
    @PostMapping("saveVideoInfo")
    public R save(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm){

        eduVideoService.saveVideoInfo(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("getVideoInfo/{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        VideoInfoForm videoInfoForm = eduVideoService.getVideoInfoFormById(id);
        return R.ok().data("item", videoInfoForm);
    }

    @ApiOperation(value = "更新课时")
    @PostMapping("updateVideoInfo/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoForm videoInfoForm){
        eduVideoService.updateVideoInfoById(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @GetMapping("deleteVideoInfo/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        if(StringUtils.isNotEmpty(eduVideo.getVideoSourceId())){
            R result = vodClient.removeVideo(eduVideo.getVideoSourceId());
            if(result.getCode() == 20001){
                throw new GuliException(20001,result.getMessage());
            }
        }
        boolean result = eduVideoService.removeVideoById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

