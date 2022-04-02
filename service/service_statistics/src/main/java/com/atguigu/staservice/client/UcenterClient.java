package com.atguigu.staservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.staservice.client.impl.UcenterClientImp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientImp.class)
public interface UcenterClient {

    @GetMapping(value = "/educenter/apimember/countregister/{day}")
    public R registerCount(@PathVariable("day") String day);
}
