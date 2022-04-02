package com.atguigu.orderservice.client;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.orderservice.client.impl.EduClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "service-edu",fallback = EduClientImpl.class)
public interface EduClient {
    //根据课程id查询课程信息
    @GetMapping("/eduservice/course/getDto/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable("courseId") String courseId);
}
