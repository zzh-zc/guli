package com.zzh.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 2021/03/15
 */
@Data
@ApiModel(value = "课程一级分类")
public class OneSubject {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "名称")
    private String title;
    @ApiModelProperty(value = "一级分类的二级子分类")
    private List<TwoSubject> children = new ArrayList<>();
}
