package com.atguigu.staservice.client.impl;

import com.atguigu.commonutils.R;
import com.atguigu.staservice.client.UcenterClient;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientImp implements UcenterClient {
    @Override
    public R registerCount(String day) {
        System.out.println("ucentero挂");
        return R.error().message("ucentero挂");
    }
}
