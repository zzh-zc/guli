package com.zzh.eduservice.service;

import com.zzh.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.eduservice.entity.form.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author zzh
 * @since 2021-03-15
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean getCountByChapterId(String id);

    void saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);

    Boolean removeByCourseId(String id);
}
