package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Ay
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;


    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }


    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id",value = "讲师ID",required = true)
            @PathVariable String id){
        eduTeacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "教师信息分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable(value = "current") long current,
            @ApiParam(name = "limit",value = "每页的数量",required = true)
            @PathVariable(value = "limit") long limit){
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //封装数据
        IPage<EduTeacher> page = eduTeacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }


    @ApiOperation(value = "教师信息分页查询带条件")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherConditin(
            @ApiParam(name = "current",value = "当前页",required = true)
            @PathVariable(value = "current") long current,
            @ApiParam(name = "limit",value = "每页的数量",required = true)
            @PathVariable(value = "limit") long limit,
            @ApiParam(name = "teacherQuery",value = "查询对象",required = false)
            @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建page对象
        Page<EduTeacher> pageParam = new Page<>(current,limit);
        eduTeacherService.pageQuery(pageParam,teacherQuery);
        //封装数据
        long total = pageParam.getTotal();
        List<EduTeacher> records = pageParam.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public R addTeacher(@ApiParam(name = "eduTeacher",value = "讲师信息",required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }
        else return R.error();
    }

    @ApiOperation(value = "根据讲师Id查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id",value = "讲师Id",required = true)
                            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);
        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }

    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public R getTeacher(
            @ApiParam(name = "eduTeacher",value = "修改信息",required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean res = eduTeacherService.updateById(eduTeacher);
        if(res){
            return R.ok();
        }
        else return R.error();
    }


    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<EduTeacher>(page, limit);

        Map<String, Object> map = eduTeacherService.pageListWeb(pageParam);

        return  R.ok().data(map);
    }
}

