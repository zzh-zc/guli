package com.zzh.eduservice.mapper;

import com.zzh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.eduservice.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zzh
 * @since 2021-03-15
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    @Select("SELECT" +
            "        c.title," +
            "        c.cover," +
            "        c.lesson_num AS lessonNum," +
            "        CONVERT(c.price, DECIMAL(8,2)) AS price," +
            "        s1.title AS subjectLevelOne," +
            "        s2.title AS subjectLevelTwo," +
            "        t.name AS teacherName" +
            "    FROM" +
            "        edu_course c" +
            "        LEFT JOIN edu_teacher t ON c.teacher_id = t.id" +
            "        LEFT JOIN edu_subject s1 ON c.subject_parent_id = s1.id" +
            "        LEFT JOIN edu_subject s2 ON c.subject_id = s2.id" +
            "    WHERE" +
            "        c.id = #{id}")
    CoursePublishVo getCoursePublishVoById(@Param("id") String id);
}
