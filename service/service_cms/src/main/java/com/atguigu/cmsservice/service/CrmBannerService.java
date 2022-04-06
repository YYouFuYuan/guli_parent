package com.atguigu.cmsservice.service;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author Ay
 * @since 2022-03-11
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanner();

    void removeBannerById(String id);

    void updateBannerById(CrmBanner banner);

    void saveBanner(CrmBanner banner);
}
