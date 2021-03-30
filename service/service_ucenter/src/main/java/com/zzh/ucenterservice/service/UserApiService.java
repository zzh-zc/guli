package com.zzh.ucenterservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzh.ucenterservice.entity.UcenterMember;
import com.zzh.ucenterservice.entity.vo.LoginVo;
import com.zzh.ucenterservice.entity.vo.RegisterVo;

public interface UserApiService extends IService<UcenterMember> {
    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    LoginVo getLoginInfo(String memberId);

    UcenterMember getByOpenid(String openid);

    Integer countRegisterByDay(String day);
}
