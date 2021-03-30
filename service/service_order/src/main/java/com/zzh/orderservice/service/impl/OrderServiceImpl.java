package com.zzh.orderservice.service.impl;

import com.zzh.commonutils.vo.CourseWebVoOrder;
import com.zzh.commonutils.vo.UcenterMemberOrder;
import com.zzh.orderservice.client.EduClient;
import com.zzh.orderservice.client.UcenterClient;
import com.zzh.orderservice.entity.Order;
import com.zzh.orderservice.mapper.OrderMapper;
import com.zzh.orderservice.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzh.orderservice.util.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2021-03-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;
    @Override
    public String saveOrder(String courseId, String memberId) {
        //远程调用课程服务，根据课程id获取课程信息
        CourseWebVoOrder courseDto = eduClient.getCourseInfoDto(courseId);
        //远程调用用户服务，根据用户id获取用户信息
        UcenterMemberOrder ucenterMember = ucenterClient.getInfo(memberId);
        //创建订单
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseDto.getTitle());
        order.setCourseCover(courseDto.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMember.getMobile());
        order.setNickname(ucenterMember.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
