package com.zzh.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.commonutils.utils.JwtUtils;
import com.zzh.commonutils.utils.MD5;
import com.zzh.servicebase.exceptionHandler.GuliException;
import com.zzh.ucenterservice.entity.UcenterMember;
import com.zzh.ucenterservice.entity.vo.LoginVo;
import com.zzh.ucenterservice.entity.vo.RegisterVo;
import com.zzh.ucenterservice.mapper.UserMapper;
import com.zzh.ucenterservice.service.UserApiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by zzh on 2021/03/29
 */
@Service
public class UserApiServiceImpl extends ServiceImpl<UserMapper, UcenterMember>implements UserApiService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        //校验参数
        if(StringUtils.isEmpty(loginVo.getMobile()) || StringUtils.isEmpty(loginVo.getPassword())) {
            throw new GuliException(20001,"error");
        }
        //获取会员
        UcenterMember user = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", loginVo.getMobile()));
        if(null == user) {
            throw new GuliException(20001,"error");
        }
        //校验密码
        if(!MD5.encrypt(loginVo.getPassword()).equals(user.getPassword())) {
            throw new GuliException(20001,"error");
        }
        //校验是否被禁用
        if(user.getIsDisabled()) {
            throw new GuliException(20001,"error");
        }
        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(user.getId(), user.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //校验参数
        if(StringUtils.isEmpty(registerVo.getMobile()) ||
                StringUtils.isEmpty(registerVo.getPassword()) ||
                StringUtils.isEmpty(registerVo.getCode())) {
            throw new GuliException(20001,"参数为空");
        }
        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(registerVo.getMobile());
        if(!registerVo.getCode().equals(mobleCode)) {
            throw new GuliException(20001,"验证码错误");
        }
        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", registerVo.getMobile()));
        if(count.intValue() > 0) {
            throw new GuliException(20001,"改手机号码已被注册");
        }
        //添加注册信息到数据库
        UcenterMember user = new UcenterMember();
        user.setNickname(registerVo.getNickname());
        user.setMobile(registerVo.getMobile());
        user.setPassword(MD5.encrypt(registerVo.getPassword()));
        user.setIsDisabled(false);
        user.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(user);
    }

    @Override
    public LoginVo getLoginInfo(String userId) {
        UcenterMember user = baseMapper.selectById(userId);
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(user, loginVo);
        return loginVo;
    }

    @Override
    public UcenterMember getByOpenid(String openid) {
//        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("openid", openid);
//        UcenterMember member = baseMapper.selectOne(queryWrapper);
        UcenterMember user = baseMapper.selectById(openid);
        return user;
    }

    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }
}
