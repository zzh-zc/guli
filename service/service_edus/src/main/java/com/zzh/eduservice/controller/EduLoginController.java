package com.zzh.eduservice.controller;

import com.zzh.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags={"登陆管理"})
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {



    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public R login(){

        return R.ok();
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping("getUserInfo")
    public R getUserInfo(){

        return R.ok();
    }
}
