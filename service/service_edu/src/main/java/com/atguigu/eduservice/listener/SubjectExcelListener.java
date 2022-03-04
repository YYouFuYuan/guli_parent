package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectService eduSubjectService;
    public SubjectExcelListener(){}
    public SubjectExcelListener(EduSubjectService eduSubjectService){
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new GuliException(20001,"文件数据为空");
        }
        //一行行读取
        //判断一级分类是否重复
        EduSubject eduOneSubject = this.existOneSubject(this.eduSubjectService,subjectData.getOneSubjectName());
        if(eduOneSubject == null){  //存储一级分类
            eduOneSubject = new EduSubject();
            eduOneSubject.setParentId("0");
            eduOneSubject.setTitle(subjectData.getOneSubjectName());
            this.eduSubjectService.save(eduOneSubject);
        }
        //判断二级分类是否重复
        String pid = eduOneSubject.getId();
        EduSubject eduTwoSubject = this.existTwoSubject(this.eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if(eduTwoSubject == null){
            eduTwoSubject = new EduSubject();
            eduTwoSubject.setParentId(pid);
            eduTwoSubject.setTitle(subjectData.getTwoSubjectName());
            this.eduSubjectService.save(eduTwoSubject);
        }
    }

    /**
     * 查询表里是否已经有一级分类
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");
        return eduSubjectService.getOne(queryWrapper);
    }

    /**
     * 查询表里是否已经有二级分类
     * @param name
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        return eduSubjectService.getOne(queryWrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
