package com.zzh.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.eduservice.entity.EduCourse;
import com.zzh.eduservice.entity.EduCourseDescription;
import com.zzh.eduservice.entity.vo.CourseInfoVo;
import com.zzh.eduservice.entity.vo.CoursePublishVo;
import com.zzh.eduservice.entity.vo.CourseQuery;
import com.zzh.eduservice.mapper.EduCourseMapper;
import com.zzh.eduservice.service.EduChapterService;
import com.zzh.eduservice.service.EduCourseDescriptionService;
import com.zzh.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.eduservice.service.EduVideoService;
import com.zzh.servicebase.exceptionHandler.GuliException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2021-03-15
 */
@Service
@Transactional
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduCourseMapper eduCourseMapper;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.insert(eduCourse);
        if(1==0){
            throw new GuliException(200001,"添加课程信息失败");
        }

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        eduCourseDescription.setId(eduCourse.getId());
        boolean j = eduCourseDescriptionService.save(eduCourseDescription);
        if(!j){
            throw new GuliException(200001,"添加课程描述信息失败");
        }
        return eduCourse.getId();
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription byId = eduCourseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        courseInfoVo.setDescription(byId.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);

        if(StringUtils.isNotEmpty(courseInfoVo.getDescription())){
            EduCourseDescription eduCourseDescription = new EduCourseDescription();
            eduCourseDescription.setId(courseInfoVo.getId());
            eduCourseDescription.setDescription(courseInfoVo.getDescription());
            eduCourseDescriptionService.updateById(eduCourseDescription);
        }

    }

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return eduCourseMapper.getCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        course.setStatus("1");
        Integer count = baseMapper.updateById(course);
        return null != count && count > 0;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean removeCourseById(String id) {
        //根据id删除所有视频
        eduVideoService.removeByCourseId(id);

        //根据id删除所有章节
        eduChapterService.removeByCourseId(id);

        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }
}
