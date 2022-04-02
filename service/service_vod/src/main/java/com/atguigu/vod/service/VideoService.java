package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    /**
     * 删除一些课程
     * @param videoIdList
     */
    void removeVideoList(List<String> videoIdList);
}
