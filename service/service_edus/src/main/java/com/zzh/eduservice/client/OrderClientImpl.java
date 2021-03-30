package com.zzh.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * Created by zzh on
 */
@Component
public class OrderClientImpl implements OrderClient{
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        return false;
    }
}
