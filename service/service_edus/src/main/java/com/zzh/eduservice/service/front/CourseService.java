package com.zzh.eduservice.service.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.eduservice.entity.EduCourse;
import com.zzh.eduservice.entity.vo.CourseQueryVo;
import com.zzh.eduservice.entity.vo.CourseWebVo;

import java.util.List;
import java.util.Map;

public interface CourseService extends IService<EduCourse> {
    List<EduCourse> selectByTeacherId(String teacherId);

    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery);


    /**
     * 获取课程信息
     * @param id
     * @return
     */
    CourseWebVo selectInfoWebById(String id);

    /**
     * 更新课程浏览数
     * @param id
     */
    void updatePageViewCount(String id);
}
