package com.zzh.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zzh.eduservice.entity.EduSubject;
import com.zzh.eduservice.entity.excel.SubjectData;
import com.zzh.eduservice.linster.SubjectExcelListener;
import com.zzh.eduservice.mapper.EduSubjectMapper;
import com.zzh.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.servicebase.exceptionHandler.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2020-10-21
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener()).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20002,"添加课程分类失败");
        }

    }
}
