package com.zzh.cmsservice.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzh.cmsservice.entity.CrmBanner;
import com.zzh.cmsservice.mapper.CrmBannerMapper;
import com.zzh.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author zzh
 * @since 2021-03-25
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public void pageBanner(Page<CrmBanner> pageParam, Object o) {

    }

    @Override
    public CrmBanner getBannerById(String id) {
        return null;
    }

    @Override
    public void saveBanner(CrmBanner banner) {

    }

    @Override
    public void updateBannerById(CrmBanner banner) {

    }

    @Override
    public void removeBannerById(String id) {

    }

    @Override
    public List<CrmBanner> selectIndexList() {
        return null;
    }
}
