package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.VideoInfoForm;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Ay
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("save-video-info")
    public R save(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm){

        String id = videoService.saveVideoInfo(videoInfoForm);
        return R.ok().data("videoId",id);
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("video-info/{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        VideoInfoForm videoInfoForm = videoService.getVideoInfoFormById(id);
        return R.ok().data("item", videoInfoForm);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("update-video-info/{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoForm videoInfoForm,

            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        videoService.updateVideoInfoById(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){

        boolean result = videoService.removeVideoById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}

