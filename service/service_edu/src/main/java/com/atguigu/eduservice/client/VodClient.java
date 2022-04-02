package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.impl.VodFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-vod",fallback = VodFileDegradeFeignClient.class) //远程服务调用名
@Component
public interface VodClient {

    /**
     * 要远程调用的方法名，完全url
     * @param videoId
     * @return
     */
    @DeleteMapping("/eduvod/video/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);

    /**
     * 删除多个视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/eduvod/video/delete-batch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
