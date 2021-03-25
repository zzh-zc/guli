package com.zzh.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzh.commonutils.R;
import com.zzh.eduservice.entity.EduCourse;
import com.zzh.eduservice.entity.EduTeacher;
import com.zzh.eduservice.service.EduCourseService;
import com.zzh.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zzh on 2021/03/25
 */
@RestController
@RequestMapping(value = "/eduservice/front")
@CrossOrigin
public class EduFrontController {

    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("index")
    public R indexFront(){
        QueryWrapper<EduCourse> queryCourse = new QueryWrapper<>();
        queryCourse.orderByDesc("id");
        queryCourse.last("limit 8");
        List<EduCourse> listCourse = eduCourseService.list(queryCourse);

        QueryWrapper<EduTeacher> queryTeacher = new QueryWrapper<>();
        queryTeacher.orderByDesc("id");
        queryTeacher.last("limit 4");
        List<EduTeacher> listTeacher = eduTeacherService.list(queryTeacher);

        return R.ok().data("eduList",listCourse).data("teacherList",listTeacher);
    }
}
