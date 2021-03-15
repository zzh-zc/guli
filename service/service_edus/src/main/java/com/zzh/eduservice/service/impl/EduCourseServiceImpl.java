package com.zzh.eduservice.service.impl;

import com.zzh.eduservice.entity.EduCourse;
import com.zzh.eduservice.entity.EduCourseDescription;
import com.zzh.eduservice.entity.vo.CourseInfoVo;
import com.zzh.eduservice.mapper.EduCourseMapper;
import com.zzh.eduservice.service.EduCourseDescriptionService;
import com.zzh.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.servicebase.exceptionHandler.GuliException;
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
}
