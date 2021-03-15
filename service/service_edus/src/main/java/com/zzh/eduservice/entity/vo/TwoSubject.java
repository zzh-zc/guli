package com.zzh.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by zzh on 2021/03/15
 */
@ApiModel(value = "课程二级分类")
@Data
public class TwoSubject {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "名称")
    private String title;
}
