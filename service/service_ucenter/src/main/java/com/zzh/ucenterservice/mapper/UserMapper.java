package com.zzh.ucenterservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.ucenterservice.entity.UcenterMember;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UcenterMember> {

    @Select("SELECT COUNT(1)" +
            "    FROM ucenter_member" +
            "    WHERE DATE(gmt_create) = #{day}")
    Integer selectRegisterCount(@Param("day") String day);
}
