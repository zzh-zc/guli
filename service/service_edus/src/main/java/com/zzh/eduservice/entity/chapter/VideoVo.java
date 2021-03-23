package com.zzh.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zzh on 2021/03/23
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
}
