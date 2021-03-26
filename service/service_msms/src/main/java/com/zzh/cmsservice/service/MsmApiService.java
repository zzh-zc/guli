package com.zzh.cmsservice.service;

import java.util.Map;

public interface MsmApiService{
    boolean send(String phone, String sms_180051135, Map<String, Object> param);
}
