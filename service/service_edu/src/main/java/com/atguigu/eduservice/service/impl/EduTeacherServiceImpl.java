package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Ay
 * @since 2022-03-01
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        //没有查询条件
        if(teacherQuery == null){
            baseMapper.selectPage(pageParam,queryWrapper);
            return;
        }
        String name = teacherQuery.getName();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        Integer level = teacherQuery.getLevel();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }

        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }

        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        baseMapper.selectPage(pageParam,queryWrapper);
    }
}
