package com.zzh.cmsservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzh.cmsservice.entity.CrmBanner;
import com.zzh.cmsservice.service.CrmBannerService;
import com.zzh.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zzh on 2021/03/25
 */
@RestController
@RequestMapping("/educms/banner")
@Api(tags = "网站首页Banner列表")
@CrossOrigin //跨域
public class BannerApiController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");

        List<CrmBanner> list = bannerService.list(queryWrapper);
        return R.ok().data("bannerList", list);
    }

}
