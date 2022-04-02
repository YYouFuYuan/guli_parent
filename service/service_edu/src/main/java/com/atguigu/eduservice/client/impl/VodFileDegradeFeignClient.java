package com.atguigu.eduservice.client.impl;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    /**
     * 要远程调用的方法名，完全url
     *
     * @param videoId
     * @return
     */
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }

    /**
     * 删除多个视频
     *
     * @param videoIdList
     * @return
     */
    @Override
    public R removeVideoList(List<String> videoIdList) {
        System.out.println("vod远程服务挂掉");
        return R.error().message("time out");
    }
}
