package com.zzh.eduservice.client;

import com.zzh.commonutils.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @GetMapping("/vod/video/deleteVodById/{videoId}")
    R removeVideo(@PathVariable("videoId") String videoId);

    @GetMapping(value = "/vod/video/deleteMoreVideo")
    R deleteMoreVideo(@RequestParam("videoIdList") List<String> videoIdList);
}
