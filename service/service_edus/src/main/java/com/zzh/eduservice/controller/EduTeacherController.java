package com.zzh.eduservice.controller;


import com.zzh.eduservice.entity.EduTeacher;
import com.zzh.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzh
 * @since 2020-09-14
 */
@Api(tags={"讲师管理"})
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

        @Autowired
        private EduTeacherService eduTeacherService;
        //查询所有讲师列表
        @ApiOperation(value = "所有讲师列表")
        @GetMapping("findAll")
        public List<EduTeacher> findAll(){
            List<EduTeacher> list = eduTeacherService.list(null);
            return list;
        }
        @ApiOperation(value = "根据ID删除讲师")
        @DeleteMapping("{id}")
        public boolean removeById(@PathVariable String id){
            return eduTeacherService.removeById(id);
        }

}

