package com.atguigu.orderservice.client.impl;

import com.atguigu.commonutils.ordervo.UcenterMemberOrder;
import com.atguigu.orderservice.client.UcenterClient;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImpl implements UcenterClient {

    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        System.out.println("ucenter服务器停了");
        return null;
    }
}
