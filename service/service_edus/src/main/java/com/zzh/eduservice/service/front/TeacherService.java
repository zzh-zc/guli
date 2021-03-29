package com.zzh.eduservice.service.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.eduservice.entity.EduTeacher;

import java.util.Map;

public interface TeacherService extends IService<EduTeacher> {
    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
}
