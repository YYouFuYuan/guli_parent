package com.atguigu.eduservice.client.impl;

import com.atguigu.eduservice.client.OrderClient;
import org.springframework.stereotype.Component;

@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        System.out.println("order远程服务..失败");
        return false;
    }
}
