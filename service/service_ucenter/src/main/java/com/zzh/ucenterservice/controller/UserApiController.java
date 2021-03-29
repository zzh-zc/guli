package com.zzh.ucenterservice.controller;

import com.zzh.commonutils.R;
import com.zzh.commonutils.utils.JwtUtils;
import com.zzh.servicebase.exceptionHandler.GuliException;
import com.zzh.ucenterservice.entity.UcenterMember;
import com.zzh.ucenterservice.entity.vo.LoginVo;
import com.zzh.ucenterservice.entity.vo.RegisterVo;
import com.zzh.ucenterservice.service.UserApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zzh on 2021/03/29
 */
@RestController
@CrossOrigin
@RequestMapping("/ucenter/user")
@Api(tags = "前台登录管理")
public class UserApiController {
    @Autowired
    private UserApiService userApiService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = userApiService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        userApiService.register(registerVo);
        return R.ok();
    }


    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            UcenterMember loginInfoVo = userApiService.getByOpenid(memberId);
            return R.ok().data("item", loginInfoVo);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }
    //根据token字符串获取用户信息
    @PostMapping("getInfoUc/{id}")
    public UcenterMember getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = userApiService.getById(id);
//        UcenterMember memeber = new UcenterMember();
//        BeanUtils.copyProperties(ucenterMember,memeber);
        return ucenterMember;
    }
}
