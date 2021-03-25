package com.zzh.eduservice.client;

import com.zzh.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zzh on 2021/03/24
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteMoreVideo(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
