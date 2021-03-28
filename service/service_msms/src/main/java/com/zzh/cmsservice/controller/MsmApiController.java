package com.zzh.cmsservice.controller;

import com.zzh.cmsservice.service.MsmApiService;
import com.zzh.commonutils.R;
import com.zzh.commonutils.utils.RandomUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzh on 2021/03/26
 */
@RestController
@RequestMapping(value = "/edumsm/msm")
@CrossOrigin
public class MsmApiController {

    @Autowired
    private MsmApiService msmApiService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/send/{phone}")
    public R sendMessage(@PathVariable String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return R.ok(); //短信还未过期则不发送新的验证码
        } else{
            redisTemplate.opsForValue().set(phone, "1101",5, TimeUnit.MINUTES);
        }
//       code = RandomUtil.getFourBitRandom();
//        Map<String,Object> param = new HashMap<>();
//        param.put("code", code);
//        boolean isSend = msmApiService.send(phone, "SMS_180051135", param);
        return R.ok();
//        if(isSend) {
//            redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
//            return R.ok();
//        } else {
//            return R.error().message("发送短信失败");
//        }
    }
}
