package com.zzh.orderservice.service;

import com.zzh.orderservice.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author zzh
 * @since 2021-03-30
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String memberId);
}
