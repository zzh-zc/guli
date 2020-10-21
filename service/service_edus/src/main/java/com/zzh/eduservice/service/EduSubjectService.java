package com.zzh.eduservice.service;

import com.zzh.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zzh
 * @since 2020-10-21
 */
public interface EduSubjectService extends IService<EduSubject> {

    public void saveSubject(MultipartFile file,EduSubjectService subjectService);
}
