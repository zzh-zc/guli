package com.zzh.eduservice.controller;


import com.zzh.commonutils.R;
import com.zzh.eduservice.entity.EduSubject;
import com.zzh.eduservice.entity.vo.OneSubject;
import com.zzh.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zzh
 * @since 2020-10-21
 */
@Api(tags = {"课程模块"})
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        eduSubjectService.saveSubject(file,eduSubjectService);
        //判断返回集合是否为空
        return R.ok();
    }

    @ApiOperation(value = "嵌套数据列表（分类一二级）")
    @GetMapping("findAllByNest")
    public R nestedList(){
        List<OneSubject> subjectNestedVoList = eduSubjectService.nestedList();
        return R.ok().data("items", subjectNestedVoList);
    }

}

