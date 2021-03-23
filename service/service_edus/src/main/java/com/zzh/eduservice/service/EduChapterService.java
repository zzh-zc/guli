package com.zzh.eduservice.service;

import com.zzh.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zzh
 * @since 2021-03-15
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean removeChapterById(String id);

    boolean removeByCourseId(String id);
}
