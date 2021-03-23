package com.zzh.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzh on 2021/03/23
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
