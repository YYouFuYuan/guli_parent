package com.atguigu.orderservice.client.impl;

import com.atguigu.commonutils.ordervo.CourseWebVoOrder;
import com.atguigu.orderservice.client.EduClient;
import org.springframework.stereotype.Component;

@Component
public class EduClientImpl implements EduClient {
    @Override
    public CourseWebVoOrder getCourseInfoDto(String courseId) {
        System.out.println("edu 停");
        return null;
    }
}
