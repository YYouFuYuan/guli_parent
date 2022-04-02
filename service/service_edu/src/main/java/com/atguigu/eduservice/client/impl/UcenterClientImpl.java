package com.atguigu.eduservice.client.impl;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.eduservice.client.UcenterClient;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {

    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        System.out.println("ucenter远程服务挂掉");
        return null;
    }
}