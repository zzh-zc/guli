package com.zzh.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.commonutils.R;
import com.zzh.commonutils.utils.JwtUtils;
import com.zzh.commonutils.vo.CourseWebVoOrder;
import com.zzh.eduservice.client.OrderClient;
import com.zzh.eduservice.entity.EduCourse;
import com.zzh.eduservice.entity.chapter.ChapterVo;
import com.zzh.eduservice.entity.vo.CourseInfoVo;
import com.zzh.eduservice.entity.vo.CourseQueryVo;
import com.zzh.eduservice.entity.vo.CourseWebVo;
import com.zzh.eduservice.service.EduChapterService;
import com.zzh.eduservice.service.EduCourseService;
import com.zzh.eduservice.service.front.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zzh on
 */
@RestController
@RequestMapping("/eduservice/front/course")
@CrossOrigin
@Api(tags = "前台课程列表")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private OrderClient orderClient;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return  R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId){
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = courseService.selectInfoWebById(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);
    }


    //根据课程id查询课程信息
    @GetMapping("getDto/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable String courseId) {
        CourseInfoVo courseInfo1 = eduCourseService.getCourseInfo(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo1,courseWebVoOrder);
        return courseWebVoOrder;
    }


    //根据id查询课程详情信息
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id, HttpServletRequest request) {
        //课程查询课程基本信息
            CourseInfoVo courseFrontInfo = eduCourseService.getCourseInfo(id);
        //查询课程里面大纲数据
        List<ChapterVo> chapterVideoList = eduChapterService.getChapterVideoByCourseId(id);
        //远程调用，判断课程是否被购买
        boolean buyCourse = orderClient.isBuyCourse(JwtUtils.getMemberIdByJwtToken(request), id);

        return R.ok().data("courseFrontInfo",courseFrontInfo).data("chapterVideoList",chapterVideoList).data("isbuy",buyCourse);
    }
}
