package com.zzh.eduservice.controller;


import com.zzh.commonutils.R;
import com.zzh.eduservice.entity.EduChapter;
import com.zzh.eduservice.entity.chapter.ChapterVo;
import com.zzh.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 章节前端控制器
 * </p>
 *
 * @author zzh
 * @since 2021-03-15
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/chapter")
@Api(tags = "课程章节")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "根据id查询章节和小节")
    @GetMapping(value = "getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId){
        List<ChapterVo> list = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping(value = "addEduChapter")
    public R save(
            @ApiParam(name = "eduChapter", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }
    @ApiOperation(value = "根据ID查询章节")
    @GetMapping(value = "getEduChapter/{id}")
    public R getById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){
        EduChapter chapter = eduChapterService.getById(id);
        return R.ok().data("item", chapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PostMapping(value = "updateEduChapter")
    public R updateById(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除章节")
    @GetMapping("deleteEduChapter/{id}")
    public R removeById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id){

        boolean result = eduChapterService.removeChapterById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

