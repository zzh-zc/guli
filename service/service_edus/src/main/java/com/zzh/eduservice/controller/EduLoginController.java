package com.zzh.eduservice.controller;

import com.zzh.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(tags={"后台登陆管理"})
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {


    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("getUserInfo")
    public R getUserInfo(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
