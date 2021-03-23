package com.zzh.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzh.eduservice.entity.EduChapter;
import com.zzh.eduservice.entity.EduVideo;
import com.zzh.eduservice.entity.chapter.ChapterVo;
import com.zzh.eduservice.entity.chapter.VideoVo;
import com.zzh.eduservice.mapper.EduChapterMapper;
import com.zzh.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.eduservice.service.EduVideoService;
import com.zzh.servicebase.exceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2021-03-15
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id查询所以章节
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(queryWrapper);
        //根据课程id查询课程的所以小节
        QueryWrapper<EduVideo> queryVideo = new QueryWrapper<>();
        queryVideo.eq("course_id",courseId);
        List<EduVideo> list = eduVideoService.list(queryVideo);

        List<ChapterVo> chapterVoList = new ArrayList<>();

        eduChapters.parallelStream().forEach(res ->{
            List<VideoVo> videoVoList = new ArrayList<>();
            List<EduVideo> collect = list.parallelStream().filter(res2 -> res2.getChapterId().equals(res.getId())).collect(Collectors.toList());
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(res,chapterVo);
            collect.parallelStream().forEach(res3 ->{
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(res3,videoVo);
                videoVoList.add(videoVo);
            });
            chapterVo.setChildren(videoVoList);
            chapterVoList.add(chapterVo);
        });
        return chapterVoList;
    }

    @Override
    public boolean removeChapterById(String id) {
        //根据id查询是否存在视频，如果有则提示用户尚有子节点
        if(eduVideoService.getCountByChapterId(id)){
            throw new GuliException(20001,"该分章节下存在视频课程，请先删除视频课程");
        }

        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Override
    public boolean removeByCourseId(String id) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        Integer count = baseMapper.delete(queryWrapper);
        return null != count && count > 0;
    }
}
