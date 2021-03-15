package com.zzh.eduservice.service;

import com.zzh.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.eduservice.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zzh
 * @since 2020-10-21
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> nestedList();;
}
