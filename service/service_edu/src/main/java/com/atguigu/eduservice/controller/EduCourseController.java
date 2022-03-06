package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Ay
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiOperation("添加课程信息")
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@ApiParam(name = "CourseInfoVo", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo){
        courseService.saveCourseInfo(courseInfoVo);
        return R.ok();
    }
}
