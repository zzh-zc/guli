package com.zzh.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzh.eduservice.entity.EduSubject;
import com.zzh.eduservice.entity.excel.SubjectData;
import com.zzh.eduservice.entity.vo.OneSubject;
import com.zzh.eduservice.entity.vo.TwoSubject;
import com.zzh.eduservice.linster.SubjectExcelListener;
import com.zzh.eduservice.mapper.EduSubjectMapper;
import com.zzh.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.servicebase.exceptionHandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<OneSubject> nestedList() {
        //最终要的到的数据列表
        ArrayList<OneSubject> subjectNestedVoArrayList = new ArrayList<>();

        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);

        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

        subjects.parallelStream().forEach(res ->{
            List<TwoSubject> twoSubjectList = new ArrayList<>();
            //筛选出一级菜单下的所以二级菜单  并排寻
            List<EduSubject> list2 = subSubjects.stream().filter(res2 -> res2.getParentId().equals(res.getId())).collect(Collectors.toList()).stream().sorted(Comparator.comparing(EduSubject::getSort)).collect(Collectors.toList());
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(res, oneSubject);
            list2.parallelStream().forEach(res3 ->{
                TwoSubject twoSubject = new TwoSubject();
                BeanUtils.copyProperties(res3, twoSubject);
                twoSubjectList.add(twoSubject);
            });
            oneSubject.setChildren(twoSubjectList);
            subjectNestedVoArrayList.add(oneSubject);
        });
        return subjectNestedVoArrayList;
    }
}
