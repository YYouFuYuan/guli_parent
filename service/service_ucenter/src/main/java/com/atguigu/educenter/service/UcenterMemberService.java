package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.LoginInfoVo;
import com.atguigu.educenter.entity.vo.LoginVo;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Ay
 * @since 2022-03-18
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 登录接口
     * @param loginVo
     * @return
     */
    String login(LoginVo loginVo);

    /**
     * 注册接口
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 获得用户信息并返回
     * @param memberId
     * @return
     */
    LoginInfoVo getLoginInfo(String memberId);

    UcenterMember getByOpenid(String openid);

    Integer countRegisterByDay(String day);
}
