package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.vo.SubjectNestedVo;
import com.atguigu.eduservice.entity.vo.SubjectVo;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Ay
 * @since 2022-03-04
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(this)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到整个课程分类的列表，按照一二级目录填充完毕
     * @return
     */
    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVos = new ArrayList<>();

        //获取一级分类的数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        queryWrapper.orderByAsc("sort","id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);

        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper.ne("parent_id",0);
        queryWrapper.orderByAsc("sort","id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

        //填充一级分类的子列表
        int count = subjects.size();
        for(int i=0;i<count;i++){
            EduSubject eduSubject = subjects.get(i);
            //填充进vo
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(eduSubject,subjectNestedVo);
            subjectNestedVos.add(subjectNestedVo);

            //二级分类
            ArrayList<SubjectVo> subjectVos2 = new ArrayList<>();
            for(int j=0;j<subSubjects.size();j++){
                EduSubject eduSubject2 = subSubjects.get(j);
                if(eduSubject2.getParentId().equals(eduSubject.getId())){

                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(eduSubject2,subjectVo);
                    subjectVos2.add(subjectVo);

                }
            }
            subjectNestedVo.setChildren(subjectVos2);
        }

        return subjectNestedVos;
    }


}
