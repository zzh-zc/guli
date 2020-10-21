package com.zzh.eduservice.controller;


import com.zzh.commonutils.R;
import com.zzh.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@CrossOrigin
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

}

