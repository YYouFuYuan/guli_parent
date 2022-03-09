package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.VideoInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Ay
 * @since 2022-03-06
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean getCountByChapterId(String id);

    String saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);

    boolean removeByCourseId(String id);
}
