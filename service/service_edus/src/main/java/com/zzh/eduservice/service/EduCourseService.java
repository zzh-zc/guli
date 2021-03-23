package com.zzh.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.eduservice.entity.vo.CourseInfoVo;
import com.zzh.eduservice.entity.vo.CoursePublishVo;
import com.zzh.eduservice.entity.vo.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zzh
 * @since 2021-03-15
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String id);

    boolean publishCourseById(String id);

    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    boolean removeCourseById(String id);
}
